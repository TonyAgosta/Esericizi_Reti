public class Studenti extends Thread {
    private MonitorLaboratorio ML;

    private int access;
    private int numtesistieaccessi;
    private int numprofessorieaccessi;

    public Studenti(MonitorLaboratorio ML, int numtesistieaccessi, int numprofessorieaccessi, int access) {
        this.ML = ML;
        this.numtesistieaccessi = numtesistieaccessi;
        this.numprofessorieaccessi = numprofessorieaccessi;
        this.access = access;
    }

    public void run() {
        int i = 0;

        while (i < access) {

            ML.take("stud");

            int j = ML.computerlibero();
            while (j == 404) {
                j = ML.computerlibero();
            }

            ML.occupasingolopc(j);
            System.out.printf("Lo studente %s ha occupato il pc numero %d\n", Thread.currentThread().getName(), j);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            ML.liberapc(j);
            System.out.printf("Lo studente %s ha liberato il pc numero %d\n", Thread.currentThread().getName(), j);

            ML.rilascia("stud");
            i++;
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
