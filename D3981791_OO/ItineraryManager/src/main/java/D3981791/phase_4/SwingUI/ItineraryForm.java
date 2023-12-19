package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.*;
import D3981791.phase_3.Model.SaveItinerary;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ItineraryForm extends JFrame {

    private final JLabel totalActivities;
    private Itinerary itinerary;
    JButton submit = new JButton("Submit");
    private JLabel firstNameLabel, lastNameLabel, attendeesLabel, activitiesLabel, dateLabel;
    private JTextField firstNameField, lastNameField, attendeesField, activitiesField, dateField;
    private JButton addActivitiesButton;
    private JComboBox<String> activityComboBox;
    private JCheckBox[] addOnCheckboxes;
    private List<String> availableActivities = List.of("Paper Bridge Building", "Assault Course", "Cooking");
    private List<String> availableAddOns = List.of("Travel", "Insurance", "Photography");
    private List<String> availableItineraryAddOns = List.of("Accommodation", "Coffee/Tea breaks", "Lunch");
    private int activityCount = 0;
    private PreBuiltItems preBuiltItems = new PreBuiltItems();
    private boolean yep = false;
    private List<Activity> listOfActivities;

    public ItineraryForm() {
        super();

        listOfActivities = new ArrayList<>();

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
        totalActivities = new JLabel(String.valueOf(activityCount));
        panel.add(activitiesLabel);
        panel.add(totalActivities);

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

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(panel, BorderLayout.NORTH);
        add(itineraryAddOnsPanel);

        setSize(400, 250);
        setResizable(true);


        JPanel addActivitiesPanel = new JPanel();
        addActivitiesPanel.setBackground(Color.BLUE);
        addActivitiesPanel.setLayout(new BoxLayout(addActivitiesPanel, BoxLayout.X_AXIS));


        addActivitiesPanel.add(addActivitiesButton);
        addActivitiesPanel.setVisible(true);

        add(addActivitiesPanel);

        setVisible(true);
        setLocationRelativeTo(null);

        // submit event listener
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
//                if (!dateField.getText().matches("\\d{2}-\\d{2}-\\d{2}")) {
//                    JOptionPane.showMessageDialog(getContentPane(), "Please enter a date in the format DD-MM-YY", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }

                itinerary = new Itinerary(firstNameField.getText(), lastNameField.getText(), Integer.parseInt(attendeesField.getText()), activityCount, LocalDate.parse(dateField.getText()));
                for (int i = 0; i < itineraryAddOnCheckboxes.size(); i++) {
                    if (itineraryAddOnCheckboxes.get(i).isSelected()) {
                        itinerary.getItineraryAddOnsList().add(preBuiltItems.getAvailableItineraryAddOns().get(i));
                    }
                }
                itinerary.getActivitiesList().addAll(listOfActivities);
                itinerary.calculateItineraryCost();
                itinerary.generateRefNum();
                System.out.println("Total cost: " + itinerary.getItineraryCost());
                System.out.println("Discount: " + itinerary.getDiscountDecimal());
                new SaveItinerary().serializeItineraries(itinerary);
            }
        });
        addActivitiesButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
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
            }
        });
    }

    public JPanel newActivity() {
        // activities panel

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        List<JPanel> activityPanels = new ArrayList<>();


        JPanel activityPanel = new JPanel();
        activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.Y_AXIS));

        JLabel activitiesLabel = new JLabel("Select Activity:");
        JComboBox<String> activityComboBox = new JComboBox<>();
        activityComboBox.addItem(""); // Add a placeholder item (empty string)
        availableActivities.forEach(activityComboBox::addItem); // Add actual activities
        activityComboBox.setSelectedIndex(-1); // Set initial selection to nothing
        activityPanel.add(activitiesLabel);
        activityPanel.add(activityComboBox);

        JLabel timeLabel = new JLabel("Time for activity (hh:mm):");
        JTextField timeField = new JTextField();
        activityPanel.add(timeLabel);
        activityPanel.add(timeField);


        List<JCheckBox> addOnCheckboxes = new ArrayList<>();
        for (int j = 0; j < availableAddOns.size(); j++) {
            JCheckBox checkBox = new JCheckBox(availableAddOns.get(j));
            activityPanel.add(checkBox);
            addOnCheckboxes.add(checkBox);
        }

        activityPanels.add(activityPanel);
        mainPanel.add(activityPanel, BorderLayout.NORTH);

        JButton addActivityButton = new JButton("Add Activity");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(addActivityButton);



        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.setBackground(Color.RED);
        addActivityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activityComboBox.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(getContentPane(), "Please select an activity", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int selectedActivityIndex = activityComboBox.getSelectedIndex() - 1; // -1 to account for placeholder item



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

                totalActivities.setText(String.valueOf(activityCount));
                if(activityCount==availableActivities.size()){
                    addActivitiesButton.setVisible(false);
                    mainPanel.setVisible(false);
                    pack();
                }

            }
        });

        return mainPanel;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ItineraryForm::new);
    }
}