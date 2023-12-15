/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.Library;

import D3981791.phase_2.Model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepository {

  private List<Activity> availableActivities;

  public ActivityRepository() {
    availableActivities = new ArrayList<>();

    initializeActivities();
  }

  private void initializeActivities() {
    Activity PB01 = new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30);
    Activity AC02 = new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120);
    Activity CC03 = new Activity(3000, "Cooking", "Cookery classes", "Location2", 120);

    availableActivities.add(PB01);
    availableActivities.add(AC02);
    availableActivities.add(CC03);

  }

  public List<Activity> getAllActivities() {
    return availableActivities;
  }

}
