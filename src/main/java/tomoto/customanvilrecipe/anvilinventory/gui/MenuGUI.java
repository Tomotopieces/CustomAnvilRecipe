package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MenuGUI extends InventoryGUI {
    public static String createButtonName = "§lCreate";
    public static String listButtonName = "§lList";

    public MenuGUI(Player player) {
        super(player);
        _GUIName = "Anvil Menu";
    }

    @Override
    public void openInventoryGUI() {
        Inventory menu = Bukkit.createInventory(_player, 1*9, _GUIName);

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

        _player.closeInventory();
        _player.openInventory(menu);
    }
}
