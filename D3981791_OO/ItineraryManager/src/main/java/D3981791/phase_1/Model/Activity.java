/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Activity implements Serializable {

  private int baseCost;
  private String title;
  private String description;
  private String location;
  private LocalTime time;
  private int expectedDuration;
  private List<ActivityAddOn> activityAddOnsList;
  private int totalCost;
  private boolean requireInsurance;
  private boolean thirdPartyInsurance;

  /**
   *
   * @param baseCost
   * @param title
   * @param description
   * @param location
   * @param expectedDuration
   * @param requireInsurance
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

  /**
   *
   * @param selectedActivityAddOns
   */
  public void addAddOns(List<ActivityAddOn> selectedActivityAddOns) {
    this.activityAddOnsList.addAll(selectedActivityAddOns);
  }

  /**
   *
   * @param totalAttendees
   */
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

  public boolean isRequireInsurance() {
    return requireInsurance;
  }

  public void setRequireInsurance(boolean requireInsurance) {
    this.requireInsurance = requireInsurance;
  }

  public boolean isThirdPartyInsurance() {
    return thirdPartyInsurance;
  }

  public void setThirdPartyInsurance(boolean thirdPartyInsurance) {
    this.thirdPartyInsurance = thirdPartyInsurance;
  }

  
}