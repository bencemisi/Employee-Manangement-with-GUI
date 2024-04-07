package Ceg;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

//A Cégnél elérhető posztok, az "Egyéb" később pontosítva lesz a MUNKAKÖR értékei közül valamelyikre
enum Poszt{
    Junior, Senior, Egyeb
        }

public class Menu extends JFrame {

        private final Ceg cegem;
   /*
   * A kezdőmenü felépítése, üdvözlő szöveg kirajzolása, és kezdés gomb elkészítése, majd elhelyezése
   * */
    public Menu(Ceg cegparam) {

        super("Cégnyilvántartó");
        cegem = cegparam;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new GridLayout(2, 1));

        JPanel kezdomenuUp = new JPanel(new BorderLayout(0, 0));
        JPanel kezdomenuDown = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 60));

        JButton startButton = new JButton("Kezdés");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));  // Kicsit nagyobb betűméret
        startButton.addActionListener(e -> {
            setJMenuBar(foMenu());
            kezdomenuUp.setVisible(false);
           kezdomenuDown.setVisible(false);
        });


        JLabel welcomeText = new JLabel("<html><u>Üdvözöllek a cégnyilvántartó programomban</u></html>");
        welcomeText.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);

        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK); // Vonal hozzáadása az alsó részhez
        welcomeText.setBorder(border);

        kezdomenuUp.add(welcomeText, BorderLayout.CENTER);
        kezdomenuDown.add(startButton, BorderLayout.SOUTH);

        add(kezdomenuUp);
        add(kezdomenuDown);
    }
    /*
    * Gyakornok és Junior előléptetésének kezelése. Lényege hogy a felhasználó egy ComboBoxból kiválasztja az előléptetni
    * kívánt munkatárs nevét, majd rákattint az előléptet gombra. Ezután a program visszatér a főmenübe.
    * */
    private void eloleptetes(String selectedPozicio) {
        switch (selectedPozicio) {
            case "Gyakornok":

                //Kiszedem az összes gyakornok nevét
                JComboBox<String> gyakornoknevek = new JComboBox<>();
                for (Dolgozok dolgozo : cegem.getAlkalmazottak()){
                    if(dolgozo instanceof Kisegito_szemelyzet){
                        if(((Kisegito_szemelyzet) dolgozo).getPozicio().equals(Munkakor.Gyakornok)){
                            gyakornoknevek.addItem(dolgozo.getName());
                        }

                    }
                }
                //Ha nincs gyakornok akkor nem történik semmi, visszatérek a főmenübe
                if(gyakornoknevek.getItemCount()==0) {
                    JOptionPane.showMessageDialog(this, "Nincs Gyakornok akit elő lehetne léptetni");
                    return;
                }

                //Ablak átalakítása
                getContentPane().removeAll();
                revalidate();
                repaint();

                JPanel gyakornokPanel = new JPanel();
                JLabel gyakornokLabel = new JLabel("Kérem válassza ki az előléptetni kívánt Gyakornok nevét:");


                JButton gyakornokEloleptetes = new JButton("Előléptetés");

                //Az előlépteteés elvégzése, olyan módon hogy az adott dolgozó gyakornokként eltávolítása, majd Junior-ként a
                // nyilvántartáshoz adása
                gyakornokEloleptetes.addActionListener(e -> {
                    String selectedGyakornok = (String) gyakornoknevek.getSelectedItem();
                    Kisegito_szemelyzet gyakornok = null;
                    for (Dolgozok dolgozo: cegem.getAlkalmazottak()) {

                            if(dolgozo.getName().equals(selectedGyakornok)){
                                gyakornok = new Kisegito_szemelyzet(dolgozo.getName(),dolgozo.getE_mail(),dolgozo.getMunkaber(),dolgozo.getItt_toltott_evek(),Munkakor.Gyakornok,false);
                                break;
                            }


                    }
                    //Ebben a függvényben történik a törlés és a hozzáadás
                    cegem.gyakornokEloleptetes(gyakornok);

                    //Főmenü visszaállítása
                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    JPanel placeholder1 = new JPanel();
                    JPanel placeholder2 = new JPanel();
                    add(placeholder1);
                    add(placeholder2);
                    foMenu();

                });

                gyakornokPanel.add(gyakornokLabel);
                gyakornokPanel.add(gyakornoknevek);
                gyakornokPanel.add(gyakornokEloleptetes);

                add(gyakornokPanel);
                pack();


                break;
            case "Junior":

                //Ugyanez elvégzése csak a Junior->Senior átmenettel

                JComboBox<String> juniornevek = new JComboBox<>();
                for (Dolgozok dolgozo : cegem.getAlkalmazottak()){
                    if(dolgozo instanceof Junior){
                        juniornevek.addItem(dolgozo.getName());
                    }
                }
                if(juniornevek.getItemCount()==0) {
                    JOptionPane.showMessageDialog(this, "Nincs Junior akit elő lehetne léptetni");
                    return;
                }

                getContentPane().removeAll();
                revalidate();
                repaint();

                JPanel juniorPanel = new JPanel();
                JLabel juniorLabel = new JLabel("Kérem válassza ki az előléptetni kívánt Junior nevét:");


                JButton eloleptetesgomb = new JButton("Előléptetés");

                eloleptetesgomb.addActionListener(e -> {
                    String selectedJunior = (String) juniornevek.getSelectedItem();
                    Junior junior = null;
                    for (Dolgozok dolgozo: cegem.getAlkalmazottak()) {
                            if(dolgozo.getName().equals(selectedJunior)){
                                junior = new Junior(dolgozo.getName(),dolgozo.getE_mail(),dolgozo.getMunkaber(),dolgozo.getItt_toltott_evek(),((Junior) dolgozo).getVegzettseg(),((Junior) dolgozo).getMentor());
                                break;
                            }
                    }
                    //Annak vizsgálata hogy valami hiba történt volna
                    if(junior == null){
                        return;
                    }
                    cegem.juniorEloleptetes(junior);

                    getContentPane().removeAll();
                    revalidate();
                    repaint();
                    JPanel placeholder1 = new JPanel();
                    JPanel placeholder2 = new JPanel();
                    add(placeholder1);
                    add(placeholder2);
                    foMenu();

                });

                juniorPanel.add(juniorLabel);
                juniorPanel.add(juniornevek);
                juniorPanel.add(eloleptetesgomb);

                add(juniorPanel);
                pack();


                break;
            default:
                //Nem történik semmi
                break;
        }
    }
/*
* Ez a függvény felelős a megfelelő JTable kiválasztásáért és annak megjelenítésért
* */
    private void pozicioTablaKivalasztasa(String selectedPozicio) {
        //
        switch (selectedPozicio) {
            case "Junior":
                JuniorFrame jf = new JuniorFrame(cegem);
                jf.setVisible(true);
                break;
            case "Senior":
                SeniorFrame sf = new SeniorFrame(cegem);
                sf.setVisible(true);
                break;
            case "Kisegítő személyzet":
                KisegitoFrame kf = new KisegitoFrame(cegem);
                kf.setVisible(true);
                break;
            default:
                // Semmi sem történik
                break;
        }
    }

    //A főmenü megrajzolása, gombok, menü, menüItemek hozzárendelése, azok funkcióinak implementálása
    private JMenuBar foMenu() {
        setSize(600, 400);
        setResizable(false);
        this.setLayout(new GridLayout(4,4));

        //Dolgozó adatainak megjelenítése JTable segítségével, ahol egyes értékek szerkeszthetőek is

        JButton dolgozokAdatai = new JButton("Dolgozók adatainak szerkesztése");
        dolgozokAdatai.addActionListener(e -> {
            // A felhasználó itt ki tudja választani kiknek az adatait szeretné látni
            String[] pozicioOptions = { "Junior", "Senior", "Kisegítő személyzet" };
            String kivalasztottPozicio = (String) JOptionPane.showInputDialog(
                    Menu.this,
                    "Válassz pozíciót:",
                    "Pozíció kiválasztása",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    pozicioOptions,
                    pozicioOptions[0]);

            // Ellenőrizése, hogy valóban kiválasztott-e valamit
            if (kivalasztottPozicio != null) {
                // A választás kezelése, megfelelő JTable felépítése és megjelenítése
                pozicioTablaKivalasztasa(kivalasztottPozicio);
            }
        });


        /*
        * Hasonlóan a projektek esetében, kivéve, hogy itt a felhasználónak nem kell választania
        * */
        JButton projektekAdatai = new JButton("Projektek szerkesztése");
        projektekAdatai.addActionListener(e -> {
            ProjektFrame pf = new ProjektFrame(cegem);
            pf.setVisible(true);

        });

/*
* Előléptetni Gyakornokot Junior-rá lehet illetve Junior-t Senior-rá
* A felhasználó hasonló módon tudja kiválasztani, hogy milyen poziciójú személyt szeretne előléptetni, mint amikor
* azt választja ki mely pozicióban levő dolgozók adatait szeretné látni
* */
        JButton juniorEloleptetese = new JButton("Dolgozó előléptetése");
        juniorEloleptetese.addActionListener(e -> {

        String[] pozicioOptions = { "Gyakornok", "Junior" };
        String selectedPozicio = (String) JOptionPane.showInputDialog(
                Menu.this,
                "Válassz pozíciót:",
                "Pozíció kiválasztása",
                JOptionPane.QUESTION_MESSAGE,
                null,
                pozicioOptions,
                pozicioOptions[0]);

        // Annak ellenőzése, hogy valóban kiválasztott e valamit a felhasználó
        if (selectedPozicio != null) {
            // A választás kezelése
            eloleptetes(selectedPozicio);

        }
    });
        /*
        * Ebben a nyilvántartásban csak Junior lehet lefokozni Gyakornokká. A funkció működése hasonló az előléptetéséhez
        * csak itt a ranglétrán lejjebb mozgatok dolgozót
        * */

        JButton lefokozas = new JButton("Junior lefokozása gyakornokká");
        lefokozas.addActionListener(e -> {

            JComboBox<String> juniornevek = new JComboBox<>();
            for (Dolgozok dolgozo : cegem.getAlkalmazottak()){
                if(dolgozo instanceof Junior){
                    juniornevek.addItem(dolgozo.getName());
                }
            }
            if(juniornevek.getItemCount()==0) {
                JOptionPane.showMessageDialog(this, "Nincs Junior akit vissza lehetne fokozni gyakornokká");
                return;
            }

            getContentPane().removeAll();
            revalidate();
            repaint();

            JPanel juniorPanel = new JPanel();
            JLabel juniorLabel = new JLabel("Kérem válassza ki az lefokozni kívánt Junior nevét:");


            JButton lefokozButton = new JButton("Lefokozás");

            lefokozButton.addActionListener(e1 -> {
                String selectedJunior = (String) juniornevek.getSelectedItem();
                Junior junior = null;
                for (Dolgozok dolgozo: cegem.getAlkalmazottak()) {
                        if(dolgozo.getName().equals(selectedJunior)){
                            junior = new Junior(dolgozo.getName(),dolgozo.getE_mail(),dolgozo.getMunkaber(),dolgozo.getItt_toltott_evek(),((Junior) dolgozo).getVegzettseg(),((Junior) dolgozo).getMentor());

                        }
                }
                //Hiba esetén ne történjen semmi
                if(junior == null){
                    return;
                }
                cegem.lefokoz(junior);

                getContentPane().removeAll();
                revalidate();
                repaint();
                JPanel placeholder1 = new JPanel();
                JPanel placeholder2 = new JPanel();
                add(placeholder1);
                add(placeholder2);
                foMenu();

            });
            juniorPanel.add(juniorLabel);
            juniorPanel.add(juniornevek);
            juniorPanel.add(lefokozButton);

            add(juniorPanel);
            pack();
        });


        //A cég elmentett adatainak betöltése
        JButton betoltes = new JButton("Adatok betöltése");
        betoltes.addActionListener(e -> cegem.load());

        //A cég adatainak mentése
        JButton mentes = new JButton("Mentés");
        mentes.addActionListener(e ->{
            cegem.mentes();
            JOptionPane.showMessageDialog(this, "A mentes sikeres.");
    });

        //Menüsáv felépítése, melyben a felhasználó képes lesz új dolgozók, projektek hozzáadására, meglévőek keresésére
        //és törlésére, illetve képes lesz visszalépni a főmenübe

        JMenuBar menuBar = new JMenuBar();

        //Evvel a gombbal a felhasználó akármikor visszaléphet a főmenübe
        JMenu vissza = new JMenu("Visszalépés");
        JMenuItem visszalepes = new JMenuItem("Vissza a főmenübe");
        visszalepes.addActionListener(e -> {
            getContentPane().removeAll();
            revalidate();
            repaint();
            JPanel placeholder1 = new JPanel();
            JPanel placeholder2 = new JPanel();
            add(placeholder1);
            add(placeholder2);
            foMenu();
            setVisible(true);
        });

        //Menüopciók, almenü hozzáadása
        JMenu dolgozok_menu = new JMenu("Dolgozók kezelése");
        JMenu projekt_menu = new JMenu("Projektek kezelése");
        JMenu ujDolgozoSubMenu = new JMenu("Új dolgozó felvétele");

        /*
        * Egy projekt és adatainak keresése a nyilvántartásban. A keresésre az azonosítót használom.
        * Ha nincs találat azt is jelzem, ha van akkor kiírom annak adatait.
        * */
        JMenuItem keresProjekt = new JMenuItem("Projekt keresése");
        keresProjekt.addActionListener(e -> {
            if(cegem.getMegbizasok().isEmpty()){
                JOptionPane.showMessageDialog(this,"A cégnek nincs jelenleg egyetlen projektje sem");
                return;
            }
            //Ablak átalakítása
            getContentPane().removeAll();
            revalidate();
            repaint();



            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("Kérem adja meg a keresni kívánt projekt azonosítóját:");
            JTextField nameField = new JTextField(15);
            JButton keresesbutton = new JButton("Keresés");

            keresesbutton.addActionListener(e14 -> {
                String keresettAzonosito = nameField.getText();

                Projekt eredmeny = cegem.munkat_keres(keresettAzonosito);
                if(eredmeny == null){
                    JOptionPane.showMessageDialog(keresesbutton,"Nincs ilyen azonosítójú projekt a nyilvántartásban");
                }
                else{
                    JOptionPane.showMessageDialog(keresesbutton,eredmeny.adatokListazasa());
                }
                getContentPane().removeAll();
                revalidate();
                repaint();
                JPanel placeholder1 = new JPanel();
                JPanel placeholder2 = new JPanel();
                add(placeholder1);
                add(placeholder2);
                foMenu();
            });
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            namePanel.add(keresesbutton);

            add(namePanel);
            pack();
        });
        /*
        * Meglévő projektazonosítók felsorolása egy ComboBoxban , ahonnan a felhasználó majd választani tud, melyiket szeretné törölni
        * */
        JMenuItem deleteProjekt = new JMenuItem("Projekt törlése a nyílvántartásból");
        deleteProjekt.addActionListener(e -> {
            //Ha nincs a Cegnek projektje akkor nem történik semmi
            if(cegem.getMegbizasok().isEmpty()){
                JOptionPane.showMessageDialog(this,"A cégnek nincs jelenleg egyetlen projektje sem");
                return;
            }
            //Ablak átalakítása
            getContentPane().removeAll();
            revalidate();
            repaint();



            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("Kérem válassza ki a törölni kívánt projekt azonosítóját:");
            JComboBox<String> projektekNevei = new JComboBox<>();
            for(int i = 0; i< cegem.getMegbizasok().size();i++){
                projektekNevei.addItem(cegem.getMegbizasok().get(i).getAzonosito());
            }

            JButton deleteButton = new JButton("Törlés");

            deleteButton.addActionListener(e12 -> {
                String adatok = (String) projektekNevei.getSelectedItem();
                cegem.munkat_lead(adatok);  //Törlés végrehajtása
                getContentPane().removeAll();
                revalidate();
                repaint();
                JPanel placeholder1 = new JPanel();
                JPanel placeholder2 = new JPanel();
                add(placeholder1);
                add(placeholder2);
                foMenu();
            });


            namePanel.add(nameLabel);
            namePanel.add(projektekNevei);
            namePanel.add(deleteButton);

            add(namePanel);
            pack();


        });

        JMenuItem keresDolgozo = new JMenuItem("Dolgozó keresése");
        keresDolgozo.addActionListener(e -> {
            if(cegem.getAlkalmazottak().isEmpty()){
                JOptionPane.showMessageDialog(this,"A cégnél nem dolgozik jelenleg senki");
                return;
            }
            //Ablak átalakítása
            getContentPane().removeAll();
            revalidate();
            repaint();



            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("Kérem adja meg a keresni kívánt dolgozó nevét:");
            JTextField nameField = new JTextField(15);
            JButton keresesbutton = new JButton("Keresés");

            keresesbutton.addActionListener(e14 -> {
                String keresettnev = nameField.getText();

                Dolgozok eredmeny = cegem.dolgozotKeres(keresettnev);
                if(eredmeny == null){
                    JOptionPane.showMessageDialog(keresesbutton,"Nincs ilyen nevű dolgozó a nyilvántartásban");
                }
                else{
                    JOptionPane.showMessageDialog(keresesbutton,eredmeny.Adatok_listazas());
                }
                getContentPane().removeAll();
                revalidate();
                repaint();
                JPanel placeholder1 = new JPanel();
                JPanel placeholder2 = new JPanel();
                add(placeholder1);
                add(placeholder2);
                foMenu();
            });
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            namePanel.add(keresesbutton);

            add(namePanel);
            pack();
        });

        //Dolgozó törlése. A dolgozót a neve és az email-címe alapján kiválasztással lehet.

        JMenuItem deleteDolgozo = new JMenuItem("Dolgozó törlése a nyílvántartásból");

        deleteDolgozo.addActionListener(e -> {
            if(cegem.getAlkalmazottak().isEmpty()){
                JOptionPane.showMessageDialog(this,"A cégnél nem dolgozik jelenleg senki");
                return;
            }
            getContentPane().removeAll();
            revalidate();
            repaint();



            JPanel namePanel = new JPanel();
            JLabel nameLabel = new JLabel("Kérem válassza ki a törölni kívánt kolléga nevét:");
            JComboBox<String> dolgozokNevei = new JComboBox<>();
            for(int i = 0; i< cegem.getAlkalmazottak().size();i++){
                dolgozokNevei.addItem(cegem.getAlkalmazottak().get(i).getName()+","+cegem.getAlkalmazottak().get(i).getE_mail());
            }

            JButton deleteButton = new JButton("Törlés");

            deleteButton.addActionListener(e13 -> {
                String[] adatok = Objects.requireNonNull(dolgozokNevei.getSelectedItem()).toString().split(",");
                cegem.dolgozot_kirug(adatok[0], adatok[1]);//Törlés végrehajtása
                getContentPane().removeAll();
                revalidate();
                repaint();
                JPanel placeholder1 = new JPanel();
                JPanel placeholder2 = new JPanel();
                add(placeholder1);
                add(placeholder2);
                foMenu();
            });


            namePanel.add(nameLabel);
            namePanel.add(dolgozokNevei);
            namePanel.add(deleteButton);

            add(namePanel);
            pack();


        });
        /*
        * Uj dolgozó hozzáadásánál, a kellő adatokat egymás után kérem be, közben a grafikus felületet alakítva, mert
        * a különböző adatokat különböző módszerekkel kérem be a felhasználótól. (Pl a nevét egy TextField-ből nyerem ki,
        * míg azt, hogy éjjeli műszakos-e például a portás egy ChechBox-ből. Emellett használok még többek között Combobox-ot is.
        * Minden egyes attribútumot külön függvényben kérek be.Így ezek egymásba vannak ágyazva.
        * */

        /*
        * Junior hozzáadása a alkalmazottak listájához
        * */
        JMenuItem juniorItem = new JMenuItem("Junior");
        juniorItem.addActionListener(e -> {

            //Minden Juniorhoz kötelező módon rendelki kell egy mentoráló Senior kollégát, amennyiben nincs ilyen
            //beosztású alkalmazott Juniort nem lehet felvenni
            ArrayList<Senior> seniorok = new ArrayList<>();
            for(int i = 0; i<cegem.getAlkalmazottak().size();i++){
                if(cegem.getAlkalmazottak().get(i) instanceof Senior){
                    seniorok.add((Senior) cegem.getAlkalmazottak().get(i));
                }
            }
            if(seniorok.isEmpty()){
                JOptionPane.showMessageDialog(this, "Minden Juniornak kell egy mentoráló Senior, de jelenleg nincs Senior poziciójú kolléga");
                return;
            }
            Poszt poszt = Poszt.Junior;

            //Junior adatainak bekérése név, email, munkabér, ledolgozott évek, iskola(amennyiben nem a cégnél volt gyakornok), illetve a
            //mentoráló Senior sorrendjében
            ujDolgozoNeve(poszt);
        });

        JMenuItem seniorItem = new JMenuItem("Senior");
        seniorItem.addActionListener(e -> {
            Poszt poszt = Poszt.Senior;
            //Senior adatainak bekérése név, email, munkabér, ledolgozott évek, seniorként töltött évek és mentor munkája sorrendjében
            ujDolgozoNeve(poszt);
        });

        JMenuItem egyebItem = new JMenuItem("Egyéb");
        egyebItem.addActionListener(e -> {
            Poszt poszt = Poszt.Egyeb;
            //Kisegítő személyzet adatainak bekérése név, email, munkabér, ledolgozott évek, munkakör, éjjeli műszakság sorrendjében
            ujDolgozoNeve(poszt);
        });

        ujDolgozoSubMenu.add(juniorItem);
        ujDolgozoSubMenu.add(seniorItem);
        ujDolgozoSubMenu.add(egyebItem);

        //Új projekt felvétele funkció
        JMenuItem ujProjektItem = new JMenuItem("Új projekt felvétele");
        ujProjektItem.addActionListener(e -> {
            //Ha a cégnél nem dolgozik senki akkor nem lehet új munkát elvállalni
            if(cegem.getAlkalmazottak().isEmpty()){
                JOptionPane.showMessageDialog(this,"Nincs dolgozó, akik a munka felelőse tudna lenni");
                return;
            }

            //Projekt adatainak bekérése az azonosító,leírás, és felelős soreendjében
           ujProjektAzonositoja();
        });
        add(dolgozokAdatai);
        add(projektekAdatai);
        add(juniorEloleptetese);
        add(lefokozas);
        add(betoltes);
        add(mentes);

        dolgozok_menu.add(ujDolgozoSubMenu);
        dolgozok_menu.add(keresDolgozo);
        dolgozok_menu.add(deleteDolgozo);
        projekt_menu.add(ujProjektItem);
        projekt_menu.add(keresProjekt);
        projekt_menu.add(deleteProjekt);
        vissza.add(visszalepes);

        menuBar.add(projekt_menu);
        menuBar.add(dolgozok_menu);
        menuBar.add(vissza);

        return menuBar;
    }


    //Innentől szerepelnek az adatbekérő függvényeim, melyek a grafikus felületet is átalakítják.
    private void ujDolgozoNeve(Poszt poszt) {
        getContentPane().removeAll();
        revalidate();
        repaint();



        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Kérem adja meg a felvenni kívánt kolléga nevét:");
        JTextField nameField = new JTextField(20);
        JButton continueButton = new JButton("Tovább");

        continueButton.addActionListener(e -> {
            String dolgozoName = nameField.getText();
            ujDolgozoEmaile(poszt,dolgozoName);

        });

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(continueButton);

        add(namePanel);
        pack();
    }
    private void ujDolgozoEmaile(Poszt poszt, String name) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel emailPanel = new JPanel();
        JLabel emailLabel = new JLabel("Adja meg az e-mail címét:");
        JTextField emailField = new JTextField(20);
        JButton submitButton = new JButton("Tovább");

        submitButton.addActionListener(e -> {
            String email = emailField.getText();
            ujDolgozoFizetese(poszt, name, email);
        });

        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        emailPanel.add(submitButton);

        add(emailPanel);
        pack();
    }
    private  void ujDolgozoFizetese(Poszt poszt, String name, String email) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel fizetesPanel = new JPanel();
        JLabel salaryLabel = new JLabel("Kérem adja meg a dolgozó munkabérét(Ft):");
        JTextField salaryField = new JTextField(20);
        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            String salaryText = salaryField.getText();
            try {
                int salary = Integer.parseInt(salaryText);
                if (salary > 0) {
                    ujDolgozoCegnelToltottIdeje(poszt,name,email,salary);

                } else {
                    JOptionPane.showMessageDialog(this, "A munkabérnek nagyobbnak kell lennie, mint 0.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Érvénytelen munkabér formátum.");
            }
        });

        fizetesPanel.add(salaryLabel);
        fizetesPanel.add(salaryField);
        fizetesPanel.add(submitButton);

        add(fizetesPanel);
        pack();
    }
    private  void ujDolgozoCegnelToltottIdeje(Poszt poszt, String name, String email, int fizetes) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel ledolgozottEvekPanel = new JPanel();
        JLabel ledolgozottEvekLabel = new JLabel("Kérem adja meg mióta dolgozik a cégnél a munkatárs (években):");
        JTextField ledolgozottEvekField = new JTextField(20);
        JButton ledolgozottEvekButton = new JButton("Beküldés");

        ledolgozottEvekButton.addActionListener(e -> {
            String ledolgozottEvekText = ledolgozottEvekField.getText();
            try {
                int tenure = Integer.parseInt(ledolgozottEvekText);
                if (tenure > 0) {
                    if(poszt == Poszt.Junior)
                    {
                        ujJuniorIskolaja( name, email, fizetes,tenure);
                    } else if (poszt == Poszt.Senior) {
                        ujDolgozoSeniorkentToltottIdeje(name,email,fizetes,tenure);

                    }else{
                        ujKisegMunkatarsMunkakore(name,email,fizetes,tenure);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "A munkaviszonynak nagyobbnak kell lennie, mint 0 években.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Érvénytelen munkaviszony formátum.");
            }
        });

        ledolgozottEvekPanel.add(ledolgozottEvekLabel);
        ledolgozottEvekPanel.add(ledolgozottEvekField);
        ledolgozottEvekPanel.add(ledolgozottEvekButton);

        add(ledolgozottEvekPanel);
        pack();
    }
    private   void ujDolgozoSeniorkentToltottIdeje(String name, String email, int salary, int tenure) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel seniorTenurePanel = new JPanel();
        JLabel seniorTenureLabel = new JLabel("Kérem adja meg mióta dolgozik Senior munkakörben a cégnél a munkatárs (években):");
        JTextField seniorTenureField = new JTextField(20);
        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            String seniorTenureText = seniorTenureField.getText();
            try {
                int seniorTenure = Integer.parseInt(seniorTenureText);
                if (seniorTenure > 0) {
                    ujSeniorMentoral_e(name,email,salary,tenure,seniorTenure);
                } else {
                    JOptionPane.showMessageDialog(this, "A Senior munkaviszonynak nagyobbnak kell lennie, mint 0 években.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Érvénytelen Senior munkaviszony formátum.");
            }
        });

        seniorTenurePanel.add(seniorTenureLabel);
        seniorTenurePanel.add(seniorTenureField);
        seniorTenurePanel.add(submitButton);

        add(seniorTenurePanel);
        pack();
    }
    private  void ujSeniorMentoral_e(String name, String email, int salary, int tenure, int seniorTenure) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel mentorPanel = new JPanel();
        JLabel mentorLabel = new JLabel("Kérem adja meg, hogy mentorál-e az alkalmazott :");
        JCheckBox mentorCheckBox = new JCheckBox("Mentoral"); // Módosított, hogy a bevitelt egy szövegmezőbe tegye
        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {

                boolean mentor = mentorCheckBox.isSelected();
                Senior uj = new Senior(name, email, salary, tenure, seniorTenure, mentor);
                cegem.dolgozot_felvesz(uj);
                getContentPane().removeAll();
                revalidate();
                repaint();

            JPanel placeholder2 = new JPanel();
            add(placeholder2);
            JPanel placeholder1 = new JPanel();
            add(placeholder1);

                foMenu();

        });

        mentorPanel.add(mentorLabel);
        mentorPanel.add(mentorCheckBox);
        mentorPanel.add(submitButton);

        add(mentorPanel);
        pack();
    }
    private void ujJuniorIskolaja( String name, String email, int salary, int tenure) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel schoolPanel = new JPanel();
        JLabel schoolLabel = new JLabel("Kérem adja meg melyik iskolában végzett:");
        JTextField schoolField = new JTextField(20);
        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            String school = schoolField.getText();
            ujJuniorMentora(name,email,salary,tenure,school);
        });

        schoolPanel.add(schoolLabel);
        schoolPanel.add(schoolField);
        schoolPanel.add(submitButton);

        add(schoolPanel);
        pack();
    }
    private void ujJuniorMentora(String name, String email, int salary, int tenure, String school) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel mentorPanel = new JPanel();
        JLabel mentorLabel = new JLabel("Kérem válassza ki a Mentor személyét:");

        JComboBox<String> mentorComboBox = new JComboBox<>();
       for (Dolgozok dolgozo : cegem.getAlkalmazottak()){
           if(dolgozo instanceof Senior){
               mentorComboBox.addItem(dolgozo.getName());
           }
       }
       if(mentorComboBox.getItemCount()==0){

           getContentPane().removeAll();
           revalidate();
           repaint();
           JPanel placeholder1 = new JPanel();
           JPanel placeholder2 = new JPanel();
           add(placeholder1);
           add(placeholder2);
           foMenu();
           return;
       }
        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            String selectedMentor = (String) mentorComboBox.getSelectedItem();
            Senior mentor = null;
            for (Dolgozok dolgozo: cegem.getAlkalmazottak()) {
               // if(dolgozo instanceof Senior){
                    if(dolgozo.getName().equals(selectedMentor)){
                         mentor = new Senior(dolgozo.getName(),dolgozo.getE_mail(),dolgozo.getMunkaber(),dolgozo.getItt_toltott_evek(),((Senior) dolgozo).getMiotaSenior(),((Senior) dolgozo).mentoral_e());
                        break;
                    }

               // }
            }

            Junior uj = new Junior(name, email, salary, tenure, school, mentor);

            getContentPane().removeAll();
            revalidate();
            repaint();
            cegem.dolgozot_felvesz(uj);
            JPanel placeholder1 = new JPanel();
            add(placeholder1);
            JPanel placeholder2 = new JPanel();
            add(placeholder2);
            foMenu();

        });

        mentorPanel.add(mentorLabel);
        mentorPanel.add(mentorComboBox);
        mentorPanel.add(submitButton);

        add(mentorPanel);
        pack();
    }
    private void ujKisegMunkatarsMunkakore( String name, String email, int salary, int tenure) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel munkakorPanel = new JPanel();
        JLabel munkakorLabel = new JLabel("Kérem válassza ki a munkakört:");

        JComboBox<Munkakor> munkakorComboBox = new JComboBox<>(Munkakor.values());

        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            Munkakor selectedMunkakor = (Munkakor) munkakorComboBox.getSelectedItem();
            ujKisegMunkatarsMuszakja(name,email,salary,tenure,selectedMunkakor);

        });

        munkakorPanel.add(munkakorLabel);
        munkakorPanel.add(munkakorComboBox);
        munkakorPanel.add(submitButton);

        add(munkakorPanel);
        pack();
    }
    private void ujKisegMunkatarsMuszakja( String name, String email, int salary, int tenure, Munkakor munkakor) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel timePanel = new JPanel();
        JLabel timeLabel = new JLabel("Kérem válassza ki, hogy ejjel vagy nappal dolgozik:");

        JCheckBox nightShiftCheckBox = new JCheckBox("Ejjel dolgozik");

        JButton submitButton = new JButton("Beküldés");

        submitButton.addActionListener(e -> {
            boolean nightShift = nightShiftCheckBox.isSelected();
            Kisegito_szemelyzet uj = new Kisegito_szemelyzet(name, email, salary, tenure, munkakor, nightShift);
            cegem.dolgozot_felvesz(uj);
            getContentPane().removeAll();
            revalidate();
            repaint();
            JPanel placeholder2 = new JPanel();
            JPanel placeholder1 = new JPanel();
            add(placeholder1);
            add(placeholder2);
            foMenu();

        });

        timePanel.add(timeLabel);
        timePanel.add(nightShiftCheckBox);
        timePanel.add(submitButton);

        add(timePanel);
        pack();
    }
    public void ujProjektAzonositoja(){
        getContentPane().removeAll();
        revalidate();
        repaint();



        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Kérem adja meg a felvenni kívánt projekt azonosítóját:");
        JTextField nameField = new JTextField(20);
        JButton continueButton = new JButton("Tovább");

        continueButton.addActionListener(e -> {
            String azonosito = nameField.getText();
            ujProjektLeirasa(azonosito);

        });

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(continueButton);

        add(namePanel);
        pack();
    }
    public void ujProjektLeirasa(String azonosito){
        getContentPane().removeAll();
        revalidate();
        repaint();



        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Kérem adja meg a felvenni kívánt projekt rövid leírását:");
        JTextField nameField = new JTextField(50);
        JButton continueButton = new JButton("Rögzítés");

        continueButton.addActionListener(e -> {
            String leiras = nameField.getText();
            ujProjektFelelose(azonosito,leiras);

        });

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.add(continueButton);

        add(namePanel);
        pack();
    }
    public void ujProjektFelelose(String azonosito, String leiras){
        getContentPane().removeAll();
        revalidate();
        repaint();

        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Kérem válassza ki a projekt felelősét");

        JButton continueButton = new JButton("Tovább");


        JComboBox<String> dolgozok_nevei = new JComboBox<>();
        for(int i = 0; i< cegem.getAlkalmazottak().size();i++){
            dolgozok_nevei.addItem(cegem.getAlkalmazottak().get(i).getName());
        }

        continueButton.addActionListener(e -> {
            String felelos_neve = (String) dolgozok_nevei.getSelectedItem();
            Dolgozok felelos = null;
            for(int i = 0; i< cegem.getAlkalmazottak().size();i++){
                if(cegem.getAlkalmazottak().get(i).getName().equals(felelos_neve)){
                    felelos = cegem.getAlkalmazottak().get(i);
                }
            }
            Projekt uj = new Projekt(azonosito,leiras,felelos);
            cegem.munkat_felvesz(uj);
            getContentPane().removeAll();
            revalidate();
            repaint();
            JPanel placeholder1 = new JPanel();
            JPanel placeholder2 = new JPanel();
            add(placeholder1);
            add(placeholder2);
            foMenu();

        });

        namePanel.add(nameLabel);
        namePanel.add(dolgozok_nevei);
        namePanel.add(continueButton);

        add(namePanel);
        pack();
    }
}



