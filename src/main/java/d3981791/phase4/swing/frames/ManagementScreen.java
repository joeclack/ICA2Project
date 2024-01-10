/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4.swing.frames;

import d3981791.phase1.RunPhase1;
import d3981791.phase1.model.Itinerary;
import d3981791.phase1.model.PreBuiltItems;
import d3981791.phase3.model.DataPersistence;
import d3981791.phase4.swing.model.*;
import d3981791.phase4.swing.testing.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * The management screen will include
 * - A table of all itineraries
 * - A button to add a new itinerary
 * - A button to delete all itineraries
 * - A button to delete a single itinerary
 * - A button to show/hide pre-built items
 * - A table of all pre-built items
 */
public class ManagementScreen extends JFrame {

    private final JTable managementTable;
    private final List<Itinerary> itinerariesList;
    private final JLabel totalItinerariesLabel;
    private final DataPersistence save;
    private final PreBuiltItems preBuiltItems;
    private final JPanel preBuiltItemsTablePanel;
    private final JPanel toolBarPanel;

    /**
     * Constructor for the management screen
     */
    public ManagementScreen() {
        super();

        save = new DataPersistence();

        // Set up the pre-built items object to retrieve activities and add-ons
        preBuiltItems = new PreBuiltItems();

        // Set up the list of itineraries
        itinerariesList = save.deSerializeItineraries();

        // Set up the total itineraries label
        totalItinerariesLabel = new JLabel(setTotalItinerariesText());

        // Set up the management table with all itineraries loaded
        managementTable = createManagementTable();

        // Set up the panel containing the pre-built items tables
        preBuiltItemsTablePanel = setupAvailableItemsPanel(setupAvailableAddOnsTable(), setupAvailableActivityAddOnsTable(), setupAvailableActivitiesTable());

        toolBarPanel = setupToolBarPanel();

        setupManagementFrame();

    }

    // Creates the frame, adding components to it and setting its properties
    private void setupManagementFrame() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(new JScrollPane(managementTable), BorderLayout.NORTH);
        add(toolBarPanel, BorderLayout.CENTER);
        add(preBuiltItemsTablePanel, BorderLayout.SOUTH);

        setSize(1000, 500);
        setTitle("Management Screen");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(true);
        setLocationRelativeTo(null);

        rowSelectionEventHandler();
    }

    // Handles the row/itinerary selection event
    private void rowSelectionEventHandler() {
        managementTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    expandItineraryOnLeftClick();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    deleteItineraryOnRightClick(e);
                }
            }
        });
    }

    // Creates the management table
    private JTable createManagementTable() {
        JTable table = new JTable();
        table.setModel(new ItineraryListModel(itinerariesList));
        table.setBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(192, 217, 237));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

    // Creates the available activities table
    private JTable setupAvailableActivitiesTable() {
        JTable table = new JTable();
        table.setModel(new AvailableActivitiesModel(preBuiltItems.getAvailableActivities()));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);

        return table;
    }

    // Creates the available activity add-ons table
    private JTable setupAvailableActivityAddOnsTable() {
        JTable table = new JTable();
        table.setModel(new AvailableAAddOnModel(preBuiltItems.getAvailableActivityAddOns()));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);

        return table;
    }

    // Creates the available itinerary add-ons table
    private JTable setupAvailableAddOnsTable() {
        JTable table = new JTable();
        table.setModel(new AvailableIAddOnModel(preBuiltItems.getAvailableItineraryAddOns()));

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.white);

        return table;
    }

    // Creates the panel that holds the available items tables
    private JPanel setupAvailableItemsPanel(JTable preBuiltActivitiesTable, JTable preBuiltActivityAddOnsTable, JTable preBuiltItineraryAddOnsTable) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        panel.add(new JScrollPane(preBuiltActivitiesTable));
        panel.add(new JScrollPane(preBuiltActivityAddOnsTable));
        panel.add(new JScrollPane(preBuiltItineraryAddOnsTable));
        panel.setVisible(false);

        return panel;
    }

    // Creates the toolbar panel with the delete all button, total itineraries label, add new itinerary button and show/hide pre-built items button
    private JPanel setupToolBarPanel() {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(deleteAllButton());
        panel.add(totalItinerariesLabel);
        panel.add(addNewItineraryButton());
        panel.add(availableItemsToggleButton());
        panel.setBackground(Color.LIGHT_GRAY);

        return panel;
    }

    // Creates the show/hide pre-built items button
    private JButton availableItemsToggleButton() {
        JButton button = new JButton("Show pre-built items");
        button.addActionListener(event -> {
            if (preBuiltItemsTablePanel.isVisible()) {
                preBuiltItemsTablePanel.setVisible(false);
                button.setText("Show pre-built items");
            } else {
                preBuiltItemsTablePanel.setVisible(true);
                button.setText("Hide pre-built items");
            }
        });

        return button;
    }

    // Creates the delete all button
    private JButton deleteAllButton() {
        JButton button = new JButton("Delete all");
        button.addActionListener(event -> {
            save.deleteAll(itinerariesList);
            managementTable.updateUI();
            totalItinerariesLabel.setText(setTotalItinerariesText());
        });

        return button;
    }

    // Creates the add new itinerary button
    private JButton addNewItineraryButton() {
        JButton button = new JButton("Add itinerary");
        button.addActionListener(event -> {
            randomData randomData = new randomData();
            Itinerary newItinerary = new RunPhase1().createItinerary(randomData.firstNameGenerator(), randomData.lastNameGenerator(), randomData.attendeesGenerator(), randomData.dateGenerator());
            itinerariesList.add(newItinerary);
            new DataPersistence().serializeItineraries(newItinerary);
            managementTable.setModel(new ItineraryListModel(itinerariesList));
            managementTable.updateUI();
            totalItinerariesLabel.setText(setTotalItinerariesText());
        });

        return button;
    }

    // Expands the itinerary on left click, opening the itinerary screen
    private void expandItineraryOnLeftClick() {
        int selectedRow = managementTable.getSelectedRow();
        if (selectedRow != -1) {
            Object itineraryAtRow0 = managementTable.getValueAt(selectedRow, 0);

            try {
                for (Itinerary selectedItinerary : itinerariesList) {
                    if (selectedItinerary.getRefNumber().equals(itineraryAtRow0)) {
                        new ItineraryScreen(selectedItinerary).setVisible(true);
                        managementTable.clearSelection();
                        break;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    // Shows delete itinerary popup menu on right click
    private void deleteItineraryOnRightClick(MouseEvent e) {
        int row = managementTable.rowAtPoint(e.getPoint());
        int column = managementTable.columnAtPoint(e.getPoint());

        try {
            if (row >= 0 && column >= 0) {
                managementTable.setRowSelectionInterval(row, row);
                managementTable.setColumnSelectionInterval(column, column);

                deletePopupMenu().show(managementTable, e.getX(), e.getY());
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Creates the delete popup menu, allowing a user to delete an itinerary
    private JPopupMenu deletePopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem menuItem = new JMenuItem("Delete");
        menuItem.addActionListener(event -> {
            int selectedRow = managementTable.getSelectedRow();
            save.deleteItinerary(selectedRow, itinerariesList);
            managementTable.updateUI();
            managementTable.clearSelection();
            totalItinerariesLabel.setText(setTotalItinerariesText());
        });

        popupMenu.add(menuItem);

        return popupMenu;
    }

    // Sets the total itineraries label text, showing the total number of itineraries and call when an itinerary is added or deleted
    private String setTotalItinerariesText() {
        return "Total Itineraries - (" + itinerariesList.size() + ")";
    }
}