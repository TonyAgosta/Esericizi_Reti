public class Tesisti extends Thread {
    private MonitorLaboratorio ML;
    private int indextesista;
    private int access;

    public Tesisti(MonitorLaboratorio ML, int indextesista, int access) {
        this.ML = ML;
        this.indextesista = indextesista;
        this.access = access;
    }

    public void run() {
        int i = 0;
        while (i < access) {
            ML.take("tes", indextesista);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            i++;
        }
    }
}
