package Ceg;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//A Ceg osztály 2 listája tartalmazza hogy milyen megbízásokat vállált el a cég
//, illetve kik és milyen pozicióban dolgoznak a válallatnál

public class Ceg implements Serializable {

    private ArrayList<Dolgozok> alkalmazottak;
    private ArrayList<Projekt> megbizasok;

    //Paraméter nélküli konstruktor
    public Ceg(){

        alkalmazottak = new ArrayList<>();
        megbizasok = new ArrayList<>();
    }


    public ArrayList<Dolgozok> getAlkalmazottak() {

        return alkalmazottak;
    }

    public void setAlkalmazottak(ArrayList<Dolgozok> alkalmazottak) {

        this.alkalmazottak = alkalmazottak;
    }

    public ArrayList<Projekt> getMegbizasok() {

        return megbizasok;
    }

    public void setMegbizasok(ArrayList<Projekt> megbizasok) {

        this.megbizasok = megbizasok;
    }

    //A céghez dolgozót vettünk fel, így bekerült a nyilvántartásba
    public void dolgozot_felvesz(Dolgozok felvenni) {
        alkalmazottak.add(felvenni);

    }
    //A cégből dolgozót bocsájtottunk el, így kikerült a nyilvántartásból
    public void dolgozot_kirug(String neve, String email){
        for(int i = 0; i< alkalmazottak.size();i++){
            if(alkalmazottak.get(i).getName().equals(neve) && alkalmazottak.get(i).getE_mail().equals(email) ){
                alkalmazottak.remove(i);
                return;
            }
        }


    }

    //Junior pozíciójú kollége előléptetése Seniorrá
    public void juniorEloleptetes(Junior junior){
       Senior senior = new Senior(junior.getName(),junior.getE_mail(),junior.getMunkaber(),junior.getItt_toltott_evek(),0,false);
        for(int i = alkalmazottak.size()-1; i>=0;i--){
            if(alkalmazottak.get(i).getE_mail().equals(junior.getE_mail()) && alkalmazottak.get(i).getName().equals(junior.getName())){
                alkalmazottak.remove(i);
                break;
            }
        }

       alkalmazottak.add(senior);
    }
    //Gyakornok pozíciójú kollége előléptetése Juniorrá
    public void gyakornokEloleptetes(Kisegito_szemelyzet gyakornok){
        Senior gyakornokSeniora = null;
        ArrayList<Senior> mentorok = new ArrayList<>();
        for (Dolgozok dolgozok : alkalmazottak) {
            if (dolgozok instanceof Senior) {
                mentorok.add((Senior) dolgozok);
            }
        }
        for ( Senior senior : mentorok) {
            if (!senior.mentoral_e()) {
                gyakornokSeniora = senior;
                gyakornokSeniora.setMentoral(true);
                break;
            }
        }
        if(gyakornokSeniora == null){
            Random rand = new Random();
            int mentorokSzama = mentorok.size();
            int random = rand.nextInt(mentorokSzama);
            gyakornokSeniora = mentorok.get(random);

        }




        for(int i = 0; i< alkalmazottak.size();i++){
            if(alkalmazottak.get(i).getName().equals(gyakornok.getName()) && alkalmazottak.get(i).getE_mail().equals(gyakornok.getE_mail())){
                alkalmazottak.remove(i);
                break;
            }
        }
        Junior junior = new Junior(gyakornok.getName(),gyakornok.getE_mail(),gyakornok.getMunkaber(),gyakornok.getItt_toltott_evek(),"Nálunk végezte a gyakorlatát",gyakornokSeniora);
        alkalmazottak.add(junior);
    }

    //Junior pozíciójú kollége lefokozása Gyakornokká
    public void lefokoz(Junior junior){
        Kisegito_szemelyzet gyakornok = new Kisegito_szemelyzet(junior.getName(),junior.getE_mail(),junior.getMunkaber(),junior.getItt_toltott_evek(),Munkakor.Gyakornok,false);
        for(int i = 0; i< alkalmazottak.size();i++){
            if(alkalmazottak.get(i).getName().equals(junior.getName()) && alkalmazottak.get(i).getE_mail().equals(junior.getE_mail())){
                alkalmazottak.remove(i);
                break;
            }
        }

        alkalmazottak.add(gyakornok);
    }
    //Dolgozó keresése a nyilvántartásban a neve alapján
    public Dolgozok dolgozotKeres(String name){
        for (Dolgozok dolgozok : alkalmazottak) {
            if (dolgozok.getName().equals(name)) {
                return dolgozok;
            }
        }
        return null;
    }
    //Új Projekt elvállalása
    public void munkat_felvesz(Projekt uj){
        megbizasok.add(uj);
    }
    public Projekt munkat_keres(String azonosito){
        for (Projekt projekt : megbizasok) {
            if (projekt.getAzonosito().equals(azonosito)) {
                return projekt;
            }
        }
        return null;
    }
    //Az elvállalt Projektek közül valamely befejezése vagy félbe hagyása
    public void munkat_lead(String ID){
        for(int i = 0; i< megbizasok.size();i++){
            if(megbizasok.get(i).getAzonosito().equals(ID)){
                megbizasok.remove(i);
                return;
            }
        }
    }
    public void munkat_listaz(){
        //A cégnél folyó munkálatok kilistázása információkkal
    }
    //A cég állapotának mentése
    public void mentes(){
        try {
            FileOutputStream f = new FileOutputStream("cegem.txt");
            ObjectOutputStream out= new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        }catch(IOException except)
        {
            System.out.println("Hiba a fáljbaírásnál.");
        }

    }
    //A cég állapotának betöltése
    public void load() {
        try {
            FileInputStream f = new FileInputStream("cegem.txt");
            ObjectInputStream in = new ObjectInputStream(f);
            Ceg loaded = (Ceg) in.readObject();
            this.alkalmazottak = loaded.alkalmazottak;
            this.megbizasok = loaded.megbizasok;
            in.close();
            JOptionPane.showMessageDialog(null, "A betöltés sikeres");
        } catch (Exception except) {
            JOptionPane.showMessageDialog(null, "Hiba a beolvasásnál");
        }

    }
}
