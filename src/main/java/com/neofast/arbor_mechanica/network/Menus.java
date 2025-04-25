package com.neofast.arbor_mechanica.network;

import com.neofast.arbor_mechanica.ArborMechanica;
import com.neofast.arbor_mechanica.network.custom.NT_Machine1Menu;
import com.neofast.arbor_mechanica.network.custom.NT_Machine2Menu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Menus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, ArborMechanica.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<NT_Machine1Menu>> NT_MACHINE1_MENU =
            registerMenuType("nt_machine1_menu", NT_Machine1Menu::new);
    public static final DeferredHolder<MenuType<?>, MenuType<NT_Machine2Menu>> NT_MACHINE2_MENU =
            registerMenuType("nt_machine2_menu", NT_Machine2Menu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                              IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
