package net.phimai.tntlogger.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "loadWorld")
    private void init(CallbackInfo info) {
        // This code is injected into the start of MinecraftServer.loadWorld()V
    }
}


 /*
import net.minecraft.entity.Entity;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class ExplosionMixin {
	private static final Logger LOGGER = LoggerFactory.getLogger("TNTLogger");

	@Inject(method = "explode", at = @At("HEAD"))
	private void onExplosion(CallbackInfo ci) {
		Explosion explosion = (Explosion) (Object) this;
		World world = explosion.world;
		Entity entity = explosion.getCausingEntity();

		if (entity != null && "minecraft:tnt".equals(entity.getType().toString())) {
			LOGGER.info("TNT explosion detected at {}, {}, {} in dimension {}",
					explosion.getX(), explosion.getY(), explosion.getZ(), world.getRegistryKey().getValue());
		}
	}
}


  */