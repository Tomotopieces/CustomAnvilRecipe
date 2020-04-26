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
            Stream.iterate(0, i -> i + 1)
                    .limit(inv.getSize())
                    .forEach(i -> {
                        switch (i) {
                            case 0:
                                inv.setItem(i, Optional.of(CREATE_BUTTON)
                                        .map(cb -> setButton(cb, createButtonName, "§eClick to create new anvil recipe.")).get());
                                return;
                            case 1: inv.setItem(i, Optional.of(LIST_BUTTON)
//                                    .map(lb -> setButton(lb, listButtonName, "§eClick to check custom anvil recipes list.")).get());
                                    .map(lb -> setButton(lb, listButtonName, "§4Feature not implemented.")).get());
                                return;
                            default: inv.setItem(i, GRAY_GLASS_PANE);
                        }
                    });
            return inv;
        }).get());

        return this;
    }
}
