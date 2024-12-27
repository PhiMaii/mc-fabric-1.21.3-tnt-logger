package net.phimai.tntlogger.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import net.phimai.tntlogger.LogUtils;
import net.phimai.tntlogger.TNTLoggerState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.Objects;

@Mixin(ExplosionImpl.class)
public class ExplosionMixin {
    @Inject(at = @At("HEAD"), method = "explode")
    private void init(CallbackInfo info) {
//        TNTLogger.LOGGER.info("EXPLOSION MIXIN CALLED!");

        Explosion explosion = (Explosion) this;
        Entity causingEntity = explosion.getCausingEntity();
        World world = explosion.getWorld();


        if (TNTLoggerState.isLoggingEnabled) {
            if (causingEntity != null) {
                if ("entity.minecraft.tnt".equals(Objects.requireNonNull(explosion.getEntity()).getType().toString())) {

                    int explosionX = (int) Math.floor(explosion.getPosition().getX());
                    int explosionY = (int) Math.floor(explosion.getPosition().getY());
                    int explosionZ = (int) Math.floor(explosion.getPosition().getZ());

                    String blockCords = String.format("%s, %s, %s", explosionX, explosionY, explosionZ);

                    LogUtils.logToFile(causingEntity.getName().getString(), blockCords, world.getRegistryKey().getValue().toString().split(":")[1], false);

                    if (TNTLoggerState.isChatOutputEnabled) {
                        LogUtils.logToChat(causingEntity.getName().getString(), blockCords, world.getRegistryKey().getValue().toString().split(":")[1], false);
                    }
                }

            }
        }
    }
}



