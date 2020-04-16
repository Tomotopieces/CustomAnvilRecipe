package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MenuGui extends InventoryGui {
    private static final String createButtonName = "§lCreate";
    private static final String listButtonName = "§lList";

    public MenuGui(Player player) {
        super(player);
        guiName = "Anvil Menu";
    }

    @Override
    public void openInventoryGUI() {
        Inventory menu = Bukkit.createInventory(player, 1*9, guiName);

        ItemStack createButton = new ItemStack(Material.ANVIL);
        ItemMeta meta = createButton.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eClick to creat new anvil recipe.");
        meta.setLore(lore);
        meta.setDisplayName(createButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        createButton.setItemMeta(meta);

        ItemStack listButton = new ItemStack(Material.PAPER);
        meta = listButton.getItemMeta();
        lore.clear();
        lore.add("§eClick to check custom anvil recipes list.");
        meta.setLore(lore);
        meta.setDisplayName(listButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        listButton.setItemMeta(meta);

        for(int i = 0; i < 9; ++i) {
            menu.setItem(i, _grayGlassPane);
        }
        menu.setItem(0, createButton);
        menu.setItem(1, listButton);

        player.closeInventory();
        player.openInventory(menu);
    }

    public static String getCreateButtonName() {
        return createButtonName;
    }

    public static String getListButtonName() {
        return listButtonName;
    }
}
