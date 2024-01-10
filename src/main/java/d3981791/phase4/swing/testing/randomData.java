package d3981791.phase4.swing.testing;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class randomData {

    /**
     * Generates a random date
     *
     * @return The random date
     */
    public LocalDate dateGenerator() {
        // Generate a random date between 19/12/2023 and 31/12/2024
        int minDay = (int) LocalDate.of(2023, 12, 19).toEpochDay();
        int maxDay = (int) LocalDate.of(2024, 12, 31).toEpochDay();
        long randomDay = minDay + (int) (Math.random() * (maxDay - minDay));
        return LocalDate.ofEpochDay(randomDay);
    }

    /**
     * Generates a random attendee
     *
     * @return The random attendee
     */
    public int attendeesGenerator() {
        return (int) (Math.random() * 10) + 1;
    }

    /**
     * Generates a random first name
     *
     * @return The random first name
     */
    public String firstNameGenerator() {
        List<String> names = Arrays.asList("John", "Sam", "Jack", "Jill", "James", "Ben", "Henry", "Bobby", "Dave", "Bridget", "Alice", "Sarah", "Jane", "Kate", "Mary", "Emily", "Emma", "Olivia", "Jessica", "Sophie");
        return names.get((int) (Math.random() * names.size()));
    }

    /**
     * Generates a random surname
     *
     * @return The random surname
     */
    public String lastNameGenerator() {
        List<String> surnames = Arrays.asList("Doe", "Smith", "Jones", "Brown", "Wilson", "Taylor", "Johnson", "White", "Martin", "Anderson", "Thompson", "Nguyen", "Thomas", "Walker", "Harris", "Lee", "Ryan", "Robinson");
        return surnames.get((int) (Math.random() * surnames.size()));
    }
}