package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.stream.Stream;

public class MenuGui extends InventoryGui {
    public static final String guiName = "Anvil Menu";
    public static final String createButtonName = "§lCreate";
    public static final String listButtonName = "§lList";

    private static final ItemStack CREATE_BUTTON = new ItemStack(Material.ANVIL);
    private static final ItemStack LIST_BUTTON = new ItemStack(Material.PAPER);

    @Override
    public MenuGui openGui(Player player) {
        super.openGui(player);
        player.closeInventory();
        player.openInventory(Optional.of(Bukkit.createInventory(player, 9, guiName)).map(inv -> {
            setItem(CREATE_BUTTON, createButtonName, "§eClick to create new anvil recipe.");
            setItem(LIST_BUTTON, listButtonName, "§eClick to check custom anvil recipes list.");

            Stream.iterate(0, i -> i + 1)
                    .limit(inv.getSize())
                    .forEach(i -> {
                        switch (i) {
                            case 0: inv.setItem(i, CREATE_BUTTON); return;
                            case 1: inv.setItem(i, LIST_BUTTON); return;
                            default: inv.setItem(i, GRAY_GLASS_PANE);
                        }
                    });
            return inv;
        }).get());

        return this;
    }
}
