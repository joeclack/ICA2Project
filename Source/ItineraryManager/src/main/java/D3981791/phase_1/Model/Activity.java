/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity class that stores information about an activity.
 */

public class Activity {
  private final int baseCost;
  private final String title;
  private final String description;
  private final String location;
  private LocalTime time;
  private final int expectedDuration;
  private final List<ActivityAddOn> activityAddOnsList;
  private int totalCost;
  private final boolean requireInsurance;
  private boolean thirdPartyInsurance;

  /**
   * Constructor for Activity class.
   * @param baseCost The base cost of the activity.
   * @param title The title of the activity.
   * @param description The description of the activity.
   * @param location The location of the activity.
   * @param expectedDuration The expected duration of the activity.
   * @param requireInsurance Whether the activity requires insurance.
   */

  public Activity(int baseCost, String title, String description,
                  String location, int expectedDuration, boolean requireInsurance) {
    this.baseCost = baseCost;
    this.title = title;
    this.description = description;
    this.location = location;
    this.expectedDuration = expectedDuration;
    this.activityAddOnsList = new ArrayList<>();
    this.requireInsurance = requireInsurance;
  }

  public void calculateTotalCost(int totalAttendees) {
    int cost = 0;

    for (ActivityAddOn addOnActivity : activityAddOnsList) {
      cost += addOnActivity.getBaseCost();
    }
    cost += baseCost;

    this.totalCost = cost * totalAttendees;
  }

  public int getBaseCost() {
    return baseCost;
  }

  public String getTitle() {
    return title;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }
  public List<ActivityAddOn> getActivityAddOnsList() {
    return activityAddOnsList;
  }

  public int getTotalCost() {
    return totalCost;
  }

  public boolean isRequireInsurance() {
    return requireInsurance;
  }

  public boolean isThirdPartyInsurance() {
    return thirdPartyInsurance;
  }

  public void setThirdPartyInsurance(boolean thirdPartyInsurance) {
    this.thirdPartyInsurance = thirdPartyInsurance;
  }

  public String getDescription() {
    return description;
  }

  public String getLocation() {
    return location;
  }

  public int getExpectedDuration() {
    return expectedDuration;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }
}