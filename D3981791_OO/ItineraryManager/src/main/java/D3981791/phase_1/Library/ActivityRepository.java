/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Library;

import D3981791.phase_1.Model.Activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository implements Serializable {

  private List<Activity> availableActivities;

  public ActivityRepository() {
    availableActivities = new ArrayList<>();

    initializeActivities();
  }

  private void initializeActivities() {
    Activity PB01 = new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false);
    Activity AC02 = new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true);
    Activity CC03 = new Activity(3000, "Cooking", "Cookery classes", "Location2", 120, false);

    availableActivities.add(PB01);
    availableActivities.add(AC02);
    availableActivities.add(CC03);

  }

  public List<Activity> getAllActivities() {
    return availableActivities;
  }

}