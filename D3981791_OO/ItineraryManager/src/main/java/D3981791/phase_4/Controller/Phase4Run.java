/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.Controller;

import D3981791.phase_4.Library.ActivityAddOnRepository;
import D3981791.phase_4.Library.ActivityRepository;
import D3981791.phase_4.Library.ItineraryAddonRepository;
import D3981791.phase_4.Model.*;
import D3981791.phase_4.View.ManagementScreen;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static D3981791.phase_4.Model.SaveItinerary.serializeItineraries;
import static java.lang.System.exit;

public class Phase4Run implements Serializable {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    // new feature!!!

    ActivityRepository activityRepository = new ActivityRepository();
    List<Activity> availableActivities = activityRepository.getAllActivities();

    ActivityAddOnRepository activityAddOnRepository = new ActivityAddOnRepository();
    List<ActivityAddOn> availableActivityAddOns = activityAddOnRepository.getAllActivityAddOns();

    ItineraryAddonRepository itineraryAddOnRepository = new ItineraryAddonRepository();
    List<ItineraryAddOn> availableItineraryAddOns = itineraryAddOnRepository.getAllItineraryAddOns();

    ItineraryInput generate = new ItineraryInput();

    Scanner scanner = new Scanner(System.in);

    System.out.println("");
    System.out.println("Menu: ");
    System.out.println("1. Create itinerary");
    System.out.println("2. View itinerary");
    System.out.println("3. Exit");

    Itinerary newItinerary = null;

    boolean finished = false;
    while (!finished) {
      System.out.print(">");
      int menuOption = scanner.nextInt();

      switch (menuOption) {
        case 1:
          newItinerary = generate.generateItinerary(availableActivities, availableActivityAddOns, availableItineraryAddOns);
          System.out.println("Itinerary created!");
          serializeItineraries(newItinerary);
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