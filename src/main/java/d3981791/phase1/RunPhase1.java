/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase1;

import d3981791.phase1.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RunPhase1 {

    public static void main(String[] args) {
        System.out.println(new RunPhase1().createItinerary("John", "Doe", 10, LocalDate.now()).getRefNumber());
    }

    /**
     * Creates an itinerary with pre-built items.
     *
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     * @param attendees The number of attendees.
     * @param date      The date of the itinerary.
     * @return The itinerary.
     */
    public Itinerary createItinerary(String firstName, String lastName, int attendees, LocalDate date) {
        PreBuiltItems preBuiltItems = new PreBuiltItems();

        Itinerary itinerary = new Itinerary(firstName, lastName, attendees, 2, date);

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

        return itinerary;
    }
}