/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.View;

import D3981791.phase_4.Model.Itinerary;

import java.text.DecimalFormat;

/**
 *
 * @author d3981791
 */
public class Format {
  /**
   *
   * @param itinerary
   * @return
   */
  public static String formatItineraryCost(Itinerary itinerary) {
    double cost = itinerary.getItineraryCost() / 100.0;
    
    DecimalFormat decimalFormat = new DecimalFormat("£0.00");
    String formattedCost = decimalFormat.format(cost);
    
    return formattedCost;
  }

}