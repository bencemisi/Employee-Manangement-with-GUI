package Ceg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CegTest {

Ceg tesztCeg;


    @Before
    public void ceglista(){
        tesztCeg = new Ceg();

    }

    @Test
    public void testParamNelkuliKonstruktor() {

        Assert.assertEquals(new ArrayList<Dolgozok>(), tesztCeg.getAlkalmazottak());
        Assert.assertEquals(new ArrayList<Projekt>(), tesztCeg.getMegbizasok());

    }

    @Test
    public void testDolgozoHozzaadasa(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);

        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);

        Assert.assertEquals(2, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztSenior, tesztCeg.getAlkalmazottak().get(0));

    }
 @Test
    public void testDolgozoTorlese(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);

        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);
        tesztCeg.dolgozot_kirug("tesztSenior","teszt@email.com");

        Assert.assertEquals(1, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztJunior, tesztCeg.getAlkalmazottak().get(0));

    }
@Test
    public void testJuniorEloleptetese(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);
        tesztCeg.juniorEloleptetes(tesztJunior);

        Assert.assertEquals(2, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztSenior.getClass(), tesztCeg.getAlkalmazottak().get(1).getClass());
        Assert.assertNotEquals(tesztJunior, tesztCeg.getAlkalmazottak().get(1));

    }

    @Test
    public void testGyakornokEloleptetese(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);
        Kisegito_szemelyzet tesztGyakornok = new Kisegito_szemelyzet("tesztGyakornok","teszt@email.com",1,1,Munkakor.Gyakornok,false);
        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);
        tesztCeg.dolgozot_felvesz(tesztGyakornok);
        tesztCeg.gyakornokEloleptetes(tesztGyakornok);

        Assert.assertEquals(3, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztJunior.getClass(), tesztCeg.getAlkalmazottak().get(2).getClass());
        Assert.assertNotEquals(tesztGyakornok, tesztCeg.getAlkalmazottak().get(2));

    }
    @Test
    public void testJuniorLefokozas(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);
        Kisegito_szemelyzet tesztGyakornok = new Kisegito_szemelyzet("tesztGyakornok","teszt@email.com",1,1,Munkakor.Gyakornok,false);
        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);
        tesztCeg.lefokoz(tesztJunior);

        Assert.assertEquals(2, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztGyakornok.getClass(), tesztCeg.getAlkalmazottak().get(1).getClass());
        Assert.assertNotEquals(tesztJunior, tesztCeg.getAlkalmazottak().get(1));

    }

    @Test
    public void testDolgozotKeres(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Junior tesztJunior = new Junior("tesztJunior","teszt@email.com",1,1,"Tesztsuli",tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztSenior);
        tesztCeg.dolgozot_felvesz(tesztJunior);

        Dolgozok keresett = tesztCeg.dolgozotKeres("tesztJunior");


        Assert.assertEquals(2, tesztCeg.getAlkalmazottak().size());
        Assert.assertEquals(tesztJunior.getClass(), keresett.getClass());
        Assert.assertEquals("tesztJunior",keresett.getName());

    }
    @Test
    public void testMunkatFelvesz(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Projekt tesztProjekt = new Projekt("teszt1","ElsoTesztProjekt",tesztSenior);
         tesztCeg.munkat_felvesz(tesztProjekt);

        Assert.assertEquals(1, tesztCeg.getMegbizasok().size());
        Assert.assertEquals("teszt1",tesztCeg.getMegbizasok().get(0).getAzonosito());

    }
    @Test
    public void testMunkatLead(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Projekt tesztProjekt = new Projekt("teszt1","ElsoTesztProjekt",tesztSenior);
        Projekt tesztProjekt2 = new Projekt("teszt2","masodikTesztProjekt",tesztSenior);
        tesztCeg.munkat_felvesz(tesztProjekt);
        tesztCeg.munkat_felvesz(tesztProjekt2);
        tesztCeg.munkat_lead("teszt1");

        Assert.assertEquals(1, tesztCeg.getMegbizasok().size());
        Assert.assertEquals("teszt2",tesztCeg.getMegbizasok().get(0).getAzonosito());

    }
    @Test
    public void testMunkatKeres(){
        Senior tesztSenior = new Senior("tesztSenior","teszt@email.com",1,1,1,true);
        Projekt tesztProjekt = new Projekt("teszt1","ElsoTesztProjekt",tesztSenior);
        Projekt tesztProjekt2 = new Projekt("teszt2","masodikTesztProjekt",tesztSenior);
        tesztCeg.munkat_felvesz(tesztProjekt);
        tesztCeg.munkat_felvesz(tesztProjekt2);

        Projekt keresett = tesztCeg.munkat_keres("teszt2");

        Assert.assertEquals(2, tesztCeg.getMegbizasok().size());
        Assert.assertEquals("teszt2",keresett.getAzonosito());
        Assert.assertEquals("masodikTesztProjekt",keresett.getFeladat());

    }
}