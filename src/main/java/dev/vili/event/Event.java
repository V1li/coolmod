package dev.vili.event;

import dev.vili.eventbus.HaikuEvent;
import net.minecraft.client.MinecraftClient;

public class Event extends HaikuEvent {
    public MinecraftClient mc = MinecraftClient.getInstance();

    public Event() {}
}
