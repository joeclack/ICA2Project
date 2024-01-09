/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PreBuiltItems class that stores information about pre-built items
 */

public class PreBuiltItems implements Serializable {
    /**
     * List of available activities
     */
    private final List<Activity> availableActivities = new ArrayList<>(Arrays.asList(
            new Activity(2000, "Paper Bridge building", "Building a bridge from paper", "Location1", 30, false),
            new Activity(4500, "Assault course", "SAS-style assault course", "Location2", 120, true),
            new Activity(3000, "Cooking", "Cookery classes", "Location3", 120, false)

    ));

    /**
     * List of available itinerary add-ons
     */
    private final List<ItineraryAddOn> availableItineraryAddOns = new ArrayList<>(Arrays.asList(
            new ItineraryAddOn("Accommodation", 1200),
            new ItineraryAddOn("Coffee/Tea Breaks", 200),
            new ItineraryAddOn("Lunch", 400)
    ));

    /**
     * List of available activity add-ons
     */
    private final List<ActivityAddOn> availableActivityAddOns = new ArrayList<>(Arrays.asList(
            new ActivityAddOn("Travel", 700),
            new ActivityAddOn("Insurance", 500),
            new ActivityAddOn("Photography", 1100)
    ));

    public List<Activity> getAvailableActivities() {
        return availableActivities;
    }

    public List<ActivityAddOn> getAvailableActivityAddOns() {
        return availableActivityAddOns;
    }

    public List<ItineraryAddOn> getAvailableItineraryAddOns() {
        return availableItineraryAddOns;
    }
}