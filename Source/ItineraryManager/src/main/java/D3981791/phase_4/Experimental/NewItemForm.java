package D3981791.phase_4.Experimental;

import D3981791.phase_1.Model.PreBuiltItems;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class NewItemForm extends JFrame {
    private final PreBuiltItems preBuiltItems = new PreBuiltItems();
    private final Object type = null;
    private JTextField nameField, titleField , costField, actCostField, locationField, durationField, insuranceField, expectedDurationField, descriptionField;
    private JLabel nameLabel, titleLabel, costLabel, actCostLabel, locationLabel, durationLabel, insuranceLabel, expectedDurationLabel, descriptionLabel;
    private JComboBox typeComboBox;
    private JCheckBox insuranceCheckBox;
    private JButton submitButton;
    private final List<String> types = List.of(new String[]{"Activity", "Activity Add-On", "Itinerary Add-On"});


    /**
     * Constructor for objects of class NewItemForm
     */
    public NewItemForm() {
        super();

        titleField = new JTextField();
        actCostField = new JTextField();
        locationField = new JTextField();
        durationField = new JTextField();
        expectedDurationField = new JTextField();
        descriptionField = new JTextField();
        costField = new JTextField();
        nameField = new JTextField();

        insuranceCheckBox = new JCheckBox();

        nameLabel = new JLabel("Name");
        costLabel = new JLabel("Cost (pence)");
        titleLabel = new JLabel("Name");
        actCostLabel = new JLabel("Cost (pence)");
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
        activityForm.add(titleLabel);
        activityForm.add(titleField);
        activityForm.add(actCostLabel);
        activityForm.add(actCostField);
        activityForm.add(descriptionLabel);
        activityForm.add(descriptionField);
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