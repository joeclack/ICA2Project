package d3981791.phase_4.experimental;

import d3981791.phase_1.model.Activity;
import d3981791.phase_4.swing.model.Format;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AvailableActivitiesModel extends AbstractTableModel {

    private final String[] columnNames = {"Title", "Description", "Location", "Expected Duration", "Insurance Required", "Base Cost"};

    private final List<Activity> activities;

    public AvailableActivitiesModel(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public int getRowCount() {
        return this.activities.size();
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

        Activity a = activities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getTitle();
            case 1:
                return a.getDescription();
            case 2:
                return a.getLocation();
            case 3:
                return a.getExpectedDuration();
            case 4:
                return a.isRequireInsurance();
            case 5:
                return Format.formatCost(a.getBaseCost());
            default:
                return null;
        }
    }

}