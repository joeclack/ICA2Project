/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.*;
import D3981791.phase_3.Model.SaveItinerary;

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

public class ManagementScreen extends JFrame {

  private final JTable managementTable;
  private final JScrollPane scrollPane;
  private final List<Itinerary> itineraries;

  public ManagementScreen() {
    super();
    SaveItinerary save = new SaveItinerary();
    itineraries = save.deSerializeItineraries();
    managementTable = new JTable();
    managementTable.setModel(new ItineraryTableModel(itineraries));

    managementTable.setBackground(Color.LIGHT_GRAY);
    managementTable.setGridColor(Color.DARK_GRAY);
    managementTable.setSelectionBackground(new Color(192, 217, 237));

    JTableHeader header = managementTable.getTableHeader();
    header.setBackground(Color.DARK_GRAY);
    header.setForeground(Color.white);
    managementTable.getTableHeader().setReorderingAllowed(false);

    scrollPane = new JScrollPane(managementTable);

    add(scrollPane);
    setSize(800, 450);
    setTitle("Management Screen");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setAlwaysOnTop(true);
    setResizable(false);
    setLocationRelativeTo(null);

    JButton deleteAllButton = new JButton("Delete all");
    deleteAllButton.addActionListener(event -> {
      save.deleteAll(itineraries);
      managementTable.updateUI();
    });


    JButton addItineraryButton = new JButton("Add itinerary");

    addItineraryButton.addActionListener(event -> {
      Itinerary newItinerary = testWithDifferentAttendeeNames(randomFirstnameGenerator(), randomSurnameGenerator(), randomAttendeeGenerator(), randomDateGenerator());
      itineraries.add(newItinerary);
      new SaveItinerary().serializeItineraries(newItinerary);
      managementTable.setModel(new ItineraryTableModel(itineraries));
      scrollPane.updateUI();
    });


    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

    JPanel toolBar = new JPanel();
    toolBar.setLayout(new BorderLayout());
    toolBar.add(deleteAllButton, BorderLayout.WEST);
    toolBar.add(addItineraryButton, BorderLayout.EAST);
    add(toolBar, BorderLayout.SOUTH);

    managementTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button clicked
          int selectedRow = managementTable.getSelectedRow();
          if (selectedRow != -1) {
            Object itineraryAtRow0 = managementTable.getValueAt(selectedRow, 0);

            for (Itinerary selectedItinerary : itineraries) {
              if (selectedItinerary.getRefNumber().equals(itineraryAtRow0)) {
                new ItineraryScreen(selectedItinerary).setVisible(true);
                managementTable.clearSelection();
                break;
              }
            }
          }
        }
      }
    });

    managementTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
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
            });
            popupMenu.add(deleteMenuItem);

            popupMenu.show(managementTable, e.getX(), e.getY());
          }
        }
      }

    });

  }

  public static LocalDate randomDateGenerator() {
    int minDay = (int) LocalDate.of(2023, 12, 19).toEpochDay();
    int maxDay = (int) LocalDate.of(2024, 12, 31).toEpochDay();
    long randomDay = minDay + (int) (Math.random() * (maxDay - minDay));
    return LocalDate.ofEpochDay(randomDay);
  }

  public static int randomAttendeeGenerator() {
    return (int) (Math.random() * 10) + 1;
  }

  public static String randomFirstnameGenerator() {
    List<String> names = Arrays.asList("John", "Sam", "Jack", "Jill", "James", "Ben", "Henry", "Bobby", "Dave", "Bridget", "Alice", "Sarah", "Jane", "Kate", "Mary", "Emily", "Emma", "Olivia", "Jessica", "Sophie");
    return names.get((int) (Math.random() * names.size()));
  }

  public static String randomSurnameGenerator() {
    List<String> surnames = Arrays.asList("Doe", "Smith", "Jones", "Brown", "Wilson", "Taylor", "Johnson", "White", "Martin", "Anderson", "Thompson", "Nguyen", "Thomas", "Walker", "Harris", "Lee", "Ryan", "Robinson");
    return surnames.get((int) (Math.random() * surnames.size()));
  }

  public static int randomNumGenerator(int min, int max) {
    return (int) (Math.random() * (max - min)) + min;
  }

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