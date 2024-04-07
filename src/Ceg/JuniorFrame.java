package Ceg;

import javax.swing.*;

import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
public class JuniorFrame extends JFrame {

    private final JuniorData data;


    /*
     * Komponensek létrehozása és az ablakhoz adása
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        table.setFillsViewportHeight(true);


        JButton button = new JButton("Vissza");

        JPanel back = new JPanel();
        //A gomb lenyomására bezáródik a táblázat
        button.addActionListener(e1 -> dispose());
        back.add(button);
        add(back, BorderLayout.SOUTH);

        table.setRowSorter(new TableRowSorter<>(table.getModel()));

    }



    //Tábzat felépítése
    public JuniorFrame( Ceg cegem) {
        super("Junior nyilvántartás");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Junior> juniorok = new ArrayList<>();
        for(int i = 0; i<cegem.getAlkalmazottak().size();i++){
            if(cegem.getAlkalmazottak().get(i) instanceof Junior){
                juniorok.add((Junior) cegem.getAlkalmazottak().get(i));
            }
        }
        data = new JuniorData();
        data.junorok= juniorok;


        // Felépítem az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }
}
