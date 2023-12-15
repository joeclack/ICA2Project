/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.View;

import D3981791.phase_4.Model.ItineraryAddOn;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 *
 * @author d3981791
 */
public class ItineraryAddOnTableModel extends AbstractTableModel {

  private String[] columnNames = {"Itinerary Add-Ons"};

  private List<ItineraryAddOn> selectedItineraryAddOnsList;

  /**
   *
   * @param selectedItineraryAddOnsList
   */
  public ItineraryAddOnTableModel(List<ItineraryAddOn> selectedItineraryAddOnsList) {
    this.selectedItineraryAddOnsList = selectedItineraryAddOnsList;
  }

  @Override
  public int getRowCount() {
    return this.selectedItineraryAddOnsList.size();
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
   *
   * @param rowIndex        the row whose value is to be queried
   * @param columnIndex     the column whose value is to be queried
   * @return
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ItineraryAddOn i = selectedItineraryAddOnsList.get(rowIndex);

    switch (columnIndex) {
      case 0:
        return i.getName();
      default:
        return null;
    }
  }

}