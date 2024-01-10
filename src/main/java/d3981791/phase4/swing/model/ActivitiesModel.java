/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4.swing.model;

import d3981791.phase1.model.Activity;
import d3981791.phase1.model.ActivityAddOn;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * The table model for the activities table
 */
public class ActivitiesModel extends AbstractTableModel {

    private final String[] columnNames = {"Activities", "Add-Ons"};

    private final List<Activity> selectedItineraryActivitiesList;

    public ActivitiesModel(List<Activity> selectedItineraryActivitiesList) {
        this.selectedItineraryActivitiesList = selectedItineraryActivitiesList;
    }

    @Override
    public int getRowCount() {
        return this.selectedItineraryActivitiesList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
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
        // Get the activity at the row index
        Activity a = selectedItineraryActivitiesList.get(rowIndex);
        StringBuilder listOfActivityAddOns = new StringBuilder();

        switch (columnIndex) {
            case 0:
                // Return the activity title and time
                return a.getTitle() + " @ " + a.getTime();
            case 1:
                if (a.isThirdPartyInsurance()) {
                    // If third party insurance is selected, append it to the string
                    listOfActivityAddOns.append("* Third party insurance selected *, ");
                }
                if (a.getActivityAddOnsList().isEmpty()) {
                    // If there are no add-ons, append None
                    listOfActivityAddOns.append("** None **");
                } else {
                    // Loop through the list of add-ons and append them to the string
                    List<ActivityAddOn> addOnsList = a.getActivityAddOnsList();
                    for (int i = 0; i < addOnsList.size(); i++) {
                        ActivityAddOn activityAddOn = addOnsList.get(i);
                        listOfActivityAddOns.append(activityAddOn.getName());
                        if (i < addOnsList.size() - 1) {
                            // If it is not the last item in the list, append a comma
                            listOfActivityAddOns.append(", ");
                        }
                    }
                }
                return listOfActivityAddOns;
            default:
                return null;
        }
    }

}