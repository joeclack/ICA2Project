/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import D3981791.phase_1.Model.Activity;
import D3981791.phase_1.Model.ActivityAddOn;
import D3981791.phase_1.Model.ItineraryAddOn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreBuiltItems implements Serializable {
  private final List<Activity> availableActivities = new ArrayList<>(Arrays.asList(
          new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false),
          new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true),
          new Activity(3000, "Cooking", "Cookery classes", "Location2", 120, false)
  ));

  private final List<ItineraryAddOn> availableItineraryAddOns = new ArrayList<>(Arrays.asList(
          new ItineraryAddOn("Accommodation", 1200),
          new ItineraryAddOn("Coffee/Tea Breaks", 200),
          new ItineraryAddOn("Lunch", 400)
  ));

  private final List<ActivityAddOn> availableActivityAddOns = new ArrayList<>(Arrays.asList(
          new ActivityAddOn("Travel", 700),
          new ActivityAddOn("Insurance", 500),
          new ActivityAddOn("Photography", 1100)
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