//Tony Agosta 544090

//La classe CoppiMovimenti contiene i metodi per settare e restiuire i valori di un movimento
//caratterizzato dai campi : Causale e Data

public class CoppiaMovimenti {
    private String Causale;
    private String Data;

    public void setData(String data) throws NullPointerException {
        this.Data = data;
    }

    public void setCausale(String causale) throws NullPointerException {
        this.Causale = causale;
    }

    public String getData() {
        return Data;
    }

    public String getCausale() {
        return Causale;
    }

}