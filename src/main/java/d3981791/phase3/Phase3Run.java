/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase3;

import d3981791.phase1.model.Itinerary;
import d3981791.phase1.model.Validation;
import d3981791.phase2.UI.ConsoleOutput;
import d3981791.phase2.model.ItineraryInput;
import d3981791.phase3.model.SaveItinerary;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Phase3Run implements Serializable {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ConsoleOutput display = new ConsoleOutput();

        SaveItinerary save = new SaveItinerary();

        System.out.println("Phase 3");

        display.menu();

        int menuOption = scanner.nextInt();

        switch (menuOption) {
            case 1:
                Itinerary newItinerary = ItineraryInput.generateItinerary();
                display.printItinerary(newItinerary);
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
                display.printItinerary(itineraries.get(viewItineraryNumber - 1));
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