package D3981791.phase_4.GUI.Models;

import D3981791.phase_1.Model.ItineraryAddOn;
import D3981791.phase_4.GUI.UI.Format;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PreBuiltItineraryAddOnsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name", "Base Cost"};

    private final List<ItineraryAddOn> itineraryAddOns;

    public PreBuiltItineraryAddOnsTableModel(List<ItineraryAddOn> itineraryAddOns) {
        this.itineraryAddOns = itineraryAddOns;
    }

    @Override
    public int getRowCount() {
        return itineraryAddOns.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        ItineraryAddOn i = itineraryAddOns.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return i.getName();
            case 1:
                return Format.formatCost(i.getBaseCost());
            default:
                return null;
        }
    }

}