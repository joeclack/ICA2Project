/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.SwingUI;

import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_3.Model.SaveItinerary;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


/**
 *
 * @author d3981791
 */
public class ManagementScreen extends JFrame {

  private JTable managementTable;
  private JScrollPane scrollPane;

  private List<Itinerary> itineraries;


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

    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    ((JComponent) getContentPane()).setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));


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
              scrollPane.updateUI();
            });
            popupMenu.add(deleteMenuItem);

            popupMenu.show(managementTable, e.getX(), e.getY());
          }
        }
      }

    });

  }

}