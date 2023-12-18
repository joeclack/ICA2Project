/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4;

import D3981791.phase_1.Model.*;
import D3981791.phase_1.Library.*;
import D3981791.phase_2.Model.ItineraryInput;
import D3981791.phase_2.TextUI.*;
import D3981791.phase_3.Model.SaveItinerary;
import D3981791.phase_4.SwingUI.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Phase4Run implements Serializable{

  public static void main(String[] args) throws IOException, ClassNotFoundException {


    PreBuiltItems preBuiltItems = new PreBuiltItems();


    Scanner scanner = new Scanner(System.in);

    ConsoleUI display = new ConsoleUI();

    System.out.println(display.menu());

    SaveItinerary save = new SaveItinerary();

    boolean finished = false;
    while (!finished) {
      System.out.print(">");
      int menuOption = scanner.nextInt();

      switch (menuOption) {
        case 1:
          Itinerary newItinerary = ItineraryInput.generateItinerary(preBuiltItems.getAvailableActivities(), preBuiltItems.getAvailableActivityAddOns(), preBuiltItems.getAvailableItineraryAddOns());
          System.out.println("Itinerary created!");
          save.serializeItineraries(newItinerary);
          java.awt.EventQueue.invokeLater(() -> {
            new ManagementScreen().setVisible(true);
          });
          finished = true;
          break;
        case 2:
          java.awt.EventQueue.invokeLater(() -> {
            new ManagementScreen().setVisible(true);
          });
          finished = true;
          break;
        case 3:
          exit(0);
          break;
        default:
          System.out.println("Invalid option");
          break;
      }
    }
  }

}