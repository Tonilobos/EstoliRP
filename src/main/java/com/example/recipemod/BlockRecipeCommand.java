package com.example.recipemod;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BlockRecipeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("bloquear")
            .requires(source -> source.hasPermission(2)) // Nivel 2 = OP
            .executes(context -> {
                if (!context.getSource().hasPermission(2)) {
                    context.getSource().sendFailure(Component.literal("§c¡Necesitas ser OP para usar este comando!"));
                    return 0;
                }
                
                if (context.getSource().getEntity() instanceof Player player) {
                    ItemStack heldItem = player.getMainHandItem();
                    if (!heldItem.isEmpty()) {
                        RecipeMod.BLOCKED_ITEMS.add(heldItem.getItem());
                        player.sendSystemMessage(Component.literal("§a¡Receta bloqueada para: §f" + 
                            heldItem.getItem().getDescription().getString()));
                        return 1;
                    } else {
                        player.sendSystemMessage(Component.literal("§cDebes tener un ítem en la mano"));
                        return 0;
                    }
                }
                return 0;
            }));
    }
}