package dev.vili.listeners;

import dev.vili.event.events.PacketEvent;
import dev.vili.haiku.eventbus.HaikuSubscribe;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;

public class TPSListener {
    public static TPSListener INSTANCE = new TPSListener();
    public static double ticks = 0;
    private static long prevTime = 0;

    @HaikuSubscribe
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof WorldTimeUpdateS2CPacket) {
            long time = System.currentTimeMillis();
            long timeOffset = Math.abs(1000 - (time - prevTime)) + 1000;
            ticks = (MathHelper.clamp(20 / (timeOffset / 1000d), 0, 20) * 100d) / 100d;
            prevTime = time;
        }
    }
}
