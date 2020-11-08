public class CoppiaMovimenti {
    private String Causale;
    private String Data;

    public CoppiaMovimenti() {

    }

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