/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PreBuiltItems class that stores information about pre-built items
 */

public class PreBuiltItems implements Serializable {
  /**
   * List of available activities
   */
  private final List<Activity> availableActivities = new ArrayList<>(Arrays.asList(
          new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false),
          new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true),
          new Activity(3000, "Cooking", "Cookery classes", "Location3", 120, false),
          new Activity(3800, "Rock Climbing", "Indoor rock climbing experience", "Location7", 90, true),
          new Activity(4200, "Yoga Retreat", "Mindful yoga and meditation sessions", "Location8", 120, false),
          new Activity(2600, "Archery Class", "Learn archery skills with professional instructors", "Location9", 60, false)

  ));

  /**
   * List of available itinerary add-ons
   */
  private final List<ItineraryAddOn> availableItineraryAddOns = new ArrayList<>(Arrays.asList(
          new ItineraryAddOn("Accommodation", 1200),
          new ItineraryAddOn("Coffee/Tea Breaks", 200),
          new ItineraryAddOn("Lunch", 400),
          new ItineraryAddOn("Dinner Buffet", 800),
          new ItineraryAddOn("City Sightseeing Tour", 1000),
          new ItineraryAddOn("Airport Transfer", 1200)
  ));

  /**
   * List of available activity add-ons
   */
  private final List<ActivityAddOn> availableActivityAddOns = new ArrayList<>(Arrays.asList(
          new ActivityAddOn("Travel", 700),
          new ActivityAddOn("Insurance", 500),
          new ActivityAddOn("Photography", 1100),
          new ActivityAddOn("Scuba Diving Gear Rental", 1200),
          new ActivityAddOn("Adventure Photoshoot", 950),
          new ActivityAddOn("VIP Experience", 2000)
  ));

  public List<Activity> getAvailableActivities() {
    return availableActivities;
  }

  public List<ActivityAddOn> getAvailableActivityAddOns() {
    return availableActivityAddOns;
  }

  public List<ItineraryAddOn> getAvailableItineraryAddOns() {
    return availableItineraryAddOns;
  }
}