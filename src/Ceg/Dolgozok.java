package Ceg;

import java.io.Serializable;

//Minden egyes munkatárs egy Dolgozó lesz, ezért Abstrakt ez az osztály
public abstract class Dolgozok implements Serializable {
  private String name;
  private String e_mail;
  private int munkaber;
  private int itt_toltott_evek;

    public Dolgozok(String namep, String e_mailp, int munkaberp, int evek) {
        name = namep;
        e_mail= e_mailp;
        munkaber = munkaberp;
        itt_toltott_evek = evek;
    }
    public String getName(){
        return name;
    }
    public String getE_mail(){
        return e_mail;
    }
    public int getMunkaber(){
        return munkaber;
    }
    public int getItt_toltott_evek(){
        return itt_toltott_evek;
    }
    public void setName(String namep){
        name=namep;
    }
    public void setE_mail(String e_mailp){
        e_mail=e_mailp;
    }
    public void setBer(int berp){
        munkaber=berp;
    }
    public void setItt_toltott_evek(int evekp){
        itt_toltott_evek=evekp;
    }
    public abstract String Adatok_listazas();
}
