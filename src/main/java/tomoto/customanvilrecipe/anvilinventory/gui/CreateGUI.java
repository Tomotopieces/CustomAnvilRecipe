package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateGUI extends InventoryGUI {
    public static String leftSettingsButtonName = "§r§b§l§oLeft Material Settings";
    public static String rightSettingsButtonName = "§r§b§l§oRight Material Settings";
    public static String backButtonName = "§r§b§l§oBack";
    public static String saveButtonName = "§r§b§l§oSave";

    public CreateGUI(Player player) {
        super(player);
        _GUIName = "Anvil Recipe Create";
    }

    @Override
    public void openInventoryGUI() {
        Inventory create = Bukkit.createInventory(_player, 3*9, _GUIName);

        ItemStack leftHint = new ItemStack(Material.ANVIL);
        ItemMeta meta = leftHint.getItemMeta();
        meta.setDisplayName("§r§c§l§o###Please put left material up here###");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leftHint.setItemMeta(meta);

        ItemStack rightHint = new ItemStack(Material.ANVIL);
        meta = rightHint.getItemMeta();
        meta.setDisplayName("§r§c§l§o###Please put right material up here###");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        rightHint.setItemMeta(meta);

        ItemStack resultHint = new ItemStack(Material.ANVIL);
        meta = resultHint.getItemMeta();
        meta.setDisplayName("§r§c§l§o###Please put Result up here###");

        ItemStack leftSettingsButton = new ItemStack(Material.GOLDEN_APPLE);
        meta = leftSettingsButton.getItemMeta();
        meta.setDisplayName(leftSettingsButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§r§eClick for advanced settings.");
        meta.setLore(lore);
        leftSettingsButton.setItemMeta(meta);

        ItemStack rightSettingsButton = new ItemStack(Material.NETHER_STAR);
        meta = rightSettingsButton.getItemMeta();
        meta.setDisplayName(rightSettingsButtonName);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        rightSettingsButton.setItemMeta(meta);

        ItemStack result = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
        meta = result.getItemMeta();
        meta.setDisplayName("§r§b§l§oResult");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(meta);

        ItemStack backButton = new ItemStack(Material.BOOK);
        meta = backButton.getItemMeta();
        meta.setDisplayName(backButtonName);
        lore.clear();
        lore.add("§r§eClick to return to menu.");
        meta.setLore(lore);
        backButton.setItemMeta(meta);

        ItemStack saveButton = new ItemStack(Material.MAP);
        meta = saveButton.getItemMeta();
        meta.setDisplayName(saveButtonName);
        lore.clear();
        lore.add("§r§eClick to save recipe.");
        meta.setLore(lore);
        saveButton.setItemMeta(meta);

        for(int i = 0; i < 27; ++i) {
            create.setItem(i, _grayGlassPane);
        }
        create.setItem(2, null);
        create.setItem(4, null);
        create.setItem(6, null);
        create.setItem(11, leftHint);
        create.setItem(13, rightHint);
        create.setItem(15, resultHint);
        create.setItem(20, leftSettingsButton);
        create.setItem(22, rightSettingsButton);
        create.setItem(24, result);
        create.setItem(18, backButton);
        create.setItem(26, saveButton);

        _player.closeInventory();
        _player.openInventory(create);
    }
}
