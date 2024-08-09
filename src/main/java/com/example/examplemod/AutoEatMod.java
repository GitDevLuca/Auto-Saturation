package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.item.ItemStack;

@Mod(AutoEatMod.MOD_ID)
public class AutoEatMod {

    public static final String MOD_ID = "autoeat";

    public AutoEatMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Vorbereitungen vor dem Start (falls nötig)
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;


        PlayerEvent playerEvent = new PlayerEvent(player);

        // Überprüfen, ob der Spieler Hunger hat
        if (player.getFoodData().getFoodLevel() < 18) {
            // Durch das Inventar iterieren
            for (ItemStack itemStack : player.getAllSlots()) {
                // Prüfen, ob der Gegenstand essbar ist
                if (itemStack.getItem().isEdible()) {
                    // Simuliere das Essen
                    player.eat(null,itemStack);
                    break;
                }
            }
        }
    }
}
