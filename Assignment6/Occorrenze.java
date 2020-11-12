//Tony Agosta 544090

//La classe occorrenze continene i metodi usati per incrementare in modo thread-safe 
//i valori delle occorrenze dei movimenti; 
//inoltre contiene i metodi che vegono utilzzati per restituire 
//il totale delle occorrenze di ogni tipo di causale

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

    // incrementa il numero dei bonifici
    public synchronized void addBonifico() {
        this.Bonifico++;
    }

    // incremento il numero dei bollettini
    public synchronized void addBollettino() {
        this.Bollettino++;
    }

    // incrementa il numero dgli F24
    public synchronized void addF24() {
        this.F24++;
    }

    // incrementa il numero degli accrediti
    public synchronized void addAccredito() {
        this.Accredito++;
    }

    // incrementa il numero dei PagoBancomat
    public synchronized void addPagoBancomat() {
        this.PagoBancomat++;
    }

    // Resituisce il numero di bonifici
    public int getBonifico() {
        return this.Bonifico;
    }

    // restituisce il numero di bollettini
    public int getBollettino() {
        return this.Bollettino;
    }

    // Restituisce il numero di F24
    public int getF24() {
        return this.F24;
    }

    // Restituisce il numero di accrediti
    public int getAccredito() {
        return this.Accredito;
    }

    // Restituisce il numero di PagoBancomat
    public int getPagoBancomat() {
        return this.PagoBancomat;
    }

}
