package dev.vili.listeners;

import dev.vili.Main;
import dev.vili.event.events.PacketEvent;
import dev.vili.haiku.eventbus.HaikuSubscribe;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.text.Text;

public class DeathListener {
    public static DeathListener INSTANCE = new DeathListener();

    @HaikuSubscribe
    public void onPacket(PacketEvent.Receive event) {
        if (Main.mc.world == null && Main.mc.player == null) return;

        if (event.getPacket() instanceof HealthUpdateS2CPacket packet) {
            if (packet.getHealth() <= 0.0F) {
                int x = (int) Main.mc.player.getPos().getX();
                int y = (int) Main.mc.player.getPos().getY();
                int z = (int) Main.mc.player.getPos().getZ();
                Main.LOGGER.info(("You died at " + x + ", " + y + ", " + z + " in the " + getDimension()));
                Main.mc.player.sendMessage(Text.of("You died at " + x + ", " + y + ", " + z + " in the " + getDimension()));
            }
        }
    }

    private String getDimension() {
        if (Main.mc.world.getDimension().respawnAnchorWorks())
            return "Nether";
        else
            return "Overworld";
    }
}
