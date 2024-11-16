package com.example.recipemod;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "recipemod")
public class RecipeBlockHandler {
    @SubscribeEvent
    public static void onCraftingCheck(PlayerEvent.ItemCraftedEvent event) {
        ItemStack result = event.getCrafting();
        if (RecipeMod.BLOCKED_ITEMS.contains(result.getItem())) {
            event.setCanceled(true);
            event.getEntity().sendSystemMessage(Component.literal("Esta receta está bloqueada"));
        }
    }
}