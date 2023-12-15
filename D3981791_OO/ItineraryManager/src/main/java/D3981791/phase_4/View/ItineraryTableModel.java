/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.View;

import D3981791.phase_4.Model.Itinerary;

import javax.swing.table.AbstractTableModel;
import java.util.List;

import static D3981791.phase_4.View.Format.formatItineraryCost;

/**
 *
 * @author d3981791
 */
public class ItineraryTableModel extends AbstractTableModel {

  private String[] columnNames = {"Ref", "Lead", "Attendees", "Activities", "Cost"};

  private List<Itinerary> itineraries;

  /**
   *
   * @param itineraries
   */
  public ItineraryTableModel(List<Itinerary> itineraries) {
    this.itineraries = itineraries;
  }

  @Override
  public int getRowCount() {
    return this.itineraries.size();
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

    Itinerary i = itineraries.get(rowIndex);

    switch (columnIndex) {
      case 0:
        return i.getRefNumber();
      case 1:
        return i.getLeadAttendeeFirstName().charAt(0) + " " + i.getLeadAttendeeLastName();
      case 2:
        return i.getTotalAttendees();
      case 3:
        return i.getTotalActivities();
      case 4:
        return formatItineraryCost(i);
      default:
        return null;
    }
  }
}