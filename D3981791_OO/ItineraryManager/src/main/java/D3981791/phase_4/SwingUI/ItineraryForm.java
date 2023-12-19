package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.ItineraryAddOn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItineraryForm extends JFrame {

    private Itinerary itinerary;
    private JLabel firstNameLabel, lastNameLabel, attendeesLabel, activitiesLabel, dateLabel;
    private JTextField firstNameField, lastNameField, attendeesField, activitiesField, dateField;
    private JButton submitButton;
    private JButton addActivitiesButton;
    private JComboBox<String> activityComboBox;
    private JCheckBox[] addOnCheckboxes;
    private List<String> availableActivities = List.of("Paper Bridge Building", "Assault Course", "Cooking");
    private List<String> availableAddOns = List.of("Travel", "Insurance", "Photography");
    private List<String> availableItineraryAddOns = List.of("Accommodation", "Coffee/Tea breaks", "Lunch");

    public ItineraryForm() {
        super();

        setTitle("Itinerary Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        panel.add(firstNameLabel);
        panel.add(firstNameField);

        lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        attendeesLabel = new JLabel("Total Attendees:");
        attendeesField = new JTextField();
        panel.add(attendeesLabel);
        panel.add(attendeesField);

        activitiesLabel = new JLabel("Total Activities:");
        activitiesField = new JTextField();
        panel.add(activitiesLabel);
        panel.add(activitiesField);

        dateLabel = new JLabel("Date (DD-MM-YY):");
        dateField = new JTextField();
        panel.add(dateLabel);
        panel.add(dateField);

        JPanel itineraryAddOnsPanel = new JPanel();
        itineraryAddOnsPanel.setLayout(new BoxLayout(itineraryAddOnsPanel, BoxLayout.X_AXIS));
        itineraryAddOnsPanel.setBorder(BorderFactory.createTitledBorder("Itinerary Add-Ons"));
//        itineraryAddOnsPanel.setBackground(Color.RED);

        List<JCheckBox> itineraryAddOnCheckboxes = new ArrayList<>();
        for (int j = 0; j < availableItineraryAddOns.size(); j++) {
            JCheckBox checkBox = new JCheckBox(availableItineraryAddOns.get(j));
            itineraryAddOnsPanel.add(checkBox);
            itineraryAddOnCheckboxes.add(checkBox);
        }

        addActivitiesButton = new JButton("Add Activities");



        addActivitiesButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    int total = 0;
                    if(Integer.parseInt(activitiesField.getText()) < 1 || Integer.parseInt(activitiesField.getText()) > availableActivities.size()) {
                        JOptionPane.showMessageDialog(getContentPane(), "Please enter a number greater than 0 and less than " + availableActivities.size(), "Error", JOptionPane.ERROR_MESSAGE);
                        activitiesField.setText("");
                        return;
                    } else {
                        showAddActivitiesDialog(Integer.parseInt(activitiesField.getText()));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(getContentPane(), "Please enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                    activitiesField.setText("");
                }
            }
        });



        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(panel, BorderLayout.NORTH);
        add(itineraryAddOnsPanel);
        JButton submit = new JButton("Submit");
        setSize(400, 250);
        setResizable(false);

        JPanel addActivitiesPanel = new JPanel();
        addActivitiesPanel.setBackground(Color.BLUE);
        addActivitiesPanel.setLayout(new BoxLayout(addActivitiesPanel, BoxLayout.X_AXIS));
        addActivitiesPanel.add(addActivitiesButton);
        add(addActivitiesPanel);
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(Color.BLUE);
        submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
        submitPanel.add(submit);
        add(submitPanel, BorderLayout.SOUTH);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void showAddActivitiesDialog(int activitiesTotal) {
        JDialog dialog = new JDialog(this, "Add Activities", true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<JPanel> activityPanels = new ArrayList<>();

        for (int i = 0; i < activitiesTotal; i++) {
            JPanel activityPanel = new JPanel();
            activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));

            JLabel activitiesLabel = new JLabel("Select Activity:");
            JComboBox<String> activityComboBox = new JComboBox<>();
            activityComboBox.addItem(""); // Add a placeholder item (empty string)
            availableActivities.forEach(activityComboBox::addItem); // Add actual activities
            activityComboBox.setSelectedIndex(-1); // Set initial selection to nothing
            activityPanel.add(activitiesLabel);
            activityPanel.add(activityComboBox);

            List<JCheckBox> addOnCheckboxes = new ArrayList<>();
            for (int j = 0; j < availableAddOns.size(); j++) {
                JCheckBox checkBox = new JCheckBox(availableAddOns.get(j));
                activityPanel.add(checkBox);
                addOnCheckboxes.add(checkBox);
            }

            activityPanels.add(activityPanel);
            mainPanel.add(activityPanel);
        }

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getContentPane(), "Successful", "Message", JOptionPane.INFORMATION_MESSAGE);
                // Perform actions when "Add" button is clicked (e.g., save data)
                // Retrieve selected activities and add-ons from activityComboBoxes and addOnCheckboxesList
                // ...

                dialog.dispose(); // Close the dialog after processing
            }
        });

        mainPanel.add(addButton);
        dialog.add(new JScrollPane(mainPanel)); // Add a scroll pane in case the content overflows
        dialog.pack();
        dialog.setVisible(true);
    }

    private void refreshActivityComboBox() {
        String[] activitiesArray = availableActivities.toArray(new String[0]);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(activitiesArray);
        activityComboBox.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ItineraryForm::new);
    }
}