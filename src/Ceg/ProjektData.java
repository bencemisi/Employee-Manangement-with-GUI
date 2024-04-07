package Ceg;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProjektData extends AbstractTableModel {

    List<Projekt> projektek = new ArrayList<>();
    //Hány soros legyen a táblázat
    public int getRowCount() {
        return projektek.size();
    }

    //Hány oszlopos
    @Override
    public int getColumnCount() {
        return 3;
    }

    //Melyik oszlopban milyen érték szepeljen
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projekt projekt = projektek.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> projekt.getAzonosito();
            case 1 -> projekt.getFeladat();
            default -> projekt.getFelelos().getName();
        };

    }
    //Oszlopok nevei
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Azonosító";
            case 1 -> "Leírás";
            default -> "Felelős munkatárs";
        };
    }
    //Oszlopokban levő classok
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }
    //Változtatható értékű oszlopok
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }
    //Érték változtatása
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Projekt projekt = projektek.get(rowIndex);
        if (columnIndex == 1) {
            projekt.setFeladat((String) aValue);
        }
    }

}
