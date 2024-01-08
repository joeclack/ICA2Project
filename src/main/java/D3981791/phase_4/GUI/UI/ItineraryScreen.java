/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.GUI.UI;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_3.Model.SaveItinerary;
import D3981791.phase_4.GUI.Models.ActivitiesModel;
import D3981791.phase_4.GUI.Models.SelectedItineraryAddOnModel;
import D3981791.phase_4.GUI.Models.ItineraryListModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static D3981791.phase_4.GUI.UI.Format.formatCost;

/**
 * The itinerary screen
 */
public class ItineraryScreen extends JFrame {

  /**
   * Creates the itinerary screen
   * @param selectedItinerary the itinerary to display
   */
  public ItineraryScreen(Itinerary selectedItinerary, List<Itinerary> itineraries, JTable managementTable) {
    super();

    JPanel infoFormPanel = new JPanel();
    infoFormPanel.setLayout(new GridLayout(4, 2));


    JLabel leadAttendeeLabel = new JLabel("Lead Attendee: ");
    JLabel totalAttendeesLabel = new JLabel("Total Attendees: ");
    JLabel totalCostLabel = new JLabel("Total Cost: ");
    JLabel dateLabel = new JLabel("Date: ");

    JTextField leadAttendeeField = new JTextField(selectedItinerary.getLeadAttendeeFirstName() + " " + selectedItinerary.getLeadAttendeeLastName());
    leadAttendeeField.setEditable(false);
    JTextField totalAttendeesField = new JTextField(String.valueOf(selectedItinerary.getTotalAttendees()));
    totalAttendeesField.setEditable(false);
    JTextField totalCostField = new JTextField(formatCost(selectedItinerary.getItineraryCost()));
    totalCostField.setEditable(false);
    JTextField dateField = new JTextField(String.valueOf(selectedItinerary.getDate()));
    dateField.setEditable(false);

    infoFormPanel.add(leadAttendeeLabel);
    infoFormPanel.add(leadAttendeeField);
    infoFormPanel.add(totalAttendeesLabel);
    infoFormPanel.add(totalAttendeesField);
    infoFormPanel.add(totalCostLabel);
    infoFormPanel.add(totalCostField);
    infoFormPanel.add(dateLabel);
    infoFormPanel.add(dateField);

    JTable itineraryAddOnsTable = new JTable();
    itineraryAddOnsTable.setBackground(Color.LIGHT_GRAY);
    itineraryAddOnsTable.setGridColor(Color.DARK_GRAY);
    itineraryAddOnsTable.setSelectionBackground(new Color(192, 217, 237));
    itineraryAddOnsTable.getTableHeader().setReorderingAllowed(false);

    JTableHeader addOnHeader = itineraryAddOnsTable.getTableHeader();
    addOnHeader.setBackground(Color.DARK_GRAY);
    addOnHeader.setForeground(Color.white);

    JTable activitiesTable = new JTable();
    activitiesTable.setBackground(Color.LIGHT_GRAY);
    activitiesTable.setGridColor(Color.DARK_GRAY);
    activitiesTable.setSelectionBackground(new Color(192, 217, 237));
    activitiesTable.getTableHeader().setReorderingAllowed(false);
    
    JTableHeader activityHeader = activitiesTable.getTableHeader();
    activityHeader.setBackground(Color.DARK_GRAY);
    activityHeader.setForeground(Color.white);

    itineraryAddOnsTable.setModel(new SelectedItineraryAddOnModel(selectedItinerary.getItineraryAddOnsList(), selectedItinerary));
    JScrollPane scrollPane1 = new JScrollPane(itineraryAddOnsTable);

    activitiesTable.setModel(new ActivitiesModel(selectedItinerary.getActivitiesList()));
    JScrollPane scrollPane2 = new JScrollPane(activitiesTable);

    scrollPane1.setPreferredSize(new Dimension(380, 200));
    scrollPane2.setPreferredSize(new Dimension(380, 200));

    itineraryAddOnsTable.setPreferredScrollableViewportSize(new Dimension(360, 180));
    activitiesTable.setPreferredScrollableViewportSize(new Dimension(360, 180));

    JButton updateButton = new JButton("Update");
    updateButton.setVisible(false);

    JButton editButton = new JButton("Edit");
    editButton.addActionListener(e -> {
      leadAttendeeField.setEditable(true);
      totalAttendeesField.setEditable(true);
      dateField.setEditable(true);
      editButton.setEnabled(false);
      updateButton.setVisible(true);
    });

    updateButton.addActionListener(e -> {
      leadAttendeeField.setEditable(false);
      totalAttendeesField.setEditable(false);
      dateField.setEditable(false);
      editButton.setEnabled(true);
      updateButton.setVisible(false);

      try {
        Itinerary updatedItinerary = itineraries.get(itineraries.indexOf(selectedItinerary));

        updatedItinerary.setLeadAttendeeFirstName(leadAttendeeField.getText().split(" ")[0]);
        updatedItinerary.setLeadAttendeeLastName(leadAttendeeField.getText().split(" ")[1]);
        updatedItinerary.setTotalAttendees(Integer.parseInt(totalAttendeesField.getText()));
        updatedItinerary.setDate(LocalDate.parse(dateField.getText()));

        updatedItinerary.generateRefNum();
        for (Activity activity : updatedItinerary.getActivitiesList()) {
          activity.calculateTotalCost(updatedItinerary.getTotalAttendees());
        }
        updatedItinerary.calculateItineraryCost();
        new SaveItinerary().deleteItinerary(itineraries.indexOf(selectedItinerary), itineraries);
        new SaveItinerary().serializeItineraries(updatedItinerary);

        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        dialog.setSize(300, 100);
        dialog.setLocationRelativeTo(getContentPane());

        dialog.add(new JLabel("Itinerary updated! Re-open manager to see changes."));
        dialog.setVisible(true);

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

      } catch (Exception ex) {
        leadAttendeeField.setText(selectedItinerary.getLeadAttendeeFirstName() + " " + selectedItinerary.getLeadAttendeeLastName());
        totalAttendeesField.setText(String.valueOf(selectedItinerary.getTotalAttendees()));
        dateField.setText(String.valueOf(selectedItinerary.getDate()));
        JOptionPane.showMessageDialog(getContentPane(), "Error updating itinerary: " + ex.getMessage());
      }

    });

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    JPanel infoPanel = new JPanel();
    infoPanel.setLayout(new BorderLayout());
    infoPanel.add(infoFormPanel, BorderLayout.CENTER);


    JPanel updateButtonsPanel = new JPanel();
    updateButtonsPanel.setLayout(new BorderLayout());
    updateButtonsPanel.add(editButton, BorderLayout.NORTH);
    updateButtonsPanel.add(updateButton, BorderLayout.SOUTH);

    infoPanel.add(updateButtonsPanel, BorderLayout.EAST);

    panel.add(infoPanel, BorderLayout.NORTH);
    panel.add(scrollPane1, BorderLayout.CENTER);
    panel.add(scrollPane2, BorderLayout.SOUTH);

    infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
    setTitle(selectedItinerary.getRefNumber());
    setResizable(true);
    add(panel);
    setSize(800, 500);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);
    setLocationRelativeTo(getContentPane());
  }
}