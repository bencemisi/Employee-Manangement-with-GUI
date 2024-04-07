package Ceg;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class SeniorFrame extends JFrame {

    private final SeniorData data;
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
        JButton button = new JButton("Vissza");

        JPanel back = new JPanel();
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
    public SeniorFrame( Ceg cegem) {
        super("Senior nyilvántartás");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ArrayList<Senior> seniorok = new ArrayList<>();
        for(int i = 0; i<cegem.getAlkalmazottak().size();i++){
            if(cegem.getAlkalmazottak().get(i) instanceof Senior){
                seniorok.add((Senior) cegem.getAlkalmazottak().get(i));
            }
        }
        data = new SeniorData();
        data.seniorok= seniorok;


        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }
}
