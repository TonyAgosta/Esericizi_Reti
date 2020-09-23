public class PigrecoThread extends Thread {
    private static float accuracy;
    private int flag;

    public PigrecoThread(float gradoaccuratezza) {
        accuracy = gradoaccuratezza;
    }

    public void run() {
        float pi; // il valore approssimativo del pigreco
        float i = 1;
        flag = 0; //flag che uso per decidere se fare la somma o la sottrazione nella serie di Gregory-Leibniz
        pi = 4 / i;
        double diff = pi - Math.PI; // diff viene usato per vedere controllare quant'è la differenza tra il pigreco calcolato e quello reale
        
        while (diff > accuracy) { //nella guardia del while controllo se il valore diff è maggiore del grado di accuratezza richiesto
            i = i + 2;
            if (flag == 0) {
                pi = pi - (4 / i);
                flag = 1;
                diff = Math.PI - pi;
            } else {
                pi = pi + (4 / i);
                flag = 0;
                diff = pi- Math.PI;
            }

        }
        System.out.println("il valore del pigreco calcolato è:" + pi);
    }
}
