package pl.patryk.ztpj.model;

public class Handlowiec extends Pracownik {
    private int prowizja;
    private int limit_prowizji;

    public Handlowiec(int prowizja, int limit_prowizji) {
        this.prowizja = prowizja;
        this.limit_prowizji = limit_prowizji;
    }

    public int getProwizja() {
        return prowizja;
    }

    public void setProwizja(int prowizja) {
        this.prowizja = prowizja;
    }

    public int getLimit_prowizji() {
        return limit_prowizji;
    }

    public void setLimit_prowizji(int limit_prowizji) {
        this.limit_prowizji = limit_prowizji;
    }
}
