package ex2;

class SetTobe extends InstrumentMuzical {
    TipTobe tipTobe;
    int nrTobe;
    int nrCinele;
        public SetTobe() {
            super("", 0);
        }

    public SetTobe(String producator, double pret, TipTobe tipTobe, int nrTobe, int nrCinele) {
        super(producator, pret);
        this.tipTobe = tipTobe;
        this.nrTobe = nrTobe;
        this.nrCinele = nrCinele;
    }

    public TipTobe getTipTobe() {
        return tipTobe;
    }

    public int getNrTobe() {
        return nrTobe;
    }

    public int getNrCinele() {
        return nrCinele;
    }

    public void setTipTobe(TipTobe tipTobe) {
        this.tipTobe = tipTobe;
    }

    public void setNrTobe(int nrTobe) {
        this.nrTobe = nrTobe;
    }

    public void setNrCinele(int nrCinele) {
        this.nrCinele = nrCinele;
    }
    void afisareDetalii() {
        System.out.println("Set Tobe: Producator - " + producator + ", Pret - " + pret + ", Tip - " + tipTobe + ", Numar Tobe - " + nrTobe + ", Numar Cinele - " + nrCinele);
    }
}