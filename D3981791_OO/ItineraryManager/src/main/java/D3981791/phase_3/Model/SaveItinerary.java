/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package D3981791.phase_3.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveItinerary implements Serializable {

  static String filePath = "itineraries3.txt";

  public static void serializeItinerary(Itinerary itinerary) throws FileNotFoundException, IOException {

    try {
      FileOutputStream fileOut = new FileOutputStream(filePath);
      ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
      objOut.writeObject(itinerary);
      objOut.close();
      fileOut.close();
      System.out.println("Serialized data is saved in " + filePath);
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

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
      e.printStackTrace();
      System.out.println("Exception occurred while deserializing itineraries!");
    }

    return loadedItineraries;
  }

}
