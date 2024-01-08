/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase_3.model;

import d3981791.phase_1.model.Itinerary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveItinerary implements Serializable {

    static final String filePath = "itineraries.txt";

    private static void writeObj(List<Itinerary> list) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            objOut.writeObject(list);
            objOut.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Error: " + i.getMessage());
        }
    }

    /**
     * Serializes the itinerary.
     *
     * @param itinerary The itinerary to be serialized.
     */
    public void serializeItineraries(Itinerary itinerary) {
        List<Itinerary> existingItineraries = deSerializeItineraries();
        existingItineraries.add(itinerary);

        writeObj(existingItineraries);
    }

    /**
     * Deserializes the itinerary.
     *
     * @return The deserialized itinerary.
     */
    public List<Itinerary> deSerializeItineraries() {
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
            System.out.println("Error: " + e.getMessage());
        }

        return loadedItineraries;
    }

    /**
     * Deletes the itinerary.
     *
     * @param index           The index of the itinerary to be deleted.
     * @param itinerariesList The list of itineraries.
     */
    public void deleteItinerary(int index, List<Itinerary> itinerariesList) {
        for (int a = 0; a < itinerariesList.size(); a++) {
            if (a == index) {
                itinerariesList.remove(a);

                writeObj(itinerariesList);
            }

        }
    }

    /**
     * Deletes all the itineraries.
     *
     * @param itinerariesList The list of itineraries.
     */
    public void deleteAll(List<Itinerary> itinerariesList) {
        itinerariesList.clear();

        writeObj(itinerariesList);
    }
}