package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateGUI extends AnvilInventoryGUI{
    public CreateGUI(Player player) {
        super(player);
        _GUIName = "Anvil Recipe Create";
    }

    @Override
    public void openInventoryGUI() {
        Inventory create = Bukkit.createInventory(_player, 5*9, _GUIName);

        ItemStack leftSettings = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = leftSettings.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§eClick for advanced settings.");
        meta.setLore(lore);
        meta.setDisplayName("§lLeft Material");
        leftSettings.setItemMeta(meta);

        ItemStack rightSettings = new ItemStack(Material.ENCHANTED_BOOK);
        meta = rightSettings.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName("§lRight Material");
        rightSettings.setItemMeta(meta);

        ItemStack resultSettings = new ItemStack(Material.IRON_AXE);
        resultSettings.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        for(int i = 0; i < 45; ++i) {
            create.setItem(i, _blackGlassPane);
        }
        create.setItem(11, null);
        create.setItem(13, null);
        create.setItem(15, null);
        create.setItem(29, leftSettings);
        create.setItem(31, rightSettings);
        create.setItem(33, resultSettings);

        _player.closeInventory();
        _player.openInventory(create);
    }
}
