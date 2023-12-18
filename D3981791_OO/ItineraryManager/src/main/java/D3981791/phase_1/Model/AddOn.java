/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.Serializable;

public class AddOn implements Serializable {

  private final String name;
  private final int baseCost;

  public AddOn(String name, int baseCost) {
    this.name = name;
    this.baseCost = baseCost;
  }

  public String getName() {
    return name;
  }

  public int getBaseCost() {
    return baseCost;
  }

}