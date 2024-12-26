package net.phimai.tntlogger;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class TNTPlacementLogger {
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (!world.isClient() && player.getStackInHand(hand).getItem() == Items.TNT) {
                if (TNTLoggerState.isLoggingEnabled) {
                    String message = String.format("%s placed a TNT at %s",
                            player.getName().getString(),
                            hitResult.getBlockPos().toShortString());

                    // Log the message to file
                    LogUtils.logToFile(message, "placement");

                    // Send message to OPs in chat if enabled
                    if (TNTLoggerState.isChatOutputEnabled) {
                        world.getPlayers().forEach(opPlayer -> {
                            if (opPlayer.hasPermissionLevel(4)) {
                                opPlayer.sendMessage(Text.of(message), false);
                            }
                        });
                    }
                }
            }
            return ActionResult.PASS;
        });
    }
}