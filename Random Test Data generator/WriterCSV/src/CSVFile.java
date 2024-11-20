import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

public class CSVFile {
    private static final String[] foodItems = {"Burger", "Chips", "Pizza", "Pasta", "Salad", "Soda", "Ice Cream", "Sandwich"};
    private static final Random random = new Random();

    public static void main(String[] args) {
        String filePath = "random_data.csv";
        int totalRows = 700000;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header
            writer.write("ID,Category,FoodItem,Price,Cost");
            writer.newLine();

            HashSet<String> uniqueEntries = new HashSet<>();
            int id = 1;

            while (uniqueEntries.size() < totalRows) {
                String category = Integer.toString(random.nextInt(10) + 1); // Random category between 1 and 10
                String foodItem = foodItems[random.nextInt(foodItems.length)]; // Random food item
                String price = String.format("%.4f", random.nextDouble() * 100); // Random price between 0 and 100
                String cost = String.format("%.4f", random.nextDouble() * 100); // Random cost between 0 and 100

                String entry = id + "," + category + "," + foodItem + "," + price + "," + cost;

                // Ensure the entry is unique
                if (uniqueEntries.add(entry)) {
                    writer.write(entry);
                    writer.newLine();
                    id++;
                }
            }

            System.out.println("Generated " + totalRows + " unique rows in " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
