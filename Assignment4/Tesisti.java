//Tony Agosta 544090

public class Tesisti extends Thread {
    private MonitorLaboratorio ML;
    private int indextesista;
    private int access;

    public Tesisti(MonitorLaboratorio ML, int indextesista, int access) {
        if (ML == null)
            throw new NullPointerException();
        this.ML = ML;
        this.indextesista = indextesista;
        this.access = access;
    }

    public void run() {
        int i = 0;
        // ciclo fin quando il thread non ha finito il numero di accessi identificato
        // dalla variabile this.access
        while (i < access) {

            ML.take("tes");

            System.out.printf("il tesista %s ha occupato  il pc numero %d\n", Thread.currentThread().getName(),
                    indextesista);
            ML.occupasingolopc(indextesista);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            ML.liberapc(indextesista);
            System.out.printf("il tesista %s ha liberato il pc numero %d\n", Thread.currentThread().getName(),
                    indextesista);

            ML.rilascia("tes");
            i++;
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
