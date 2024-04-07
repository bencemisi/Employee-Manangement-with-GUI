package Ceg;

import org.junit.Assert;
import org.junit.Test;

public class KonsTest {
    @Test
    public void ProjektKonstruktor() {
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,false);

        Projekt tesztProjekt = new Projekt("Teszt1","teszt1leiras",tesztSenior);
        Assert.assertEquals("Teszt1",tesztProjekt.getAzonosito());
        Assert.assertEquals("teszt1leiras",tesztProjekt.getFeladat());
        Assert.assertEquals(tesztSenior,tesztProjekt.getFelelos());

    }

    @Test
    public void SeniorKonstruktor() {
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,2,3,false);
        Assert.assertEquals("tesztSenior",tesztSenior.getName());
        Assert.assertEquals("teszt@email.com",tesztSenior.getE_mail());
        Assert.assertEquals(1,tesztSenior.getMunkaber());
        Assert.assertEquals(2,tesztSenior.getItt_toltott_evek());
        Assert.assertEquals(3,tesztSenior.getMiotaSenior());
        Assert.assertFalse(tesztSenior.mentoral_e());

    }
    @Test
    public void JuniorKonstruktor() {
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,2,3,false);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,2,"tesztSuli",tesztSenior);
        Assert.assertEquals("tesztJunior",tesztJunior.getName());
        Assert.assertEquals("teszt@email.com",tesztJunior.getE_mail());
        Assert.assertEquals(1,tesztJunior.getMunkaber());
        Assert.assertEquals(2,tesztJunior.getItt_toltott_evek());
        Assert.assertEquals("tesztSuli",tesztJunior.getVegzettseg());
        Assert.assertEquals(tesztSenior,tesztJunior.getMentor());


    }
    @Test
    public void KisegitoKonstruktor() {
        Kisegito_szemelyzet gyakornok= new Kisegito_szemelyzet("gy","gy@email",1,2,Munkakor.Gyakornok,false);
        Kisegito_szemelyzet takarito= new Kisegito_szemelyzet("t","t@email",1,2,Munkakor.Takarito,true);
        Kisegito_szemelyzet biztonsagi = new Kisegito_szemelyzet("b","b@email",1,2,Munkakor.Biztonsagi_or,false);
        Kisegito_szemelyzet portas = new Kisegito_szemelyzet("p","p@email",1,2,Munkakor.Portas,true);
        Assert.assertEquals("gy",gyakornok.getName());
        Assert.assertEquals("gy@email",gyakornok.getE_mail());
        Assert.assertEquals(1,gyakornok.getMunkaber());
        Assert.assertEquals(2,gyakornok.getItt_toltott_evek());
        Assert.assertEquals(Munkakor.Gyakornok,gyakornok.getPozicio());

        Assert.assertEquals("t",takarito.getName());
        Assert.assertEquals("t@email",takarito.getE_mail());
        Assert.assertEquals(1,takarito.getMunkaber());
        Assert.assertEquals(2,takarito.getItt_toltott_evek());
        Assert.assertEquals(Munkakor.Takarito,takarito.getPozicio());

        Assert.assertEquals("b",biztonsagi.getName());
        Assert.assertEquals("b@email",biztonsagi.getE_mail());
        Assert.assertEquals(1,biztonsagi.getMunkaber());
        Assert.assertEquals(2,biztonsagi.getItt_toltott_evek());
        Assert.assertEquals(Munkakor.Biztonsagi_or,biztonsagi.getPozicio());

        Assert.assertEquals("p",portas.getName());
        Assert.assertEquals("p@email",portas.getE_mail());
        Assert.assertEquals(1,portas.getMunkaber());
        Assert.assertEquals(2,portas.getItt_toltott_evek());
        Assert.assertEquals(Munkakor.Portas,portas.getPozicio());


    }
}
