/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3.Library;

import D3981791.phase_3.Model.ActivityAddOn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityAddOnRepository implements Serializable {

  private List<ActivityAddOn> availableActivityAddOns;

  public ActivityAddOnRepository() {
    availableActivityAddOns = new ArrayList<>();
    initializeAddOns();
  }

  private void initializeAddOns() {

    ActivityAddOn AO01 = new ActivityAddOn("Travel", 700);
    ActivityAddOn AO02 = new ActivityAddOn("Insurance", 500);
    ActivityAddOn AO03 = new ActivityAddOn("Photography", 1100);

    availableActivityAddOns.add(AO01);
    availableActivityAddOns.add(AO02);
    availableActivityAddOns.add(AO03);

  }

  public List<ActivityAddOn> getAllActivityAddOns() {
    return availableActivityAddOns;
  }

}
