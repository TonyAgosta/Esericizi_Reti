//Tony Agosta 544090

import java.util.ArrayList;

public class ContoCorrente {

    // INNER CLASS
    // utilizzata per creare coppie di stringhe in modo da simulare un movimento
    // bancario
    public class CoppiaMovimenti {
        private String Causale;
        private String Data;

        public CoppiaMovimenti(String caus, String dat) {
            this.Causale = caus;
            this.Data = dat;
        }

        public String getData() {
            return Data;
        }

        public String getCausale() {
            return Causale;
        }

    }

    private String NameCorrentista;
    private String nomecontocorrente;

    // arraylist in cui vengono memorizzati i movimenti bancari relativi a un
    // determinato Conto Corrente di un determinato Correntista
    private ArrayList<CoppiaMovimenti> listamovimenti;

    // COSTRUTTORE CLASSE CONTOCORRENTE
    public ContoCorrente() throws NullPointerException {
        this.listamovimenti = new ArrayList<CoppiaMovimenti>();
    }

    // metodo usato per settare il nome del Conto Corrente passato come argomento
    public void setNameContoCorrente(String s) throws NullPointerException {
        if (s == null)
            throw new NullPointerException();
        this.nomecontocorrente = s;
    }

    // metodo usato per settare il movimento bancario effettuato: set del campo
    // causale e del campo data
    public void setMoviemento(String mov, String dat) throws NullPointerException {
        if (mov == null)
            throw new NullPointerException();
        CoppiaMovimenti newMovimento = new CoppiaMovimenti(mov, dat);
        listamovimenti.add(newMovimento);

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
        return nomecontocorrente;
    }

    public ArrayList<CoppiaMovimenti> getListaMovimenti() {
        return listamovimenti;
    }

}