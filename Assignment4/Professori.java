public class Professori extends Thread {
    private MonitorLaboratorio ML;
    private int indextesista;
    int access;

    public Professori(MonitorLaboratorio ML, int indextesista, int access) {
        this.ML = ML;
        this.indextesista = indextesista;
        this.access = access;
    }

    public void run() {
        int i = 0;
        while (i < access) {
            ML.take("prof", indextesista);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            i++;
        }

    }
}
