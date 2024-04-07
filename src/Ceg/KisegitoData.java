package Ceg;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class KisegitoData extends AbstractTableModel {

    List<Kisegito_szemelyzet> kisegitok = new ArrayList<>();
    //Hány soros legyen a táblázat
    public int getRowCount() {
        return kisegitok.size();
    }

    //Hány oszlopos
    @Override
    public int getColumnCount() {
        return 6;
    }

    //Melyik oszlopban milyen érték szepeljen
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kisegito_szemelyzet kisegito = kisegitok.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> kisegito.getName();
            case 1 -> kisegito.getE_mail();
            case 2 -> kisegito.getMunkaber();
            case 3 -> kisegito.getItt_toltott_evek();
            case 4 -> kisegito.getPozicio();
            default -> kisegito.isEjjeli();
        };

    }
    //Oszlopok nevei
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Név";
            case 1 -> "E-mail";
            case 2 -> "Munkabér";
            case 3 -> "Évek";
            case 4 -> "Pozíció";
            default -> "Éjjeli Műszak";
        };
    }
    //Oszlopokban levő classok
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1 -> String.class;
            case 2, 3 -> Integer.class;
            case 4 -> Munkakor.class;
            default -> Boolean.class;
        };
    }
    //Változtatható értékű oszlopok
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 3 || columnIndex == 5;
    }
    //Érték változtatása
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Kisegito_szemelyzet kisegito = kisegitok.get(rowIndex);
        if(columnIndex == 2){
            kisegito.setBer((Integer) aValue);
        }else if(columnIndex == 3){
            kisegito.setItt_toltott_evek((Integer) aValue);
        }else if(columnIndex == 5){
            kisegito.setEjjeli((Boolean) aValue);
        }
    }

}
