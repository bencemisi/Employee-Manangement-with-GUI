package Ceg;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SeniorData extends AbstractTableModel {

    List<Senior> seniorok = new ArrayList<>();
    //Hány soros legyen a táblázat
    public int getRowCount() {
        return seniorok.size();
    }

    //Hány oszlopos
    @Override
    public int getColumnCount() {
        return 6;
    }

    //Melyik oszlopban milyen érték szepeljen
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Senior senior = seniorok.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> senior.getName();
            case 1 -> senior.getE_mail();
            case 2 -> senior.getMunkaber();
            case 3 -> senior.getItt_toltott_evek();
            case 4 -> senior.getMiotaSenior();
            default -> senior.mentoral_e();
        };

    }
    //Oszlopok nevei
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Név";
            case 1 -> "E-mail";
            case 2 -> "Munkabér";
            case 3 -> "Évek";
            case 4 -> "Senior Évek";
            default -> "Mentorál";
        };
    }
    //Oszlopokban levő classok
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1 -> String.class;
            case 2, 3, 4 -> Integer.class;
            default -> Boolean.class;
        };
    }
    //Változtatható értékű oszlopok
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2 || columnIndex == 3 || columnIndex == 4 || columnIndex == 5;
    }
    //Érték változtatása
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Senior senior = seniorok.get(rowIndex);
        if(columnIndex == 2){
            senior.setBer((Integer) aValue);
        }else if(columnIndex == 3){
            senior.setItt_toltott_evek((Integer) aValue);
        }else if(columnIndex == 4){
            senior.setMiotaSenior((Integer) aValue);
        }else if(columnIndex == 5){
            senior.setMentoral((Boolean) aValue);
        }
    }

}
