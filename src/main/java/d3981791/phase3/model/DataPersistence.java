/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase3.model;

import d3981791.phase1.model.Itinerary;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.exit;


public class DataPersistence implements Serializable {

    private final String filePath = "tineraries.txt";

    /**
     * Writes the object to the file.
     *
     * @param list The list of itineraries.
     */
    private void writeObj(List<Itinerary> list) {
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
     * @throws IOException            If the file is empty or corrupted.
     * @throws ClassNotFoundException If the class is not found.
     * @throws FileNotFoundException If the file is not found.
     */
    public List<Itinerary> deSerializeItineraries() {
        List<Itinerary> loadedItineraries = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileInputStream fileIn = null;
                ObjectInputStream objIn = null;
                try {
                    fileIn = new FileInputStream(filePath);
                    objIn = new ObjectInputStream(fileIn);
                } catch (IOException e) {
                    if(file.length() == 0) {
                        throw new IOException("File empty. Please create the file or add a new itinerary.");
                    }
                    throw new IOException("File corrupted. Please delete the file and try again.");
                }

                Object obj = objIn.readObject();
                if (obj instanceof List) {
                    loadedItineraries = (List<Itinerary>) obj;
                }
                objIn.close();
                fileIn.close();
            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());

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