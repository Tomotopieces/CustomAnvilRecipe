package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.stream.Stream;

public class MenuGui extends InventoryGui {
    public static final String GUI_NAME = "Anvil Menu";
    public static final String CREATE_BUTTON_NAME = "§lCreate";
    public static final String LIST_BUTTON_NAME = "§lList";

    @Override
    public MenuGui openGui(Player player) {
        super.openGui(player);
        player.closeInventory();
        player.openInventory(Optional.of(Bukkit.createInventory(player, 9, GUI_NAME)).map(inv -> {
            Stream.iterate(0, i -> i + 1)
                    .limit(inv.getSize())
                    .forEach(i -> {
                        switch (i) {
                            case 0:
                                inv.setItem(i, Optional.of(new ItemStack(Material.ANVIL))
                                        .map(cb -> setButton(cb, CREATE_BUTTON_NAME, "§eClick to create new anvil recipe.")).get());
                                return;
                            case 1: inv.setItem(i, Optional.of(new ItemStack(Material.PAPER))
                                    .map(lb -> setButton(lb, LIST_BUTTON_NAME, "§eClick to check custom anvil recipes list.")).get());
                                return;
                            default: inv.setItem(i, GRAY_GLASS_PANE);
                        }
                    });
            return inv;
        }).get());
        return this;
    }
}
