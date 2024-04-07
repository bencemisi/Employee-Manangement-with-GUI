package Ceg;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProjektFrame extends JFrame {

    private final ProjektData data;

    /*
     * Komponensek létrehozása és az ablakhoz adása
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);


        JPanel back = new JPanel();
        JButton button = new JButton("Vissza");
        button.addActionListener(new AddButtonListener());
        back.add(button);
        add(back, BorderLayout.SOUTH);

        table.setRowSorter(new TableRowSorter<>(table.getModel()));

    }
    //A gomb lenyomására bezáródik a táblázat
    final class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            dispose();

        }
    }
    //Tábzat felépítése
    public ProjektFrame( Ceg cegem) {
        super("Projekt nyilvántartás");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Projekt> projektek = new ArrayList<>(cegem.getMegbizasok());
        data = new ProjektData();
        data.projektek= projektek;

        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }
}
