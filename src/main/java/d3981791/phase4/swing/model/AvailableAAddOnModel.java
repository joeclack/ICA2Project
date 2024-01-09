package d3981791.phase4.swing.model;

import d3981791.phase1.model.ActivityAddOn;
import d3981791.phase4.swing.model.Format;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AvailableAAddOnModel extends AbstractTableModel {

    private final String[] columnNames = {"Name", "Base Cost"};

    private final List<ActivityAddOn> activityAddOns;

    public AvailableAAddOnModel(List<ActivityAddOn> activityAddOns) {
        this.activityAddOns = activityAddOns;
    }

    @Override
    public int getRowCount() {
        return this.activityAddOns.size();
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
        ActivityAddOn a = activityAddOns.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getName();
            case 1:
                return Format.formatCost(a.getBaseCost());
            default:
                return null;
        }
    }

}