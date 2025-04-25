package com.neofast.arbor_mechanica.item;

import com.neofast.arbor_mechanica.ArborMechanica;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class Items {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArborMechanica.MOD_ID);

    public static final DeferredItem<Item> WOODEN_GEAR = ITEMS.register("wooden_gear",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.arbor_mechanica.wooden_gear.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}