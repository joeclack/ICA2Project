/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3;

import D3981791.phase_1.Model.*;
import D3981791.phase_2.Model.ItineraryInput;
import D3981791.phase_2.TextUI.*;
import D3981791.phase_3.Model.SaveItinerary;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Phase3Run implements Serializable {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    ConsoleUI display = new ConsoleUI();

    SaveItinerary save = new SaveItinerary();

    System.out.println("Phase 3");

    display.menu();

    int menuOption = scanner.nextInt();

    switch (menuOption) {
      case 1:
        Itinerary newItinerary = ItineraryInput.generateItinerary();
        display.fullItinerary(newItinerary);
        save.serializeItineraries(newItinerary);
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
        int viewItineraryNumber = Validation.intOnly("Enter the number of the itinerary you wish to view: ", 1, itineraries.size());
        display.fullItinerary(itineraries.get(viewItineraryNumber - 1));
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