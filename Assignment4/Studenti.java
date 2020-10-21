//Tony Agosta 544090

public class Studenti extends Thread {
    private MonitorLaboratorio ML;

    private int access;

    public Studenti(MonitorLaboratorio ML, int access) throws NullPointerException {
        if (ML == null)
            throw new NullPointerException();
        this.ML = ML;
        this.access = access;
    }

    public void run() {
        int i = 0;
        // ciclo fin quando il thread non ha finito il numero di accessi identificato
        // dalla variabile this.access
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
