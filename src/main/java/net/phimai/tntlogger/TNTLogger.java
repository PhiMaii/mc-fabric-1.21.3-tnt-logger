package net.phimai.tntlogger;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTLogger implements ModInitializer {

    public static final String MOD_PREFIX = "[TNT LOGGER] ";
    public static final String MOD_ID = "tnt-logger";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitialize() {
        LOGGER.info(MOD_PREFIX + "===== STARTING TNT-Logger MOD =====");
        try {

            TNTPlacementLogger.register();
            //TNTExplosionLogger.register();
            TNTLoggerCommands.registerCommands();

        } catch (Exception e) {

            LOGGER.error("===== ERROR while initializing TNT-Logger");
            
        }

        LOGGER.info(MOD_PREFIX + "===== TNT Logger Mod initialized =====");

    }
}