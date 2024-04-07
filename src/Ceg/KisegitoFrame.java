package Ceg;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class KisegitoFrame extends JFrame {


    private final KisegitoData data;
    /*
     * Komponensek létrehozása és az ablakhoz adása
     * (táblázat, beviteli mező, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JTable table = new JTable(data);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton button = new JButton("Vissza");
        JPanel back = new JPanel();
        add(scrollPane, BorderLayout.CENTER);
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
    public KisegitoFrame( Ceg cegem) {
        super("Kisegítő személyzeti nyilvántartás");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Kisegito_szemelyzet> kisegitok = new ArrayList<>();
        for(int i = 0; i<cegem.getAlkalmazottak().size();i++){
            if(cegem.getAlkalmazottak().get(i) instanceof Kisegito_szemelyzet){
                kisegitok.add((Kisegito_szemelyzet) cegem.getAlkalmazottak().get(i));
            }
        }
        data = new KisegitoData();
        data.kisegitok= kisegitok;


        // Fel�p�tj�k az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }
}
