public class Studenti extends Thread {
    private MonitorLaboratorio ML;

    private int indextesista;
    int access;

    public Studenti(MonitorLaboratorio ML, int indextesista, int access) {
        this.ML = ML;
        this.indextesista = indextesista;
        this.access = access;
    }

    public void run() {
        int i = 0;

        while (i < access) {
            ML.take("stud", indextesista);
            try {
                Thread.sleep((long) (Math.random() * 4000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }

    }
}
