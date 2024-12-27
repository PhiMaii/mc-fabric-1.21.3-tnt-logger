package net.phimai.tntlogger;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class TNTLoggerCommands {
    public static void registerCommands() {

        // Event for registering admin commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {

            //Command to toggle logging
            dispatcher.register(CommandManager.literal("toggleLogging")
                    .requires(source -> source.hasPermissionLevel(4)) // Only ops can execute
                    .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                            .executes(context -> {
                                boolean enabled = BoolArgumentType.getBool(context, "enabled");
                                TNTLoggerState.isLoggingEnabled = enabled;

                                context.getSource().sendFeedback(() -> Text.of("TNT Logging is now §l" + (enabled ? "§aenabled" : "§cdisabled")), true);
                                return 1;
                            })
                    )
            );

            // Command to toggle chat output
            dispatcher.register(CommandManager.literal("toggleChatOutput")
                    .requires(source -> source.hasPermissionLevel(4)) // Only ops can execute
                    .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                            .executes(context -> {
                                boolean enabled = BoolArgumentType.getBool(context, "enabled");
                                TNTLoggerState.isChatOutputEnabled = enabled;

                                context.getSource().sendFeedback(() -> Text.of("TNT Logging is now §l" + (enabled ? "§aenabled" : "§cdisabled")), true);
                                return 1;
                            })
                    )
            );
        });
    }
}