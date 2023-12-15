/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_4.Model;

import java.io.Serializable;

public class AddOn implements Serializable {

  private String name;
  private int baseCost;

  /**
   *
   * @param name
   * @param baseCost
   */
  public AddOn(String name, int baseCost) {
    this.name = name;
    this.baseCost = baseCost;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(int baseCost) {
    this.baseCost = baseCost;
  }

}