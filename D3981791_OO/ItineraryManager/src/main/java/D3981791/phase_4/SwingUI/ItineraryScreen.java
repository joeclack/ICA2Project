/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.Itinerary;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static D3981791.phase_4.SwingUI.Format.formatItineraryCost;

/**
 *
 * @author d3981791
 */
public class ItineraryScreen extends JFrame {

  public ItineraryScreen(Itinerary selectedItinerary) {
    super();

    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.append("Lead attendee: " + selectedItinerary.getLeadAttendeeFirstName() + " " + selectedItinerary.getLeadAttendeeLastName() + "\n");
    textArea.append("Total attendees: " + selectedItinerary.getTotalAttendees() + "\n");
    
    textArea.append("Total cost: " + formatItineraryCost(selectedItinerary) + "\n");
    textArea.append("Date: " + selectedItinerary.getDate());

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

    itineraryAddOnsTable.setModel(new ItineraryAddOnTableModel(selectedItinerary.getItineraryAddOnsList()));
    JScrollPane scrollPane1 = new JScrollPane(itineraryAddOnsTable);

    activitiesTable.setModel(new ActivitiesTableModel(selectedItinerary.getActivitiesList()));
    JScrollPane scrollPane2 = new JScrollPane(activitiesTable);

    scrollPane1.setPreferredSize(new Dimension(380, 200));
    scrollPane2.setPreferredSize(new Dimension(380, 200));

    itineraryAddOnsTable.setPreferredScrollableViewportSize(new Dimension(360, 180));
    activitiesTable.setPreferredScrollableViewportSize(new Dimension(360, 180));

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());

    panel.add(textArea, BorderLayout.NORTH);
    panel.add(scrollPane1, BorderLayout.CENTER);
    panel.add(scrollPane2, BorderLayout.SOUTH);

    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
    setTitle(selectedItinerary.getRefNumber());
    setResizable(true);
    add(panel);

    setSize(800, 400);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);

  }
}