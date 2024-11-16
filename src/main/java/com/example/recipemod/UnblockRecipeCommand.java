package com.example.recipemod;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class UnblockRecipeCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("desbloquear")
            .requires(source -> source.hasPermission(2)) // Nivel 2 = OP
            .executes(context -> {
                if (!context.getSource().hasPermission(2)) {
                    context.getSource().sendFailure(Component.literal("§c¡Necesitas ser OP para usar este comando!"));
                    return 0;
                }
                
                if (context.getSource().getEntity() instanceof Player player) {
                    ItemStack heldItem = player.getMainHandItem();
                    if (!heldItem.isEmpty()) {
                        if (RecipeMod.BLOCKED_ITEMS.remove(heldItem.getItem())) {
                            player.sendSystemMessage(Component.literal("§a¡Receta desbloqueada para: §f" + 
                                heldItem.getItem().getDescription().getString()));
                            return 1;
                        } else {
                            player.sendSystemMessage(Component.literal("§cEste ítem no estaba bloqueado"));
                            return 0;
                        }
                    } else {
                        player.sendSystemMessage(Component.literal("§cDebes tener un ítem en la mano"));
                        return 0;
                    }
                }
                return 0;
            }));
    }
}