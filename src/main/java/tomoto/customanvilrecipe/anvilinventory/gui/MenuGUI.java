package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MenuGUI extends AnvilInventoryGUI{
    public MenuGUI(Player player) {
        super(player);
        _GUIName = "Anvil Menu";
    }

    @Override
    public void openInventoryGUI() {
        Inventory menu = Bukkit.createInventory(_player, 5*9, _GUIName);

        ItemStack createButton = new ItemStack(Material.ANVIL);
        ItemMeta meta = createButton.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eClick to creat new anvil recipe.");
        meta.setLore(lore);
        meta.setDisplayName("§lCreate");
        createButton.setItemMeta(meta);

        ItemStack listButton = new ItemStack(Material.PAPER);
        meta = listButton.getItemMeta();
        lore.clear();
        lore.add("§eClick to check custom anvil recipes list.");
        meta.setLore(lore);
        meta.setDisplayName("§lList");
        listButton.setItemMeta(meta);

        for(int i = 0; i < 45; ++i) {
            menu.setItem(i, _blackGlassPane);
        }
        menu.setItem(22, createButton);
        menu.setItem(31, listButton);

        _player.closeInventory();
        _player.openInventory(menu);
    }
}
