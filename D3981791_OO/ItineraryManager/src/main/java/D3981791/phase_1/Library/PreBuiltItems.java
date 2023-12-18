/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Library;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.ItineraryAddOn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreBuiltItems implements Serializable {

  private List<Activity> availableActivities;
  private List<ActivityAddOn> availableActivityAddOns;
  private List<ItineraryAddOn> availableItineraryAddOns;

  public PreBuiltItems() {
    availableActivities = new ArrayList<>();
    availableActivityAddOns = new ArrayList<>();
    availableItineraryAddOns = new ArrayList<>();

    initializeActivities();
    initializeItineraryAddOns();
    initializeActivityAddOns();
  }

  private void initializeActivities() {
    availableActivities.addAll(Arrays.asList(
            new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false),
            new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true),
            new Activity(3000, "Cooking", "Cookery classes", "Location2", 120, false)
    ));
  }

  private void initializeItineraryAddOns() {
    availableItineraryAddOns.addAll(Arrays.asList(
            new ItineraryAddOn("Accommodation", 1200),
            new ItineraryAddOn("Coffee/Tea Breaks", 200),
            new ItineraryAddOn("Lunch", 400)
    ));
  }

  private void initializeActivityAddOns() {
    availableActivityAddOns.addAll(Arrays.asList(
            new ActivityAddOn("Lunch", 400),
            new ActivityAddOn("Dinner", 800),
            new ActivityAddOn("Coffee/Tea Breaks", 200)
    ));

  }

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