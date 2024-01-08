/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.GUI.UI;

import D3981791.phase_1.Model.*;
import D3981791.phase_3.Model.SaveItinerary;
import D3981791.phase_4.Experimental.AvailableAAddOnModel;
import D3981791.phase_4.Experimental.AvailableActivitiesModel;
import D3981791.phase_4.Experimental.AvailableIAddOnModel;
import D3981791.phase_4.GUI.Models.ItineraryListModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * The management screen
 */
public class ManagementScreen extends JFrame {

  private final PreBuiltItems preBuiltItems = new PreBuiltItems();
  private final JTable managementTable, preBuiltActivitiesTable, preBuiltActivityAddOnsTable, preBuiltItineraryAddOnsTable;
  private final JScrollPane itinerariesScrollPane;
  private final JPanel preBuiltItemsTablePanel;
  SaveItinerary save = new SaveItinerary();
  private final List<Itinerary> itineraries = save.deSerializeItineraries();;

  /**
   * Creates the management screen
   */
  public ManagementScreen() {
    super();

    managementTable = new JTable();
    managementTable.setModel(new ItineraryListModel(itineraries));
    managementTable.setBackground(Color.LIGHT_GRAY);
    managementTable.setGridColor(Color.DARK_GRAY);
    managementTable.setSelectionBackground(new Color(192, 217, 237));
    JTableHeader header = managementTable.getTableHeader();
    header.setBackground(Color.DARK_GRAY);
    header.setForeground(Color.white);
    managementTable.getTableHeader().setReorderingAllowed(false);

    itinerariesScrollPane = new JScrollPane(managementTable);

    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    preBuiltActivitiesTable = new JTable();
    preBuiltActivitiesTable.setModel(new AvailableActivitiesModel(preBuiltItems.getAvailableActivities()));
    JTableHeader header2 = preBuiltActivitiesTable.getTableHeader();
    header2.setBackground(Color.DARK_GRAY);
    header2.setForeground(Color.white);

    preBuiltActivityAddOnsTable = new JTable();
    preBuiltActivityAddOnsTable.setModel(new AvailableAAddOnModel(preBuiltItems.getAvailableActivityAddOns()));
    JTableHeader header3 = preBuiltActivityAddOnsTable.getTableHeader();
    header3.setBackground(Color.DARK_GRAY);
    header3.setForeground(Color.white);

    preBuiltItineraryAddOnsTable = new JTable();
    preBuiltItineraryAddOnsTable.setModel(new AvailableIAddOnModel(preBuiltItems.getAvailableItineraryAddOns()));
    JTableHeader header4 = preBuiltItineraryAddOnsTable.getTableHeader();
    header4.setBackground(Color.DARK_GRAY);
    header4.setForeground(Color.white);

    preBuiltItemsTablePanel = new JPanel();
    preBuiltItemsTablePanel.setLayout(new BoxLayout(preBuiltItemsTablePanel, BoxLayout.X_AXIS));
    preBuiltItemsTablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
    preBuiltItemsTablePanel.add(new JScrollPane(preBuiltActivitiesTable));
    preBuiltItemsTablePanel.add(new JScrollPane(preBuiltActivityAddOnsTable));
    preBuiltItemsTablePanel.add(new JScrollPane(preBuiltItineraryAddOnsTable));

    JLabel totalItinerariesLabel = new JLabel("Total Itineraries - (" + itineraries.size() + ")");

    JButton deleteAllButton = new JButton("Delete all");
    // Deletes all itineraries
    deleteAllButton.addActionListener(event -> {
      if(itineraries.isEmpty()) {
        JOptionPane.showMessageDialog(getContentPane(), "There are no itineraries to delete", "Error", JOptionPane.ERROR_MESSAGE);
        return;
      } else {
        int confirmation = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure you want to delete all itineraries?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.NO_OPTION) {
          return;
        } else if (confirmation == JOptionPane.YES_OPTION) {
          save.deleteAll(itineraries);
          managementTable.updateUI();
          totalItinerariesLabel.setText("Total Itineraries - (" + itineraries.size() + ")");
        }
      }

    });

    JPanel toolBar = new JPanel();

    JButton addItineraryButton = getAddItineraryButton(totalItinerariesLabel);


    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));



    toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
    toolBar.add(deleteAllButton);
    toolBar.add(addItineraryButton);


    JButton togglePreBuiltItemsButton = new JButton("Show pre-built items");
    togglePreBuiltItemsButton.addActionListener(event -> {
      if (preBuiltItemsTablePanel.isVisible()) {
        preBuiltItemsTablePanel.setVisible(false);
        togglePreBuiltItemsButton.setText("Show pre-built items");
      } else {
        preBuiltItemsTablePanel.setVisible(true);
        togglePreBuiltItemsButton.setText("Hide pre-built items");
      }
    });

    preBuiltItemsTablePanel.setVisible(false);
    toolBar.add(togglePreBuiltItemsButton);

//    toolBar.setBackground(Color.LIGHT_GRAY);

    managementTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button clicked
          int selectedRow = managementTable.getSelectedRow();
          if (selectedRow != -1) {
            Object itineraryAtRow0 = managementTable.getValueAt(selectedRow, 0);

            for (Itinerary selectedItinerary : itineraries) {
              if (selectedItinerary.getRefNumber().equals(itineraryAtRow0)) {
                new ItineraryScreen(selectedItinerary, itineraries, managementTable).setVisible(true);
                managementTable.clearSelection();
                break;
              }
            }
          }
        } else if (SwingUtilities.isRightMouseButton(e)) {
          int row = managementTable.rowAtPoint(e.getPoint());
          int column = managementTable.columnAtPoint(e.getPoint());

          if (row >= 0 && column >= 0) {
            managementTable.setRowSelectionInterval(row, row);
            managementTable.setColumnSelectionInterval(column, column);

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteMenuItem = new JMenuItem("Delete");
            deleteMenuItem.addActionListener(event -> {
              int selectedRow = managementTable.getSelectedRow();
              save.deleteItinerary(selectedRow, itineraries);
              managementTable.updateUI();
              managementTable.clearSelection();
              totalItinerariesLabel.setText("Total Itineraries - (" + itineraries.size() + ")");
            });
            popupMenu.add(deleteMenuItem);

            popupMenu.show(managementTable, e.getX(), e.getY());
          }
        }
      }
    });

    toolBar.add(totalItinerariesLabel);
    toolBar.add(Box.createHorizontalGlue());
    add(toolBar, BorderLayout.NORTH);
    add(itinerariesScrollPane, BorderLayout.CENTER);
    add(preBuiltItemsTablePanel, BorderLayout.SOUTH);
    setSize(1000, 500);
    setTitle("Management Screen");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
    setResizable(true);
    setLocationRelativeTo(null);

  }

  /**
   * Gets the add itinerary button
   * @param totalItinerariesLabel The total itineraries label
   * @return The add itinerary button
   */
  private JButton getAddItineraryButton(JLabel totalItinerariesLabel) {
    JButton addItineraryButton = new JButton("Add itinerary");


    addItineraryButton.addActionListener(event -> {
      Itinerary newItinerary = testWithDifferentAttendeeNames(randomFirstnameGenerator(), randomSurnameGenerator(), randomAttendeeGenerator(), randomDateGenerator());
      itineraries.add(newItinerary);
      new SaveItinerary().serializeItineraries(newItinerary);
      managementTable.setModel(new ItineraryListModel(itineraries));
      itinerariesScrollPane.updateUI();
      totalItinerariesLabel.setText("Total Itineraries - (" + itineraries.size() + ")");

    });
    return addItineraryButton;
  }

  /**
   * Generates a random date
   * @return The random date
   */
  public static LocalDate randomDateGenerator() {
    int minDay = (int) LocalDate.of(2023, 12, 19).toEpochDay();
    int maxDay = (int) LocalDate.of(2024, 12, 31).toEpochDay();
    long randomDay = minDay + (int) (Math.random() * (maxDay - minDay));
    return LocalDate.ofEpochDay(randomDay);
  }

  /**
   * Generates a random attendee
   * @return The random attendee
   */
  public static int randomAttendeeGenerator() {
    return (int) (Math.random() * 10) + 1;
  }

  /**
   * Generates a random first name
   * @return The random first name
   */
  public static String randomFirstnameGenerator() {
    List<String> names = Arrays.asList("John", "Sam", "Jack", "Jill", "James", "Ben", "Henry", "Bobby", "Dave", "Bridget", "Alice", "Sarah", "Jane", "Kate", "Mary", "Emily", "Emma", "Olivia", "Jessica", "Sophie");
    return names.get((int) (Math.random() * names.size()));
  }

  /**
   * Generates a random surname
   * @return The random surname
   */
  public static String randomSurnameGenerator() {
    List<String> surnames = Arrays.asList("Doe", "Smith", "Jones", "Brown", "Wilson", "Taylor", "Johnson", "White", "Martin", "Anderson", "Thompson", "Nguyen", "Thomas", "Walker", "Harris", "Lee", "Ryan", "Robinson");
    return surnames.get((int) (Math.random() * surnames.size()));
  }

  /**
   * Generates a random number
   * @param min The minimum number
   * @param max The maximum number
   * @return The random number
   */
  public static int randomNumGenerator(int min, int max) {
    return (int) (Math.random() * (max - min)) + min;
  }

  /**
   * Tests with different attendee names
   * @param firstName The first name
   * @param lastName The last name
   * @param attendees The number of attendees
   * @param date The date
   * @return The itinerary
   */
  public Itinerary testWithDifferentAttendeeNames(String firstName, String lastName, int attendees, LocalDate date) {
    PreBuiltItems preBuiltItems = new PreBuiltItems();

    Itinerary itinerary = new Itinerary(firstName, lastName, attendees, 2, date);

    itinerary.generateRefNum();

    List<Activity> activities = preBuiltItems.getAvailableActivities();
    List<ActivityAddOn> activityAddOns = preBuiltItems.getAvailableActivityAddOns();
    List<ItineraryAddOn> itineraryAddOns = preBuiltItems.getAvailableItineraryAddOns();

    Activity activity1 = activities.get(0);
    activity1.setTime(LocalTime.of(10, 0));
    activity1.getActivityAddOnsList().add(activityAddOns.get(0));
    activity1.getActivityAddOnsList().add(activityAddOns.get(2));
    activity1.calculateTotalCost(attendees);
    itinerary.getActivitiesList().add(activity1);

    Activity activity2 = activities.get(1);
    activity2.setTime(LocalTime.of(12, 0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(1));
    activity2.getActivityAddOnsList().add(activityAddOns.get(2));
    activity2.calculateTotalCost(attendees);
    itinerary.getActivitiesList().add(activity2);

    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(0));
    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(1));


    itinerary.calculateTotalItineraryAddOnsCost();
    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();


    return itinerary;
  }
}