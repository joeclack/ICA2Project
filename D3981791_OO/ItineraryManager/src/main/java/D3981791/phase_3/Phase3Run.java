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

    PreBuiltItems preBuiltItems = new PreBuiltItems();

    Scanner scanner = new Scanner(System.in);

    ConsoleUI display = new ConsoleUI();

    SaveItinerary save = new SaveItinerary();

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

    int menuOption = scanner.nextInt();

    switch (menuOption) {
      case 1:
        Itinerary newItinerary = ItineraryInput.generateItinerary();
        display.fullItinerary(newItinerary);
        save.serializeItineraries(newItinerary);
        display.menu();
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