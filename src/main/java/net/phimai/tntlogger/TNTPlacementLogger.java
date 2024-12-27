package net.phimai.tntlogger;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class TNTPlacementLogger {
    public static void register() {

        // Callback for block placement is registered
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            // Only execute code below if the mod is running on a server
            if (!world.isClient() && player.getStackInHand(hand).getItem() == Items.TNT) {
                // Check if logging is enabled
                if (TNTLoggerState.isLoggingEnabled) {

                    //Log to the log files
                    LogUtils.logToFile(player.getName().getString(), hitResult.getBlockPos().toShortString(), world.getRegistryKey().getValue().toString().split(":")[1], true);

                    // Check if chat output is enabled
                    if (TNTLoggerState.isChatOutputEnabled) {
                        // Log to the op`s chat
                        LogUtils.logToChat(player.getName().getString(), hitResult.getBlockPos().toShortString(), world.getRegistryKey().getValue().toString().split(":")[1], true);
                    }
                }
            }
            // Return success in order for animations, sounds etc. to play
            return ActionResult.PASS;
        });
    }
}