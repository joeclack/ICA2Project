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
    Activity PB01 = new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false);
    Activity AC02 = new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true);
    Activity CC03 = new Activity(3000, "Cooking", "Cookery classes", "Location2", 120, false);

    availableActivities.add(PB01);
    availableActivities.add(AC02);
    availableActivities.add(CC03);

  }

  private void initializeItineraryAddOns() {

    ItineraryAddOn IAO01 = new ItineraryAddOn("Accommodation", 1200);
    ItineraryAddOn IAO02 = new ItineraryAddOn("Coffee/Tea Breaks", 200);
    ItineraryAddOn IAO03 = new ItineraryAddOn("Lunch", 400);

    availableItineraryAddOns.add(IAO01);
    availableItineraryAddOns.add(IAO02);
    availableItineraryAddOns.add(IAO03);
  }

  private void initializeActivityAddOns() {

    ActivityAddOn AO01 = new ActivityAddOn("Travel", 700);
    ActivityAddOn AO02 = new ActivityAddOn("Insurance", 500);
    ActivityAddOn AO03 = new ActivityAddOn("Photography", 1100);

    availableActivityAddOns.add(AO01);
    availableActivityAddOns.add(AO02);
    availableActivityAddOns.add(AO03);

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