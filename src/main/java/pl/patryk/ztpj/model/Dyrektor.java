package pl.patryk.ztpj.model;

import pl.patryk.ztpj.model.enums.StanowiskoEnum;

public class Dyrektor extends Pracownik {
    private int dodatek_sluzbowy;
    private String karta_sluzbowa;
    private int limit_kosztow;

    public Dyrektor(){this.setStanowisko(StanowiskoEnum.DYREKTOR.name());}

    public Dyrektor(int dodatek_sluzbowy, String karta_sluzbowa, int limit_kosztow) {
        this.dodatek_sluzbowy = dodatek_sluzbowy;
        this.karta_sluzbowa = karta_sluzbowa;
        this.limit_kosztow = limit_kosztow;
        this.setStanowisko(StanowiskoEnum.DYREKTOR.name());
    }

    public void setDodatek_sluzbowy(int dodatek_sluzbowy) {
        this.dodatek_sluzbowy = dodatek_sluzbowy;
    }

    public void setKarta_sluzbowa(String karta_sluzbowa) {
        this.karta_sluzbowa = karta_sluzbowa;
    }

    public void setLimit_kosztow(int limit_kosztow) {
        this.limit_kosztow = limit_kosztow;
    }

    public int getDodatek_sluzbowy() {
        return dodatek_sluzbowy;
    }

    public String getKarta_sluzbowa() {
        return karta_sluzbowa;
    }

    public int getLimit_kosztow() {
        return limit_kosztow;
    }

}
