package dev.vili.listeners;

import dev.vili.event.events.PacketEvent;
import dev.vili.haiku.eventbus.HaikuSubscribe;

public class PacketListener {
    public static PacketListener INSTANCE = new PacketListener();
    private static String lastReceivedPacket = "";
    private static String lastSentPacket = "";


    @HaikuSubscribe
    public void onPacket(PacketEvent.Receive event) {
        if (event.getPacket() != null) {
            lastReceivedPacket = event.getPacket().toString();
            // strip the packet name from the packet
            lastReceivedPacket = lastReceivedPacket.substring(lastReceivedPacket.lastIndexOf(".") + 1);
        }
    }

    @HaikuSubscribe
    public void onPacket(PacketEvent.Send event) {
        if (event.getPacket() != null) {
            lastSentPacket = event.getPacket().toString();
            // strip the packet name from the packet
            lastSentPacket = lastSentPacket.substring(lastSentPacket.lastIndexOf(".") + 1);
        }
    }

    public static String getLastReceivedPacket() {
        return lastReceivedPacket;
    }

    public static String getLastSentPacket() {
        return lastSentPacket;
    }
}
