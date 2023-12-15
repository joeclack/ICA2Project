/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3;

import D3981791.phase_1.Model.*;
import D3981791.phase_1.Library.*;
import D3981791.phase_2.Model.ItineraryInput;
import D3981791.phase_2.TextUI.*;
import D3981791.phase_3.Model.SaveItinerary;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

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
    SaveItinerary save = new SaveItinerary();

    System.out.println(display.menu());
    Itinerary newItinerary = null;
    boolean finished = false;
    while (!finished) {
      int menuOption = scanner.nextInt();

      switch (menuOption) {
        case 1:
          newItinerary = generate.generateItinerary(availableActivities, availableActivitiyAddOns, availableItineraryAddOns);
          display.fullItinerary(newItinerary);
          save.serializeItineraries(newItinerary);
          System.out.println(display.menu());
          break;
        case 2:
          List<Itinerary> itineraries = save.deSerializeItineraries();

          if (itineraries.isEmpty()) {
            System.out.println("No itineraries");
            break;
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

          System.out.println(display.menu());
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