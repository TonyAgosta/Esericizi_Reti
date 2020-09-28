import java.util.concurrent.*;
import java.util.*;
import java.lang.reflect.Array;
import java.lang.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        long h = (long) (Math.random() * 20);
        int p = (int) h;
        long t = (long) (Math.random() * p);
        int k = (int) t;
        int l = 0;
        int j = 0;
        System.out.println("p=" + p);
        System.out.println("k=" + k);

        Ufficio Salapiccola = new Ufficio();
        Task[] arraytask = new Task[p];
        for (int i = 0; i < p; i++) {
            arraytask[i] = new Task("Task" + i);
        }
        Task[] arraysalapiccola = new Task[k];
        while (l < p) {
            //System.out.println("qui");
            for (j = 0; j < k; j++) {
                if ((j + l) < p && Array.get(arraytask, j + l) != null) {
                    arraysalapiccola[j] = arraytask[j + l];
                }else arraysalapiccola[j]=null;
            }
            /*if (arraysalapiccola[k - 1] != null)*/
                Salapiccola.executeTask(arraysalapiccola, k);
            l = l + k;
        }

        Salapiccola.endUfficio();
    }
}
