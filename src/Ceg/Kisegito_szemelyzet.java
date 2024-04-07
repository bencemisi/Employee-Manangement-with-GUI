package Ceg;

import java.io.Serializable;



//Portás, Takarító, Biztonsági Őr, Gyakornok pozícióban lévő dolgozók
public class Kisegito_szemelyzet extends Dolgozok implements Serializable {
    private Munkakor pozicio;
    private boolean ejjeli;

    public Kisegito_szemelyzet(String namep, String e_mailp, int munkaberp, int evek,Munkakor poziciop,boolean ejjelip) {
        super(namep,e_mailp,munkaberp,evek);
        pozicio= poziciop;
        ejjeli= ejjelip;
    }

    public Munkakor getPozicio() {
        return pozicio;
    }

    public void setPozicio(Munkakor pozicio) {
        this.pozicio = pozicio;
    }

    public boolean isEjjeli() {
        return ejjeli;
    }

    public void setEjjeli(boolean ejjeli) {
        this.ejjeli = ejjeli;
    }

    @Override
    public String Adatok_listazas() {
        String muszak;
        if(ejjeli) {
            muszak = "éjjeli";}
        else{
            muszak = "nappali";}
         return  "Az alkalmazott neve: "+this.getName()+" akit a "+this.getE_mail()+" címen lehet elérni. Fizetése: "+this.getMunkaber()+ "Ft és "+this.getItt_toltott_evek()+" éve van a cégnél. Beosztása: "+ this.getPozicio()+ " aki "+muszak+" műszakos. \n";
    }
}
