/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Library;

import D3981791.phase_1.Model.Activity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository {

  private final List<Activity> availableActivities;

  public ActivityRepository() {
    availableActivities = new ArrayList<>();

    initializeActivities();
  }

  private void initializeActivities() {

    Activity BB01 = new Activity(2000, "Bridge building", "Building a bridge from paper", "Location1", LocalTime.of(13, 0), 30);
    Activity AC02 = new Activity(4500, "Assault course", "SAS-style assault course", "Location2", LocalTime.of(9, 0), 120);
    Activity CC03 = new Activity(3000, "Cooking", "Cookery classes", "Location2", LocalTime.of(14, 0), 120);

    availableActivities.add(BB01);
    availableActivities.add(AC02);
    availableActivities.add(CC03);

  }

  public List<Activity> getAllActivities() {
    return availableActivities;
  }
}
