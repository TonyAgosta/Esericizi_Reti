//Tony Agosta 544090
public class Occorrenze {
    private int Bollettino;
    private int F24;
    private int PagoBancomat;
    private int Accredito;
    private int Bonifico;

    public Occorrenze() {
        Bollettino = 0;
        F24 = 0;
        PagoBancomat = 0;
        Accredito = 0;
        Bonifico = 0;
    }

    public synchronized void addBonifico() {
        this.Bonifico++;
    }

    public synchronized void addBollettino() {
        this.Bollettino++;
    }

    public synchronized void addF24() {
        this.F24++;
    }

    public synchronized void addAccredito() {
        this.Accredito++;
    }

    public synchronized void addPagoBancomato() {
        this.PagoBancomat++;
    }

    public int getBonifico() {
        return this.Bonifico;
    }

    public int getBollettino() {
        return this.Bollettino;
    }

    public int getF24() {
        return this.F24;
    }

    public int getAccredito() {
        return this.Accredito;
    }

    public int getPagoBancomat() {
        return this.PagoBancomat;
    }

}
