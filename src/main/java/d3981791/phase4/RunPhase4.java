/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4;

import d3981791.phase1.model.Itinerary;
import d3981791.phase2.UI.ConsoleOutput;
import d3981791.phase2.model.ItineraryInput;
import d3981791.phase3.model.DataPersistence;
import d3981791.phase4.swing.frames.ManagementScreen;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RunPhase4 implements Serializable {

    private static final List<JFrame> openFrames = new ArrayList<>();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ConsoleOutput console = new ConsoleOutput();

        DataPersistence persistence = new DataPersistence();

        boolean finished = false;

        // displays the menu and handles the user input
        do{
            console.menu(4);
            switch (scanner.nextInt()) {
                // case 1: create a new itinerary
                case 1:
                    try {
                        // close all open frames to make sure there is only one management screen open
                        closeAllFrames();
                        // generate a new itinerary
                        Itinerary newItinerary = ItineraryInput.generateItinerary();
                        console.printItinerary(newItinerary);
                        // serialize the new itinerary
                        persistence.serializeItineraries(newItinerary);
                    } catch (Exception e) {
                        // if there is an error, print the error and exit the program
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Exiting...");
                        finished = true;
                    }
                    break;
                // case 2: open the management screen
                case 2:
                    try {
                        // close all open frames to make sure there is only one management screen open
                        closeAllFrames();
                        System.out.println("Opening management screen...");
                        JFrame managementScreen = new ManagementScreen();
                        // run the management screen on the event dispatch thread
                        java.awt.EventQueue.invokeLater(() -> managementScreen.setVisible(true));
                        // add the management screen to the list of open frames to track
                        openFrames.add(managementScreen);
                    } catch (Exception e) {
                        // if there is an error, print the error and exit the program
                        System.out.println("Error: " + e.getMessage());
                        System.out.println("Exiting...");
                        finished = true;
                    }
                    break;
                // case 3: exit the program
                case 3:
                    closeAllFrames();
                    System.out.println("Exiting...");
                    finished = true;
                    break;
                // default: invalid option
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (!finished);

    }

    /**
     * Closes all open frames.
     */
    private static void closeAllFrames() {
        for (JFrame frame : openFrames) {
            frame.dispose();
        }
        openFrames.clear();
    }

}