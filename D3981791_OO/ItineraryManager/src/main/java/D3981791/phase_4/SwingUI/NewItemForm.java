package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.PreBuiltItems;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class NewItemForm extends JFrame{
    private final PreBuiltItems preBuiltItems = new PreBuiltItems();
    private final Object type = null;
    private JTextField nameField, costField, locationField, durationField, insuranceField, expectedDurationField, descriptionField;
    private JLabel nameLabel, costLabel, locationLabel, durationLabel, insuranceLabel, expectedDurationLabel, descriptionLabel;
    private JComboBox typeComboBox;
    private JCheckBox insuranceCheckBox;
    private JButton submitButton;
    private final List<String> types = List.of(new String[]{"Activity", "Activity Add-On", "Itinerary Add-On"});


    public NewItemForm() {
        super();

        nameField = new JTextField();
        costField = new JTextField();
        locationField = new JTextField();
        durationField = new JTextField();
        expectedDurationField = new JTextField();
        descriptionField = new JTextField();

        insuranceCheckBox = new JCheckBox();

        nameLabel = new JLabel("Name");
        costLabel = new JLabel("Cost");
        locationLabel = new JLabel("Location");
        durationLabel = new JLabel("Duration");
        insuranceLabel = new JLabel("Insurance");
        expectedDurationLabel = new JLabel("Expected Duration");
        descriptionLabel = new JLabel("Description");

        submitButton = new JButton("Submit");
        typeComboBox = new JComboBox(types.toArray());

        JPanel formGrid = new JPanel();
        formGrid.setLayout(new BorderLayout());

        JPanel activityForm = new JPanel();
        activityForm.setLayout(new GridLayout(6, 2));
        activityForm.add(nameLabel);
        activityForm.add(nameField);
        activityForm.add(descriptionLabel);
        activityForm.add(descriptionField);
        activityForm.add(costLabel);
        activityForm.add(costField);
        activityForm.add(locationLabel);
        activityForm.add(locationField);
        activityForm.add(durationLabel);
        activityForm.add(durationField);
        activityForm.add(insuranceLabel);
        activityForm.add(insuranceCheckBox);

        JPanel addOnForm = new JPanel();
        addOnForm.setLayout(new GridLayout(2, 2));
        addOnForm.add(nameLabel);
        addOnForm.add(nameField);
        addOnForm.add(costLabel);
        addOnForm.add(costField);



        // make a listener for when the type is changed
        typeComboBox.addActionListener(e -> {
            String type = (String) typeComboBox.getSelectedItem();
            if(Objects.equals(type, "Activity")) {
                formGrid.removeAll();
                formGrid.add(activityForm, BorderLayout.CENTER);

                formGrid.updateUI();
            } else if(Objects.equals(type, "Activity Add-On") || Objects.equals(type, "Itinerary Add-On")) {
                formGrid.removeAll();
                formGrid.add(addOnForm, BorderLayout.CENTER);

                formGrid.updateUI();
            }
        });

        setSize(500, 500);
        setLayout(new BorderLayout());
        add(typeComboBox, BorderLayout.NORTH);
        add(formGrid, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        NewItemForm form = new NewItemForm();
        form.setVisible(true);
        form.setSize(500, 500);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}