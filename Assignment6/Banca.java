//Tony Agosta 544090

import java.util.ArrayList;

public class Banca {

    private final ArrayList<ContoCorrente> banca; // arraylist in cui vengonon salvati tutti i conti correnti
    private int numCont;// numero di conti correnti
    private int numOperazioni;// numero di operazioni per ogni conto corrente scelto in modo casuale

    // array di stringhe in cui vengono memorizzati i movimenti banacari possibili
    private static final String[] movimenti = { "Bolletino", "F24", "PagoBancomat", "Bonifico", "Accredito" };

    // array di stringhe in cui vengono memorizzati i nomi dei correntisti.
    private final String[] correntisti;

    // COSTRUTTORE
    public Banca(int k) {
        this.banca = new ArrayList<ContoCorrente>();
        this.numCont = k;
        correntisti = new String[numCont];
        for (int i = 0; i < k; i++)
            correntisti[i] = ("Correntista-" + i);

    }

    // metodo che crea un nuovo conto corrente con i relativi campi: nome del
    // correntista e la lista dei movimenti
    public void setContoCorrente(String s, int i) throws NullPointerException {
        if (s == null)
            throw new NullPointerException();
        int operazione; // indice per selezionare il movimento bancario dall'array dei movimenti
                        // possibili
        String giorno; // variabile usata per costruire la data del movimento
        int randomDay; // giorno casuale
        int randomMonth;// mese casuale
        int randomYear;// anno casuale
        ContoCorrente newconto = new ContoCorrente(); // creo un nuovo contocorrente da aggiungere alla lista dei Conti
                                                      // Correnti
        newconto.setNameContoCorrente(s); // set del nome del Conto Corrente con formato "ContoCorrente-i"
        newconto.setNameCorrentista(correntisti[i]);// set del nome del correntista con formato "Correntista-i"
        this.numOperazioni = (int) (Math.random() * 10) + 1;// numero casuale di operazioni fatte dal correntista
        for (int j = 0; j < numOperazioni; j++) {
            operazione = (int) (Math.random() * 5); // selezione casuale di uno dei possibili movimenti
            randomDay = (int) (Math.random() * 30) + 1;// selezione casuale di un giorno tra 1 e 31
            randomMonth = (int) (Math.random() * 12) + 1;// selezione casuale di un mese
            randomYear = (int) (Math.random() * 2) + 2018;// selezione casuale di anno tra il 2018 e il 2020
            giorno = randomDay + "/" + randomMonth + "/" + randomYear; // composizione della data
            newconto.setMoviemento(movimenti[operazione], giorno);// set del movimento effettuato
        }
        banca.add(newconto); // aggiunta del nuovo Conto Corrente alla lista dei Conti Correnti

    }

    // Restituisce tutta la lista dei Conti Correnti
    public ArrayList<ContoCorrente> getListaContiCorrenti() {
        return banca;
    }

}