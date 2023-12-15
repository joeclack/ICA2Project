/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_1.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveItinerary implements Serializable {

  static String filePath = "itineraries.txt";

  /**
   *
   * @param itinerary
   */
  public static void serializeItineraries(Itinerary itinerary) {
    List<Itinerary> existingItineraries = deSerializeItineraries();
    existingItineraries.add(itinerary);

    try {
      FileOutputStream fileOut = new FileOutputStream(filePath);
      ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

      objOut.writeObject(existingItineraries);
      objOut.close();
      fileOut.close();
    } catch (IOException i) {

    }
  }

  /**
   *
   * @return
   */
  public static List<Itinerary> deSerializeItineraries() {
    List<Itinerary> loadedItineraries = new ArrayList<>();
    try {
      File file = new File(filePath);
      if (file.exists()) {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);

        Object obj = objIn.readObject();
        if (obj instanceof List) {
          loadedItineraries = (List<Itinerary>) obj;
        }
        objIn.close();
        fileIn.close();
      }
    } catch (IOException | ClassNotFoundException e) {

    }

    return loadedItineraries;
  }

  /**
   *
   * @param index
   * @param itinerariesList
   */
  public static void deleteItinerary(int index, List<Itinerary> itinerariesList) {
    for(int a = 0; a<itinerariesList.size(); a++) {
      if(a==index) {
        itinerariesList.remove(a);
        
        try {
          FileOutputStream fileOut = new FileOutputStream(filePath);
          ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

          objOut.writeObject(itinerariesList);
          objOut.close();
          fileOut.close();
        } catch (IOException i) {

        }
      } 
      
      
    }
    
  }
}