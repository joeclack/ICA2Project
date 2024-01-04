package D3981791.phase_4.Experimental;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.PreBuiltItems;
import D3981791.phase_3.Model.SaveItinerary;
import D3981791.phase_4.GUI.UI.ItineraryScreen;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
public class NewItineraryForm extends JFrame {

    private final JLabel totalActivities;
    private Itinerary itinerary;
    final JButton submit = new JButton("Submit");
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField attendeesField;
    private final JTextField dateField;
    private final JButton addActivitiesButton;
    private final List<String> availableActivities = List.of("Paper Bridge Building", "Assault Course", "Cooking");
    private final List<String> availableAddOns = List.of("Travel", "Insurance", "Photography");
    private int activityCount = 0;
    private final PreBuiltItems preBuiltItems = new PreBuiltItems();
    private final List<Activity> listOfActivities;

    /** Constructor for NewItineraryForm
     *
     */
    public NewItineraryForm() {
        super();

        // initialise list of activities
        listOfActivities = new ArrayList<>();

        // set up window
        setTitle("Itinerary Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // set up form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // add fields
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        panel.add(firstNameLabel);
        panel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        JLabel attendeesLabel = new JLabel("Total Attendees:");
        attendeesField = new JTextField();
        panel.add(attendeesLabel);
        panel.add(attendeesField);

        JLabel activitiesLabel = new JLabel("Total Activities:");
        totalActivities = new JLabel(String.valueOf(activityCount));
        panel.add(activitiesLabel);
        panel.add(totalActivities);

        JLabel dateLabel = new JLabel("Date (DD-MM-YY):");
        dateField = new JTextField();
        panel.add(dateLabel);
        panel.add(dateField);

        // itinerary add-ons panel
        JPanel itineraryAddOnsPanel = new JPanel();
        itineraryAddOnsPanel.setLayout(new BoxLayout(itineraryAddOnsPanel, BoxLayout.X_AXIS));
        itineraryAddOnsPanel.setBorder(BorderFactory.createTitledBorder("Itinerary Add-Ons"));

        // add checkboxes
        List<JCheckBox> itineraryAddOnCheckboxes = new ArrayList<>();
        List<String> availableItineraryAddOns = List.of("Accommodation", "Coffee/Tea breaks", "Lunch");
        for (String availableItineraryAddOn : availableItineraryAddOns) {
            JCheckBox checkBox = new JCheckBox(availableItineraryAddOn);
            itineraryAddOnsPanel.add(checkBox);
            itineraryAddOnCheckboxes.add(checkBox);
        }

        // add activities button
        addActivitiesButton = new JButton("Add Activities");

        // add panels to window
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(panel, BorderLayout.NORTH);
        add(itineraryAddOnsPanel);
        setSize(400, 250);
        setResizable(true);

        // add activities panel
        JPanel addActivitiesPanel = new JPanel();
        addActivitiesPanel.setBackground(Color.BLUE);
        addActivitiesPanel.setLayout(new BoxLayout(addActivitiesPanel, BoxLayout.X_AXIS));
        addActivitiesPanel.add(addActivitiesButton);
        addActivitiesPanel.setVisible(true);

        add(addActivitiesPanel);
        setVisible(true);
        setLocationRelativeTo(null);

        // submit event listener
        submit.addActionListener(e -> {
            // validate form
            if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || attendeesField.getText().isEmpty() || dateField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(getContentPane(), "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (activityCount == 0) {
                JOptionPane.showMessageDialog(getContentPane(), "Please add at least one activity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Integer.parseInt(attendeesField.getText()) < 1 || Integer.parseInt(attendeesField.getText()) > 100) {
                JOptionPane.showMessageDialog(getContentPane(), "Please enter a number between 1 and 100 for total attendees", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Integer.parseInt(attendeesField.getText()) < activityCount) {
                JOptionPane.showMessageDialog(getContentPane(), "Please enter a number greater than or equal to the total number of activities", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd-MM-yy"));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(getContentPane(), "Please enter a valid date in the format DD-MM-YY", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // create itinerary
            itinerary = new Itinerary(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(attendeesField.getText()), activityCount, LocalDate.parse(dateField.getText(), DateTimeFormatter.ofPattern("dd-MM-yy")));
            for (int i = 0; i < itineraryAddOnCheckboxes.size(); i++) {
                if (itineraryAddOnCheckboxes.get(i).isSelected()) {
                    itinerary.getItineraryAddOnsList().add(preBuiltItems.getAvailableItineraryAddOns().get(i));
                }
            }

            itinerary.getActivitiesList().addAll(listOfActivities);
            itinerary.calculateItineraryCost();
            itinerary.generateRefNum();
            new SaveItinerary().serializeItineraries(itinerary);
            java.awt.EventQueue.invokeLater(() -> new ItineraryScreen(itinerary).setVisible(true));

            // clear all fields
            firstNameField.setText("");
            lastNameField.setText("");
            attendeesField.setText("");
            dateField.setText("");
            totalActivities.setText("0");
            activityCount = 0;
            listOfActivities.clear();
            addActivitiesButton.setVisible(true);

            pack();

        });

        // add activities event listener
        addActivitiesButton.addActionListener(e -> {
            System.out.println(activityCount);
            JPanel newActivityAddPanel = newActivity();
            add(newActivityAddPanel, BorderLayout.CENTER);
            newActivityAddPanel.setVisible(true);
            newActivityAddPanel.setBorder(BorderFactory.createTitledBorder("Activities"));

            JPanel submitPanel = new JPanel();
            submitPanel.setBackground(Color.BLUE);
            submitPanel.setLayout(new BoxLayout(submitPanel, BoxLayout.X_AXIS));
            submitPanel.add(submit);

            add(submitPanel, BorderLayout.SOUTH);

            pack();

        });
        setAlwaysOnTop(true);
    }

    /**
     * Creates a new activity panel
     * @return JPanel
     */
    public JPanel newActivity() {
        // activities panel

        // create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<JPanel> activityPanels = new ArrayList<>();

        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));

        // add fields
        JLabel activitiesLabel = new JLabel("Select Activity:");
        JComboBox<String> activityComboBox = new JComboBox<>();
        activityComboBox.addItem("");

        availableActivities.forEach(activityComboBox::addItem);
        activityComboBox.setSelectedIndex(-1);
        activityPanel.add(activitiesLabel);
        activityPanel.add(activityComboBox);

        JLabel timeLabel = new JLabel("Time for activity (hh:mm):");
        JTextField timeField = new JTextField();
        activityPanel.add(timeLabel);
        activityPanel.add(timeField);

        // add checkboxes
        List<JCheckBox> addOnCheckboxes = new ArrayList<>();
        for (String availableAddOn : availableAddOns) {
            JCheckBox checkBox = new JCheckBox(availableAddOn);
            activityPanel.add(checkBox);
            addOnCheckboxes.add(checkBox);
        }

        // add panel to main panel
        activityPanels.add(activityPanel);
        mainPanel.add(activityPanel, BorderLayout.NORTH);

        // add button
        JButton addActivityButton = new JButton("Add Activity");

        // add button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(addActivityButton);

        // add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.RED);
        addActivityButton.addActionListener(e -> {
            // validate form
            if (activityComboBox.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(getContentPane(), "Please select an activity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int selectedActivityIndex = activityComboBox.getSelectedIndex() - 1; // -1 to account for placeholder item

            // create activity
            Activity selectedActivity = preBuiltItems.getAvailableActivities().get(selectedActivityIndex);
            selectedActivity.setTime(LocalTime.parse(timeField.getText()));
            for(int i = 0; i < addOnCheckboxes.size(); i++) {
                if(addOnCheckboxes.get(i).isSelected()) {
                    selectedActivity.getActivityAddOnsList().add(preBuiltItems.getAvailableActivityAddOns().get(i));
                    System.out.println("Added add-on: " + preBuiltItems.getAvailableActivityAddOns().get(i).getName());
                }
            }
            selectedActivity.calculateTotalCost(Integer.parseInt(attendeesField.getText()));
            selectedActivity.setThirdPartyInsurance(false);
            listOfActivities.add(selectedActivity);

            // clear for new activity
            System.out.println("Added activity: " + selectedActivity.getTitle() + " at " + selectedActivity.getTime() + " with " + selectedActivity.getTotalCost() + " cost");
            activityComboBox.setSelectedIndex(-1);
            timeField.setText("");
            for (JCheckBox checkBox : addOnCheckboxes) {
                checkBox.setSelected(false);
            }
            activityCount++;

            // update total activities
            totalActivities.setText(String.valueOf(activityCount));
            if(activityCount==availableActivities.size()){
                addActivitiesButton.setVisible(false);
                mainPanel.setVisible(false);
                pack();
            }

        });

        return mainPanel;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewItineraryForm::new);
    }
}