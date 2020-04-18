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
    public static final String guiName = "Anvil Menu";
    public static final String createButtonName = "§lCreate";
    public static final String listButtonName = "§lList";

    private static ItemStack createButton = new ItemStack(Material.ANVIL);
    private static ItemStack listButton = new ItemStack(Material.PAPER);

    private Inventory menu;

    @Override
    public void openGui(Player player) {
        super.openGui(player);
        menu = Bukkit.createInventory(player, 1*9, guiName);

        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        createButton = new ItemStack(Material.ANVIL);
        meta = createButton.getItemMeta();
        lore.add("§eClick to create new anvil recipe.");
        meta.setLore(lore);
        meta.setDisplayName(createButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        createButton.setItemMeta(meta);

        listButton = new ItemStack(Material.PAPER);
        meta = listButton.getItemMeta();
        lore.clear();
        lore.add("§eClick to check custom anvil recipes list.");
        meta.setLore(lore);
        meta.setDisplayName(listButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        listButton.setItemMeta(meta);

        for(int i = 0; i < 9; ++i) {
            menu.setItem(i, grayGlassPane);
        }
        menu.setItem(0, createButton);
        menu.setItem(1, listButton);
        player.closeInventory();
        player.openInventory(menu);
    }

    @Override
    public Inventory getInventory() {
        return this.menu;
    }
}
