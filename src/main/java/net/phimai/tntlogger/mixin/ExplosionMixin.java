package net.phimai.tntlogger.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionImpl;
import net.phimai.tntlogger.TNTLogger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExplosionImpl.class)
public class ExplosionMixin {
    @Inject(at = @At("HEAD"), method = "explode")
    private void init(CallbackInfo info) {
        TNTLogger.LOGGER.info("EXPLOSION MIXIN CALLED!");

        Explosion explosion = (Explosion) (Object) this;
        Entity entity = explosion.getCausingEntity();

        if (entity != null && "minecraft:tnt".equals(entity.getType().toString())) {
            TNTLogger.LOGGER.info(String.format("TNT explosion detected at %s, %s, %s",
                    explosion.getPosition().getX(),
                    explosion.getPosition().getY(),
                    explosion.getPosition().getZ()));


        }
    }
}


