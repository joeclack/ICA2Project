/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d3981791.phase4.swing.model;

import java.text.DecimalFormat;

public class Format {

    /**
     * Formats the cost to a string
     *
     * @param cost the cost to format
     * @return the formatted cost
     */
    public static String formatCost(int cost) {
        double costDouble = cost / 100.0;

        DecimalFormat decimalFormat = new DecimalFormat("£0.00");

        return decimalFormat.format(costDouble);
    }

}