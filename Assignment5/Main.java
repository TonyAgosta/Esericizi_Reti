// Tony Agosta 544090

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int i, k;
        k = (int) (Math.random() * 10) + 1; // thread consumatori
        System.out.println("Il numero dei consumatori e`:" + k);

        ArrayList<Consumatore> consumatori = new ArrayList<>(k);
        String basedir = args[0];// path da seguire
        File startDirectory = new File(basedir);// prima directory
        if (!startDirectory.isDirectory()) {
            System.out.println("Initial file is not a directory!");
            return;
        }
        LinkedList<File> directories = new LinkedList<File>();
        Coda coda = new Coda(directories);
        Produttore produttore = new Produttore(startDirectory, coda);
        for (i = 0; i < k; i++) {
            consumatori.add(new Consumatore(coda, directories));
        }
        // avvio del produttore
        produttore.start();

        // avvio dei consumatori
        for (i = 0; i < k; i++) {
            consumatori.get(i).start();
        }

    }
}
