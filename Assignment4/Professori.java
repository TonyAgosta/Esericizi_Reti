public class Professori extends Thread {
    private MonitorLaboratorio ML;

    private int access;

    public Professori(MonitorLaboratorio ML, int access) {
        this.ML = ML;
        this.access = access;
    }

    public void run() {
        int i = 0;
        while (i < access) {

            ML.take("prof");

            System.out.printf("il professore %s ha occupato il laboratorio\n", Thread.currentThread().getName());
            ML.occupacomputer();
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            ML.liberalaboratorio();
            System.out.printf("il professore %s ha liberato il laboratorio\n", Thread.currentThread().getName());
            i++;
            ML.rilascia("prof");
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
