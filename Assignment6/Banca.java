//Tony Agosta 544090

//La classe Banca ha il compito di creare e aggiungere i conti correnti alla lista dei conti correnti. 
//Rappresenta la lista dei conti correnti

import java.util.ArrayList;

public class Banca {

    private ArrayList<ContoCorrente> listaconti = new ArrayList<ContoCorrente>();; // arraylist in cui vengonon salvati
                                                                                   // tutti i conti correnti
    private int numOperazioni;// numero di operazioni per ogni conto corrente scelto in modo casuale

    // array di stringhe in cui vengono memorizzati i movimenti banacari possibili
    private static final String[] movimenti = { "Bollettino", "F24", "PagoBancomat", "Bonifico", "Accredito" };

    private String[] correntisti; // array di stringhe in cui salvo i nomi dei correntisti
    private String[] conticorrenti; // array di stringhe in cui salvo i nomi dei conticorrenti

    public void addConto() {
        int k = (int) (Math.random() * 10) + 1; // numero di conti correnti da aggiungere
        correntisti = new String[k];
        conticorrenti = new String[k];
        for (int i = 0; i < k; i++) {
            correntisti[i] = ("Correntista-" + i);
            conticorrenti[i] = ("ContoCorrente-" + i);
            setContoCorrente(conticorrenti[i], i);
        }
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
            newconto.setMovimento(movimenti[operazione], giorno);// set del movimento effettuato
        }
        listaconti.add(newconto); // aggiunta del nuovo Conto Corrente alla lista dei Conti Correnti

    }

    // Restituisce tutta la lista dei Conti
    public ArrayList<ContoCorrente> getListaContiCorrenti() {
        return listaconti;
    }

}