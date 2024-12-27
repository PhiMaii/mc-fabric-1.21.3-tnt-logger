package net.phimai.tntlogger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

import net.minecraft.server.MinecraftServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTLogger implements ModInitializer {

    // Project variables
    public static final String MOD_PREFIX = "[TNT LOGGER] ";
    public static final String MOD_ID = "tnt-logger";

    // Registering a logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Making the server instance accessible to other classes
    public static MinecraftServer SERVER_INSTANCE;
    public static MinecraftServer getServer() {
        return SERVER_INSTANCE;
    }

    // Method is called upon server start and initialises the mod
    @Override
    public void onInitialize() {

        // Log that initialisation has begun
        LOGGER.info(MOD_PREFIX + "===== STARTING TNT-Logger MOD =====");
        LOGGER.info(MOD_PREFIX + "=====  Developed by: @PhiMai  =====");
        LOGGER.info(MOD_PREFIX + "=====      Version 0.0.1      =====");


        try {
            // Get a server instance
            ServerLifecycleEvents.SERVER_STARTED.register(server -> SERVER_INSTANCE = server);
            ServerLifecycleEvents.SERVER_STOPPED.register(server -> SERVER_INSTANCE = null);

            // Register custom events and commands
            TNTPlacementLogger.register();
            TNTLoggerCommands.registerCommands();

            // Catch any errors and log them
        } catch (Exception e) {
            LOGGER.error("===== ERROR while initializing TNT-Logger =====");
        }

        // If everything worked, print it in the console
        LOGGER.info(MOD_PREFIX + "===== TNT Logger Mod initialized =====");
    }
}