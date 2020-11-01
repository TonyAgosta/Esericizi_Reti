import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int i;
        int k; // numero di thread consumatori
        k = (int) (Math.random() * 5);
        while (k == 0)
            k = (int) (Math.random() * 10);
        System.out.println("Il numero dei consumatori e`:" + k);

        ArrayList<Consumatore> consumatori = new ArrayList<>(k);
        String basedir = args[0];// inziio del path da seguire
        File startDirectory = new File(basedir);
        if (!startDirectory.isDirectory()) {
            System.out.println("Initial file is not a directory!");
            return;
        }
        LinkedList<File> directories = new LinkedList<File>();
        Coda coda = new Coda(directories);
        Produttore produttore = new Produttore(directories, startDirectory, coda);
        for (i = 0; i < k; i++) {
            consumatori.add(new Consumatore(coda, directories));
        }
        produttore.start();
        for (i = 0; i < k; i++) {
            consumatori.get(i).start();
        }

    }
}
