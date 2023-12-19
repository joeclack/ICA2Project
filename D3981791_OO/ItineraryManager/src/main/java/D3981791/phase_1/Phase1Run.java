/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1;

import D3981791.phase_1.Model.PreBuiltItems;
import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.ItineraryAddOn;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Phase1Run {

  public static void main(String[] args) {
    new Phase1Run().test();
  }

  public void test() {
    PreBuiltItems preBuiltItems = new PreBuiltItems();

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

    Itinerary itinerary = new Itinerary("John", "Doe", 10, 2, LocalDate.now());

    itinerary.generateRefNum();

    List<Activity> activities = preBuiltItems.getAvailableActivities();
    List<ActivityAddOn> activityAddOns = preBuiltItems.getAvailableActivityAddOns();
    List<ItineraryAddOn> itineraryAddOns = preBuiltItems.getAvailableItineraryAddOns();

    Activity activity1 = activities.get(0);
    activity1.setTime(LocalTime.of(10, 0));
    activity1.getActivityAddOnsList().add(activityAddOns.get(0));
    activity1.getActivityAddOnsList().add(activityAddOns.get(2));
    activity1.calculateTotalCost(10);
    itinerary.getActivitiesList().add(activity1);

    Activity activity2 = activities.get(1);
    activity2.setTime(LocalTime.of(12, 0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(1));
    activity2.getActivityAddOnsList().add(activityAddOns.get(2));
    activity2.calculateTotalCost(10);
    itinerary.getActivitiesList().add(activity2);

    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(0));
    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(1));

    itinerary.calculateTotalItineraryAddOnsCost();
    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    System.out.println("Itinerary created! Reference number: " + itinerary.getRefNumber());
  }
}