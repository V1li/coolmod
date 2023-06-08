package dev.vili;

import dev.vili.haiku.eventbus.EventBus;
import dev.vili.listeners.DeathListener;
import dev.vili.listeners.PacketListener;
import dev.vili.listeners.TPSListener;
import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ClientModInitializer {
    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static final Logger LOGGER = LoggerFactory.getLogger("coolmod");
    public static final EventBus EVENT_BUS = new EventBus();
    public static final Boolean advancedDebug = false;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Vili's rat loaded. Cry.");
        EVENT_BUS.register(TPSListener.INSTANCE);
        LOGGER.info("Listening for TPS.");
        EVENT_BUS.register(DeathListener.INSTANCE);
        LOGGER.info("Listening for deaths.");
        // EVENT_BUS.register(PacketListener.INSTANCE);
        // LOGGER.info("Listening for packets.");
    }
}