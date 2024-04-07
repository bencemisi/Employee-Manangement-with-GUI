package Ceg;

import java.io.Serializable;

//Junior Pozíciójú kolléga
public class Junior extends Dolgozok implements Serializable {
    private String hol_vegzett;
    private Senior mentora;

    public Junior(String namep, String e_mailp, int munkaberp, int evek,String hol_vegzettp, Senior mentorp){
        super(namep,e_mailp,munkaberp,evek);
        hol_vegzett= hol_vegzettp;
        mentora = mentorp;
    }
    public String getVegzettseg(){
        return hol_vegzett;
    }
    public Senior getMentor(){
        return mentora;
    }
    public void setHol_vegzett(String vegzes){
        hol_vegzett=vegzes;
    }
    public void setMentora(Senior mentor){
        mentora=mentor;
    }

    @Override
    public String Adatok_listazas() {
        if (mentora == null) {
            return  "A junior neve: "+this.getName()+" akit a "+this.getE_mail()+" címen lehet elérni. Fizetése: "+this.getMunkaber()+ "Ft és "+this.getItt_toltott_evek()+" éve van a cégnél. Tanulmányainak helye: "+ this.getVegzettseg()+". Jelenleg nincs Mentora\n";
        }
        return  "A junior neve: "+this.getName()+" akit a "+this.getE_mail()+" címen lehet elérni. Fizetése: "+this.getMunkaber()+ "Ft és "+this.getItt_toltott_evek()+" éve van a cégnél. Tanulmányainak helye: "+ this.getVegzettseg()+". Mentora :"+  this.getMentor().getName()+"\n";
    }
}
