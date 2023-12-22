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