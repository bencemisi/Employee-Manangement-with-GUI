package Ceg;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class JuniorData extends AbstractTableModel {

    List<Junior> junorok = new ArrayList<>();
    //Hány soros legyen a táblázat
    public int getRowCount() {
        return junorok.size();
    }
    //Hány oszlopos
    @Override
    public int getColumnCount() {
        return 6;
    }

    //Melyik oszlopban milyen érték szepeljen
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Junior junior = junorok.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> junior.getName();
            case 1 -> junior.getE_mail();
            case 2 -> junior.getMunkaber();
            case 3 -> junior.getItt_toltott_evek();
            case 4 -> junior.getVegzettseg();
            default -> junior.getMentor().getName();
        };

    }
    //Oszlopok nevei
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Név";
            case 1 -> "E-mail";
            case 2 -> "Munkabér";
            case 3 -> "Évek";
            case 4 -> "Iskola";
            default -> "Mentor neve";
        };
    }
    //Oszlopokban levő classok
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2, 3 -> Integer.class;
            default -> String.class;
        };
    }
    //Változtatható értékű oszlopok
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 3;
    }
    //Érték változtatása
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Junior junior = junorok.get(rowIndex);
        if(columnIndex == 2){
            junior.setBer((Integer) aValue);
        }else if(columnIndex == 3){
            junior.setItt_toltott_evek((Integer) aValue);
        }
    }

}
