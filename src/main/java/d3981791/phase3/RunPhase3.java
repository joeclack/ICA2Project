/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase3;

import d3981791.phase1.model.Itinerary;
import d3981791.phase1.model.Validation;
import d3981791.phase2.UI.ConsoleOutput;
import d3981791.phase2.model.ItineraryInput;
import d3981791.phase3.model.DataPersistence;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class RunPhase3 implements Serializable {

    /**
     * Main method that runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ConsoleOutput console = new ConsoleOutput();

        DataPersistence persistence = new DataPersistence();

        boolean finished = false;

        // displays the menu and handles the user input
        do{
            console.menu(3);
            switch (scanner.nextInt()) {
                // case 1: create a new itinerary
                case 1:
                    try {
                        // generate a new itinerary and print it
                        Itinerary newItinerary = ItineraryInput.generateItinerary();
                        console.printItinerary(newItinerary);
                        // serialize the itinerary
                        persistence.serializeItineraries(newItinerary);
                    } catch (Exception e) {
                        // if there is an error, print the error and exit the program
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Exiting...");
                        finished = true;
                    }
                    break;
                // case 2: view an existing itinerary
                case 2:
                    List<Itinerary> itineraries = persistence.deSerializeItineraries();
                    // if there are no itineraries or the deSerialization threw an exception, break
                    if(itineraries.size() == 0){
                        break;
                    }
                    // print the list of itineraries if they are not empty
                    System.out.println("Itineraries: ");
                    for (int i = 0; i < itineraries.size(); i++) {
                        System.out.println((i + 1) + ". " + itineraries.get(i).getRefNumber());
                    }
                    // get the itinerary number to view from the user
                    int viewItineraryNumber = Validation.intOnly("Enter the number of the itinerary you wish to view: ", 1, itineraries.size());
                    // print the itinerary to the console
                    console.printItinerary(itineraries.get(viewItineraryNumber - 1));

                    break;
                // case 3: exit the program
                case 3:
                    System.out.println("Exiting...");
                    finished = true;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!finished);

    }

}