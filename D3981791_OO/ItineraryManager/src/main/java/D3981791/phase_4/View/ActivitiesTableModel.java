/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.View;

import D3981791.phase_4.Model.Activity;
import D3981791.phase_4.Model.ActivityAddOn;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author d3981791
 */
public class ActivitiesTableModel extends AbstractTableModel {


  private String[] columnNames = {"Activites", "Add-Ons"};

  private List<Activity> selectedItineraryActivitiesList;

  /**
   *
   * @param selectedItineraryActivitiesList
   */
  public ActivitiesTableModel(List<Activity> selectedItineraryActivitiesList) {
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
   *
   * @param rowIndex        the row whose value is to be queried
   * @param columnIndex     the column whose value is to be queried
   * @return
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Activity a = selectedItineraryActivitiesList.get(rowIndex);
    StringBuilder listOfActivityAddOns = new StringBuilder();

    switch (columnIndex) {
      case 0:
        return a.getTitle() + " @ " + a.getTime();
      case 1:
        if(a.isThirdPartyInsurance()) {
          listOfActivityAddOns.append("* Third party insurance selected *, ");
        }
        if (a.getActivityAddOnsList().isEmpty()) {
          listOfActivityAddOns.append("** None **");
        } else {
          List<ActivityAddOn> addOnsList = a.getActivityAddOnsList();
          for (int i = 0; i < addOnsList.size(); i++) {
            ActivityAddOn activityAddOn = addOnsList.get(i);
            listOfActivityAddOns.append(activityAddOn.getName());
            if (i < addOnsList.size() - 1) {
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