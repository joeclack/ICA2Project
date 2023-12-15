/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3.Controller;

import D3981791.phase_3.Library.ActivityAddOnRepository;
import D3981791.phase_3.Library.ActivityRepository;
import D3981791.phase_3.Library.ItineraryAddonRepository;
import D3981791.phase_3.Model.*;
import D3981791.phase_3.View.ConsoleUI;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static D3981791.phase_3.Model.SaveItinerary.deSerializeItineraries;
import static java.lang.System.exit;

public class Phase3Run implements Serializable {

  public static void main(String[] args) throws IOException, ClassNotFoundException {

    ActivityRepository activityRepository = new ActivityRepository();
    List<Activity> availableActivities = activityRepository.getAllActivities();

    ActivityAddOnRepository activityAddOnRepository = new ActivityAddOnRepository();
    List<ActivityAddOn> availableActivitiyAddOns = activityAddOnRepository.getAllActivityAddOns();

    ItineraryAddonRepository itineraryAddOnRepository = new ItineraryAddonRepository();
    List<ItineraryAddOn> availableItineraryAddOns = itineraryAddOnRepository.getAllItineraryAddOns();

    ItineraryInput generate = new ItineraryInput();

    Scanner scanner = new Scanner(System.in);
    ConsoleUI display = new ConsoleUI();

    display.menu();
    Itinerary newItinerary = null;
    boolean finished = false;
    while (!finished) {
      int menuOption = scanner.nextInt();

      switch (menuOption) {
        case 1:
          newItinerary = generate.generateItinerary(availableActivities, availableActivitiyAddOns, availableItineraryAddOns);
          display.fullItinerary(newItinerary);
          SaveItinerary.serializeItinerary(newItinerary);
          display.menu();
          break;
        case 2:
          List<Itinerary> itineraries = deSerializeItineraries();

          if (itineraries.isEmpty()) {
            System.out.println("No itineraries");
          } else {
            System.out.println("Itineraries: ");
            for (int i = 0; i < itineraries.size(); i++) {
              System.out.println((i + 1) + ". " + itineraries.get(i).getRefNumber());
            }
          }
          System.out.println("Enter the number of the itinerary you wish to view: ");
          System.out.print("> ");
          int viewItineraryNumber = scanner.nextInt();
          Itinerary selectedItinerary = itineraries.get(viewItineraryNumber - 1);
          display.fullItinerary(selectedItinerary);

          display.menu();
          break;
        case 3:
          exit(0);
          finished = true;
          break;
        default:
          System.out.println("Invalid option");
          break;
      }
    }

  }

}
