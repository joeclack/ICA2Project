/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Controller;

import D3981791.phase_1.Library.ActivityAddOnRepository;
import D3981791.phase_1.Library.ActivityRepository;
import D3981791.phase_1.Library.ItineraryAddonRepository;
import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.Itinerary;
import D3981791.phase_1.Model.ItineraryAddOn;

import java.time.LocalDate;
import java.util.List;

public class Phase1Run {

  public static void main(String[] args) {
    ActivityRepository activityRepository = new ActivityRepository();
    List<Activity> availableActivities = activityRepository.getAllActivities();

    ActivityAddOnRepository activityAddOnRepository = new ActivityAddOnRepository();
    List<ActivityAddOn> availableActivitiyAddOns = activityAddOnRepository.getAllActivityAddOns();

    ItineraryAddonRepository itineraryAddOnRepository = new ItineraryAddonRepository();
    List<ItineraryAddOn> availableItineraryAddOns = itineraryAddOnRepository.getAllItineraryAddOns();


    Itinerary itinerary = new Itinerary();

    itinerary.setTotalAttendees(10);
    itinerary.setTotalActivities(2);
    itinerary.setLeadAttendeeFirstName("John");
    itinerary.setLeadAttendeeLastName("Doe");
    itinerary.setDate(LocalDate.now());


    itinerary.generateRefNum();

    Activity activity1 = availableActivities.get(0);
    activity1.getActivityAddOnsList().add(availableActivitiyAddOns.get(0));
    activity1.getActivityAddOnsList().add(availableActivitiyAddOns.get(2));
    itinerary.getActivitiesList().add(activity1);

    Activity activity2 = availableActivities.get(1);
    activity2.getActivityAddOnsList().add(availableActivitiyAddOns.get(0));
    activity2.getActivityAddOnsList().add(availableActivitiyAddOns.get(1));
    activity2.getActivityAddOnsList().add(availableActivitiyAddOns.get(2));
    itinerary.getActivitiesList().add(activity2);

    itinerary.getItineraryAddOnsList().add(availableItineraryAddOns.get(0));
    itinerary.getItineraryAddOnsList().add(availableItineraryAddOns.get(1));

    itinerary.calculateTotalItineraryAddOnsCost();
    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    System.out.println("Itinerary created!");
  }
}