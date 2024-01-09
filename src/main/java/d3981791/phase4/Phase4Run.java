/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4;

import d3981791.phase1.model.Itinerary;
import d3981791.phase2.UI.ConsoleOutput;
import d3981791.phase2.model.ItineraryInput;
import d3981791.phase3.model.SaveItinerary;
import d3981791.phase4.swing.frames.ManagementScreen;

import java.io.Serializable;
import java.util.Scanner;

import static java.lang.System.exit;

public class Phase4Run implements Serializable {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ConsoleOutput display = new ConsoleOutput();

        System.out.println("Phase 4");

        System.out.println("\n" +
                "                                                                                                                                                                                   \n" +
                "88888888888                          88           88                                       db                             88               88           88                         \n" +
                "88                                   \"\"    ,d     \"\"                                      d88b                     ,d     \"\"               \"\"    ,d     \"\"                         \n" +
                "88                                         88                                            d8'`8b                    88                            88                                \n" +
                "88aaaaa      8b,     ,d8  ,adPPYba,  88  MM88MMM  88  8b,dPPYba,    ,adPPYb,d8          d8'  `8b      ,adPPYba,  MM88MMM  88  8b       d8  88  MM88MMM  88   ,adPPYba,  ,adPPYba,  \n" +
                "88\"\"\"\"\"       `Y8, ,8P'  a8\"     \"\"  88    88     88  88P'   `\"8a  a8\"    `Y88         d8YaaaaY8b    a8\"     \"\"    88     88  `8b     d8'  88    88     88  a8P_____88  I8[    \"\"  \n" +
                "88              )888(    8b          88    88     88  88       88  8b       88        d8\"\"\"\"\"\"\"\"8b   8b            88     88   `8b   d8'   88    88     88  8PP\"\"\"\"\"\"\"   `\"Y8ba,   \n" +
                "88            ,d8\" \"8b,  \"8a,   ,aa  88    88,    88  88       88  \"8a,   ,d88       d8'        `8b  \"8a,   ,aa    88,    88    `8b,d8'    88    88,    88  \"8b,   ,aa  aa    ]8I  \n" +
                "88888888888  8P'     `Y8  `\"Ybbd8\"'  88    \"Y888  88  88       88   `\"YbbdP\"Y8      d8'          `8b  `\"Ybbd8\"'    \"Y888  88      \"8\"      88    \"Y888  88   `\"Ybbd8\"'  `\"YbbdP\"'  \n" +
                "                                                                    aa,    ,88                                                                                                     \n" +
                "                                                                     \"Y8bbdP\"                                                                                                      \n");

        display.menu();

        System.out.print(">");
        int menuOption = scanner.nextInt();


        switch (menuOption) {
            case 1:
                Itinerary newItinerary = ItineraryInput.generateItinerary();
                display.printItinerary(newItinerary);
                new SaveItinerary().serializeItineraries(newItinerary);
                break;
            case 2:
                java.awt.EventQueue.invokeLater(() -> new ManagementScreen().setVisible(true));
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