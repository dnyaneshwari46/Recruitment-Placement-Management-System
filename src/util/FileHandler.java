
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {

    public static void writeToFile(String fileName, String content) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {

            writer.println("RECRUITMENT AND PLACEMENT SYSTEM");
            writer.println("Report Generated: " +
                    LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

            writer.println("----------------------------------");
            writer.println(content);

            System.out.println("Report exported successfully!");
            System.out.println("File location: " + fileName);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
