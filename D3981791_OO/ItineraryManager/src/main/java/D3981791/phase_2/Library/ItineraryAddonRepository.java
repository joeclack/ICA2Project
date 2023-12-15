/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_2.Library;

import D3981791.phase_2.Model.ItineraryAddOn;

import java.util.ArrayList;
import java.util.List;

public class ItineraryAddonRepository {

  private List<ItineraryAddOn> availableItineraryAddOns;

  public ItineraryAddonRepository() {
    availableItineraryAddOns = new ArrayList<>();
    initializeAddOns();
  }

  private void initializeAddOns() {

    ItineraryAddOn IAO01 = new ItineraryAddOn("Accommodation", 1200);
    ItineraryAddOn IAO02 = new ItineraryAddOn("Coffee/Tea Breaks", 200);
    ItineraryAddOn IAO03 = new ItineraryAddOn("Lunch", 400);

    availableItineraryAddOns.add(IAO01);
    availableItineraryAddOns.add(IAO02);
    availableItineraryAddOns.add(IAO03);
  }

  public List<ItineraryAddOn> getAllItineraryAddOns() {
    return availableItineraryAddOns;
  }

}
