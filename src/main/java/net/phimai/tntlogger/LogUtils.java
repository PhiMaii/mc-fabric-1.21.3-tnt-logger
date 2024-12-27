package net.phimai.tntlogger;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String LOGS_FOLDER = "logs/TNT-Logger";

    private static final String PLACEMENT_PREFIX = "PLACEMENT";
    private static final String EXPLOSION_PREFIX = "EXPLOSION";


    public static void logToChat(String player, String coordinates, String dimension, Boolean isPlacement) {

        String message;
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        if (isPlacement) {
            message = String.format("§7[%s] §6[%s] §e%s §7placed a TNT at §9%s",
                    timestamp,
                    PLACEMENT_PREFIX,
                    player,
                    coordinates);
        } else {
            message = String.format("§7[%s] §4[%s] §e%s §7caused an explosion at §9%s",
                    timestamp,
                    EXPLOSION_PREFIX,
                    player,
                    coordinates);
        }

        if(!"overworld".equals(dimension)){
            message += String.format("(§7%s)", dimension);
        }

        MinecraftServer server = TNTLogger.getServer();

        for (ServerPlayerEntity serverPlayer : server.getPlayerManager().getPlayerList()) {
            if (serverPlayer.hasPermissionLevel(4)) {
                serverPlayer.sendMessage(Text.of(message), false);
            }
        }
    }


        public static void logToFile(String player, String coordinates, String dimension, Boolean isPlacement){

            String message;
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);

            if (isPlacement) {
                message = String.format("[%s] [%s] Player %s placed a TNT at %s in dimension %s",
                        timestamp,
                        PLACEMENT_PREFIX,
                        player,
                        coordinates,
                        dimension);
            } else {
                message = String.format("[%s] [%s] Player %s caused an explosion at %s in dimension %s",
                        timestamp,
                        EXPLOSION_PREFIX,
                        player,
                        coordinates,
                        dimension
                );
            }


            String date = LocalDate.now().format(DATE_FORMATTER);
            String logFileName = String.format("TNT-%s.log", date);

            try {
                java.nio.file.Files.createDirectories(Paths.get(LOGS_FOLDER));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            java.nio.file.Path logFilePath = Paths.get(LOGS_FOLDER, logFileName);

            try (FileWriter writer = new FileWriter(logFilePath.toFile(), true)) {
                writer.write(message + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
