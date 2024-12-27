package net.phimai.tntlogger;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTLogger implements ModInitializer {

    public static final String MOD_PREFIX = "[TNT LOGGER] ";
    public static final String MOD_ID = "tnt-logger";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static MinecraftServer SERVER_INSTANCE;

    public static MinecraftServer getServer() {
        return SERVER_INSTANCE;
    }

    @Override
    public void onInitialize() {

        LOGGER.info(MOD_PREFIX + "===== STARTING TNT-Logger MOD =====");

        try {

            ServerLifecycleEvents.SERVER_STARTED.register(server -> SERVER_INSTANCE = server);
            ServerLifecycleEvents.SERVER_STOPPED.register(server -> SERVER_INSTANCE = null);

            TNTPlacementLogger.register();
            TNTLoggerCommands.registerCommands();

        } catch (Exception e) {

            LOGGER.error("===== ERROR while initializing TNT-Logger");

        }

        LOGGER.info(MOD_PREFIX + "===== TNT Logger Mod initialized =====");

    }
}