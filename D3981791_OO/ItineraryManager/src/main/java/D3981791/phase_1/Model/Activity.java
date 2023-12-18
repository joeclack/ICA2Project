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

}