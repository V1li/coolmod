package dev.vili.mixin;

import dev.vili.Main;
import dev.vili.TPS;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Inject(method = "render", at = @At("RETURN"), cancellable = true)
	private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
		if (Main.mc.player != null && Main.mc.world != null && !Main.mc.options.debugEnabled) {
			float yaw = Main.mc.player.getYaw() % 360;
			float pitch = Main.mc.player.getPitch();
			String fps = Main.mc.fpsDebugString.split(" ", 2)[0];
			String player_info = String.format("X: %.2f / Y: %.2f / Z: %.2f / Yaw: %.2f / Pitch: %.2f",
					Main.mc.player.getX(), Main.mc.player.getY(), Main.mc.player.getZ(),
					yaw, pitch);
			String game_info = String.format("FPS: %s / TPS: %.2f / Ping: %s", fps, TPS.INSTANCE.ticks,
					Main.mc.getCurrentServerEntry() == null ? "0" : Main.mc.getCurrentServerEntry().ping);

			context.drawTextWithShadow(Main.mc.textRenderer, player_info, 1, 1, Formatting.WHITE.getColorValue());
			context.drawTextWithShadow(Main.mc.textRenderer, game_info, 1, 11, Formatting.WHITE.getColorValue());
		}
	}
}