package net.phimai.tntlogger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtils {
    // Format for the log file name and timestamp
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String LOGS_FOLDER = "logs/TNT-Logger";

    private static final String PLACEMENT_PREFIX = "PLACEMENT";
    private static final String EXPLOSION_PREFIX = "EXPLOSION";

    public static void logToFile(String message, String eventType) {
        // Get today's date and format it
        String date = LocalDate.now().format(DATE_FORMATTER);
        String logFileName = String.format("TNT-%s.log", date);

        // Create the logs directory if it doesn't exist
        try {
            java.nio.file.Files.createDirectories(Paths.get(LOGS_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Full path to the log file
        java.nio.file.Path logFilePath = Paths.get(LOGS_FOLDER, logFileName);
        String eventTypePrefix;
        if(eventType == "placement"){
            eventTypePrefix = PLACEMENT_PREFIX;
        } else {
            eventTypePrefix = EXPLOSION_PREFIX;
        }

        // Add timestamp to the message
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String logMessage = String.format("[%s] [%s] %s", timestamp, eventTypePrefix, message);

        // Write the message to the file
        try (FileWriter writer = new FileWriter(logFilePath.toFile(), true)) {
            writer.write(logMessage + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}