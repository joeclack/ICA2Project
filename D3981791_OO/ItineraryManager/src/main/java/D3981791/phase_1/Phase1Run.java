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
import D3981791.phase_3.Model.SaveItinerary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Phase1Run {

  public static void main(String[] args) {
//    for(int i = 0; i < 10; i++) {
//      new Phase1Run().test();
//    }
    for(int i = 0; i < 50; i++) {
      new Phase1Run().testWithDifferentAttendeeNames(randomFirstnameGenerator(), randomSurnameGenerator(), randomAttendeeGenerator(), randomDateGenerator());
    }

  }


  public static LocalDate randomDateGenerator() {
      int minDay = (int) LocalDate.of(2023, 12, 19).toEpochDay();
      int maxDay = (int) LocalDate.of(2024, 12, 31).toEpochDay();
      long randomDay = minDay + (int) (Math.random() * (maxDay - minDay));
      return LocalDate.ofEpochDay(randomDay);
  }

  public static int randomAttendeeGenerator() {
    return (int) (Math.random() * 10) + 1;
  }


  public static String randomFirstnameGenerator() {
    List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill", "James", "Ben", "Bella", "Bobby", "Bart", "Bridget", "Brenda");
    return names.get((int) (Math.random() * names.size()));
  }

  public static String randomSurnameGenerator() {
    List<String> surnames = Arrays.asList("Doe", "Smith", "Jones", "Brown", "Wilson", "Taylor", "Johnson", "White", "Martin", "Anderson", "Thompson", "Nguyen", "Thomas", "Walker", "Harris", "Lee", "Ryan", "Robinson", "Kelly", "King");
    return surnames.get((int) (Math.random() * surnames.size()));
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
    itinerary.getActivitiesList().add(activity1);

    Activity activity2 = activities.get(1);
    activity2.setTime(LocalTime.of(12, 0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(1));
    activity2.getActivityAddOnsList().add(activityAddOns.get(2));
    itinerary.getActivitiesList().add(activity2);

    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(0));
    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(1));

    itinerary.calculateTotalItineraryAddOnsCost();
    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    SaveItinerary save = new SaveItinerary();
    save.serializeItineraries(itinerary);
    System.out.println("Itinerary created! Reference number: " + itinerary.getRefNumber());
  }

  public void testWithDifferentAttendeeNames(String firstName, String lastName, int attendees, LocalDate date) {
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
    itinerary.getActivitiesList().add(activity1);

    Activity activity2 = activities.get(1);
    activity2.setTime(LocalTime.of(12, 0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(0));
    activity2.getActivityAddOnsList().add(activityAddOns.get(1));
    activity2.getActivityAddOnsList().add(activityAddOns.get(2));
    itinerary.getActivitiesList().add(activity2);

    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(0));
    itinerary.getItineraryAddOnsList().add(itineraryAddOns.get(1));

    itinerary.calculateTotalItineraryAddOnsCost();
    itinerary.calculateItineraryCost();
    itinerary.generateRefNum();

    SaveItinerary save = new SaveItinerary();
    save.serializeItineraries(itinerary);
    System.out.println("Itinerary created! Reference number: " + itinerary.getRefNumber());
  }
}