/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase2;

import d3981791.phase2.model.ItineraryInput;
import d3981791.phase2.UI.ConsoleOutput;

public class RunPhase2 {

    /**
     * Main method that runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        System.out.println("Phase 2");

        // generate and print a new itinerary
        new ConsoleOutput().printItinerary(ItineraryInput.generateItinerary());
    }
}