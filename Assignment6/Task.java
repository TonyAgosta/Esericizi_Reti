//Tony Agosta 544090

public class Task implements Runnable {

    private String causale;
    private Occorrenze occ;

    public Task(String causale, Occorrenze occ) {
        this.occ = occ;
        this.causale = causale;
    }

    public void run() {

        if (causale.equals("Bollettino")) {
            occ.addBollettino();
        }
        if (causale.equals("F24")) {
            occ.addF24();
        }
        if (causale.equals("PagoBancomat")) {

            occ.addPagoBancomato();
        }
        if (causale.equals("Accredito")) {
            occ.addAccredito();
        }
        if (causale.equals("Bonifico")) {
            occ.addBonifico();

        }
    }

}
