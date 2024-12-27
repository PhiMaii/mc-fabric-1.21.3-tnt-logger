package net.phimai.tntlogger;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;

public class TNTPlacementLogger {
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {

            if (!world.isClient() && player.getStackInHand(hand).getItem() == Items.TNT) {
                if (TNTLoggerState.isLoggingEnabled) {

                    LogUtils.logToFile(player.getName().getString(), hitResult.getBlockPos().toShortString(), world.getRegistryKey().getValue().toString().split(":")[1], true);

                    if (TNTLoggerState.isChatOutputEnabled) {
                        LogUtils.logToChat(player.getName().getString(), hitResult.getBlockPos().toShortString(), world.getRegistryKey().getValue().toString().split(":")[1], true);
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}