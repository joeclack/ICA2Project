/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2;

import D3981791.phase_1.Library.*;
import D3981791.phase_1.Model.*;
import D3981791.phase_2.Model.*;
import D3981791.phase_2.TextUI.*;

public class Phase2Run {

  public static void main(String[] args) {

    PreBuiltItems preBuiltItems = new PreBuiltItems();

    Itinerary itinerary = ItineraryInput.generateItinerary(preBuiltItems.getAvailableActivities(), preBuiltItems.getAvailableActivityAddOns(), preBuiltItems.getAvailableItineraryAddOns());

    ConsoleUI display = new ConsoleUI();
    display.fullItinerary(itinerary);
  }
}