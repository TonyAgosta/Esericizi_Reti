//Tony Agosta 544090

import java.util.ArrayList;

public class ContoCorrente {

    // INNER CLASS
    // utilizzata per creare coppie di stringhe in modo da simulare un movimento
    // bancario

    private String NameCorrentista;
    private String nomeContoCorrente;

    // arraylist in cui vengono memorizzati i movimenti bancari relativi a un
    // determinato Conto Corrente di un determinato Correntista
    private ArrayList<CoppiaMovimenti> ListaMovimenti;

    // COSTRUTTORE CLASSE CONTOCORRENTE
    public ContoCorrente() throws NullPointerException {
        this.ListaMovimenti = new ArrayList<CoppiaMovimenti>();
    }

    // metodo usato per settare il nome del Conto Corrente passato come argomento
    public void setNameContoCorrente(String s) throws NullPointerException {
        if (s == null)
            throw new NullPointerException();
        this.nomeContoCorrente = s;
    }

    // metodo usato per settare il movimento bancario effettuato: set del campo
    // causale e del campo data
    public void setMoviemento(String mov, String dat) throws NullPointerException {
        if (mov == null)
            throw new NullPointerException();
        CoppiaMovimenti newMovimento = new CoppiaMovimenti();
        newMovimento.setCausale(mov);
        newMovimento.setData(dat);
        ListaMovimenti.add(newMovimento);

    }

    // metodo usato per settare il nome del correntista associato a un determinato
    // Conto Corrente
    public void setNameCorrentista(String nameCorrentista) throws NullPointerException {
        if (nameCorrentista == null)
            throw new NullPointerException();
        this.NameCorrentista = nameCorrentista;
    }

    // metodo che restituisce il nome del Correntista associato a un determinato
    // Conto Corrente
    public String getNameCorrentista() {
        return NameCorrentista;
    }

    // metodo che restituisce il nome del Conto Corrente associato a un determinato
    // Correntista
    public String getnomeContoCorrente() {
        return nomeContoCorrente;
    }

    public ArrayList<CoppiaMovimenti> getListaMovimenti() {
        return ListaMovimenti;
    }

}