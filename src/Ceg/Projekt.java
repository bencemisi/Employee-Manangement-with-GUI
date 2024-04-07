package Ceg;

import java.io.Serializable;

//Projektek amiket a cég elvállal
public class Projekt implements Serializable {
    private String azonosito;
    private String feladat;
    private Dolgozok felelos;

    public Projekt(String azonositop, String feladatp, Dolgozok felelosp)
    {
        azonosito=azonositop;
        feladat = feladatp;
        felelos= felelosp;
    }

    public String getAzonosito() {
        return azonosito;
    }

    public void setAzonosito(String azonosito) {
        this.azonosito = azonosito;
    }

    public String getFeladat() {
        return feladat;
    }

    public void setFeladat(String feladat) {
        this.feladat = feladat;
    }

    public Dolgozok getFelelos() {
        return felelos;
    }

    public void setFelelos(Dolgozok felelos) {
        this.felelos = felelos;
    }
    public String adatokListazasa(){return "A projekt azonosítója: "+azonosito+", leírása: "+feladat+", felelőse: "+felelos.getName();}
}
