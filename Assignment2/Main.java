import java.util.concurrent.*;
import java.util.*;
import java.lang.reflect.Array;
import java.lang.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        long h = (long) (Math.random() * 20); // utilizzo un numero random per decidere quanti task (persone) far
                                              // entrare nella sala d'attesa
        int p = (int) h; // faccio il cast a int su h in modo da poterlo usare come indice per scorrere
                         // un array
        long t = (long) (Math.random() * p);// utilizzo un numero random per decidere la dimensione del gruppo di
                                            // task(persone) che possono entrare nella seconda sala
        int k = (int) t; // faccio il cast a int su t in modo da poterlo usare come indice per scorrere
                         // un array

        // stampo il numero di task che entrano nella prima sala e la dimensione del
        // gruppo di persone che possono entrare nella seconda sala
        System.out.println("Il numero di persone nella sala d'attesa e`:" + p);
        System.out.printf("Nella seconda sala entrano %d persone per volta :", k);
        if (p!=0 && k != 0) {
            Ufficio Salapiccola = new Ufficio(); // creo un oggetto di tipo Ufficio per gestire la seconda sala
            Task[] arraytask = new Task[p];// creo un array di dimensione p in cui salvo i p task
            int l = 0; // indice che viene usato per scorrere arraytask di k in k posizioni
            for (int i = 0; i < p; i++) {// in ogni posizione dell'array salvo un task
                arraytask[i] = new Task("Task" + i);
            }
            Task[] arraysalapiccola = new Task[k];// creo un array di dimensione k per eseguire k task alla volta
            int j = 0;// indice che viene usato per scorrere arraysalapiccola in moda da mandare in
                      // esecuzione k task per volta
            while (l < p) {
                for (j = 0; j < k; j++) {
                    if ((j + l) < p && Array.get(arraytask, j + l) != null) {// controllo che vengano rispettati i
                                                                             // limiti di
                                                                             // arraytask
                        arraysalapiccola[j] = arraytask[j + l];// copio in arraysalapiccola i task k per volta
                    } else
                        arraysalapiccola[j] = null;
                }
                Salapiccola.executeTask(arraysalapiccola, k);// eseguo i k task
                l = l + k;
            }

            Salapiccola.endUfficio();// termino
        }else {
            System.out.println("Il numero delle persone nella prima sala o il numero delle persone che possono entrare nella seconda e` 0");
        }
    }
}
