package Ceg;

import java.io.Serializable;

//Senior Pozíciójú kolléga

public class Senior extends Dolgozok implements Serializable {
    private int miotaSenior;
    private boolean mentoral;
    public Senior(String namep, String e_mailp, int munkaberp, int evek,int miotaSeniorp, boolean mentoral_e){
        super(namep,e_mailp,munkaberp,evek);
        miotaSenior= miotaSeniorp;
        mentoral = mentoral_e;
    }
    public int getMiotaSenior(){
        return miotaSenior;
    }
    public boolean mentoral_e(){
        return mentoral;
    }
    public void setMiotaSenior(int honap){
        miotaSenior=honap;
    }
    public void setMentoral(boolean mentoral_e){
        mentoral=mentoral_e;
    }

    @Override
    public String Adatok_listazas() {
        if(mentoral_e()) {
            return  "Senior neve: " + this.getName() + " akit a " + this.getE_mail() + " címen lehet elérni. Fizetése: " + this.getMunkaber() + "Ft , " + this.getItt_toltott_evek() + " éve van a cégnél és " + this.miotaSenior + " hónapja Senior illetve mentorál is. \n";
        }else{
            return  "Senior neve: " + this.getName() + " akit a " + this.getE_mail() + " címen lehet elérni. Fizetése: " + this.getMunkaber() + "Ft , " + this.getItt_toltott_evek() + " éve van a cégnél és " + this.miotaSenior + " hónapja Senior de nem mentorál.\n";

        }
    }
}

