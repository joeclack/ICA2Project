/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.GUI.Models;

import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.ItineraryAddOn;
import D3981791.phase_4.GUI.UI.Format;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class SelectedItineraryAddOnModel extends AbstractTableModel {

  private final String[] columnNames = {"Itinerary Add-Ons", "Cost"};

  private final List<ItineraryAddOn> selectedItineraryAddOnsList;

  private final Itinerary itinerary;

  public SelectedItineraryAddOnModel(List<ItineraryAddOn> selectedItineraryAddOnsList, Itinerary itinerary) {
    this.selectedItineraryAddOnsList = selectedItineraryAddOnsList;
    this.itinerary = itinerary;
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
   */
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ItineraryAddOn i = selectedItineraryAddOnsList.get(rowIndex);
    Itinerary itinerary = this.itinerary;

      switch(columnIndex) {
        case 0:
          return i.getName();
        case 1:
          return Format.formatCost(i.getBaseCost()*itinerary.getTotalAttendees());
        default:
          return null;
      }
  }

}