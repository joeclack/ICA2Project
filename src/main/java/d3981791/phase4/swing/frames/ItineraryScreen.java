/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4.swing.frames;

import d3981791.phase1.model.Itinerary;
import d3981791.phase4.swing.model.ActivitiesModel;
import d3981791.phase4.swing.model.SelectedItineraryAddOnModel;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static d3981791.phase4.swing.model.Format.formatCost;

/**
 * The itinerary screen
 */
public class ItineraryScreen extends JFrame {

    private final Itinerary itinerary;
    private final JTable itineraryAddOnsTable;
    private final JTable activitiesTable;
    
    public ItineraryScreen(Itinerary itinerary) {
        super();

        this.itinerary = itinerary;

        itineraryAddOnsTable = setupItineraryAddOnsTable();

        activitiesTable = setupActivitiesTable();

        setupItineraryFrame();

    }

    private void setupItineraryFrame() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(setupItineraryInfoPanel(), BorderLayout.NORTH);
        add(itineraryItemsTablePanel(), BorderLayout.SOUTH);

        setResizable(true);
        setTitle(itinerary.getRefNumber());
        setSize(800, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
    }

    private JPanel itineraryItemsTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.add(new JScrollPane(itineraryAddOnsTable));
        panel.add(new JScrollPane(activitiesTable));

        return panel;
    }

    private JTable setupActivitiesTable() {
        JTable table = new JTable();
        table.setModel(new ActivitiesModel(itinerary.getActivitiesList()));
        table.setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(192, 217, 237));
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);

        return table;
    }

    private JTable setupItineraryAddOnsTable() {
        JTable table = new JTable();
        table.setModel(new SelectedItineraryAddOnModel(itinerary.getItineraryAddOnsList()));
        table.setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(192, 217, 237));
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader addOnHeader = table.getTableHeader();
        addOnHeader.setBackground(Color.DARK_GRAY);
        addOnHeader.setForeground(Color.white);

        return table;
    }

    private JPanel setupItineraryInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField(itinerary.getLeadAttendeeFirstName() + " " + itinerary.getLeadAttendeeLastName());
        nameField.setEditable(false);
        JTextField totalAttendeesField = new JTextField(String.valueOf(itinerary.getTotalAttendees()));
        totalAttendeesField.setEditable(false);
        JTextField totalCostField = new JTextField(formatCost(itinerary.getItineraryCost()));
        totalCostField.setEditable(false);
        JTextField discountField = new JTextField(itinerary.getDiscountDecimal()*100 + "%");
        discountField.setEditable(false);
        JTextField dateField = new JTextField(String.valueOf(itinerary.getDate()));
        dateField.setEditable(false);

        panel.add(new JLabel("Name"));
        panel.add(nameField);
        panel.add(new JLabel("Total attendees"));
        panel.add(totalAttendeesField);
        panel.add(new JLabel("Total cost"));
        panel.add(totalCostField);
        panel.add(new JLabel("Discount"));
        panel.add(discountField);
        panel.add(new JLabel("Date"));
        panel.add(dateField);

        return panel;
    }
}