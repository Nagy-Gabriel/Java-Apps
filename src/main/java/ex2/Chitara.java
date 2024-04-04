package ex2;

class Chitara extends InstrumentMuzical {
    TipChitara tipChitara;
    int nrCorzi;

    public Chitara() {
        super("", 0); // Call superclass constructor
    }
    public Chitara(String producator, double pret, TipChitara tipChitara, int nrCorzi) {
        super(producator, pret); // Call superclass constructor
        this.tipChitara = tipChitara;
        this.nrCorzi = nrCorzi;
    }

    @Override
    void afisareDetalii() {
        System.out.println("Chitara: Producator - " + producator + ", Pret - " + pret + ", Tip - " + tipChitara + ", Numar Corzi - " + nrCorzi);
    }

    public TipChitara getTipChitara() {
        return tipChitara;
    }

    public int getNrCorzi() {
        return nrCorzi;
    }

    public void setTipChitara(TipChitara tipChitara) {
        this.tipChitara = tipChitara;
    }

    public void setNrCorzi(int nrCorzi) {
        this.nrCorzi = nrCorzi;
    }
}