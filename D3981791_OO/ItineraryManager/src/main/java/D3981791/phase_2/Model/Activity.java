/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Activity {

  private int baseCost;
  private String title;
  private String description;
  private String location;
  private LocalTime time;
  private int expectedDuration;
  private List<ActivityAddOn> activityAddOnsList;
  private int totalCost;

  public Activity(int baseCost, String title, String description,
          String location, int expectedDuration) {
    this.baseCost = baseCost;
    this.title = title;
    this.description = description;
    this.location = location;
    this.expectedDuration = expectedDuration;
    this.activityAddOnsList = new ArrayList<>();
  }

  public void addAddOns(List<ActivityAddOn> selectedActivityAddOns) {
    this.activityAddOnsList.addAll(selectedActivityAddOns);
  }

  public void calculateTotalCost() {
    int cost = 0;

    for (ActivityAddOn addOnActivity : activityAddOnsList) {
      cost += addOnActivity.getBaseCost();
    }
    cost += baseCost;

    this.totalCost = cost;
  }

  public int getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(int baseCost) {
    this.baseCost = baseCost;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public int getExpectedDuration() {
    return expectedDuration;
  }

  public void setExpectedDuration(int expectedDuration) {
    this.expectedDuration = expectedDuration;
  }

  public List<ActivityAddOn> getActivityAddOnsList() {
    return activityAddOnsList;
  }

  public void setActivityAddOnsList(List<ActivityAddOn> activityAddOnsList) {
    this.activityAddOnsList = activityAddOnsList;
  }

  public int getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

}
