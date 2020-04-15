package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tomoto.customanvilrecipe.recipe.MatchMode;

import java.util.ArrayList;

public class MaterialSettingsGUI extends InventoryGUI {
    /**
     * Position of material in anvil.
     */
    public enum Position {LEFT, RIGHT}

    private MatchMode loreMatchMode = MatchMode.EXACT;
    private MatchMode nbtMatchMode = MatchMode.EXACT;

    private static final String loreSettingsButtonName = "§r§b§l§oLore match mode";
    private static final String nbtSettingsButtonName = "§r§b§l§oNBT match mode";
    private static final String backButtonName = "§r§b§l§oBack";

    public MaterialSettingsGUI(Player player, Position position) {
        super(player);
        guiName = "Material Advanced Settings";
        if(position.equals(Position.LEFT)) {
            guiName = "Left " + guiName;
        }
        else if(position.equals(Position.RIGHT)) {
            guiName = "Right " + guiName;
        }
    }

    @Override
    public void openInventoryGUI() {
        Inventory materialSettings = Bukkit.createInventory(player, 1*9, guiName);

        ItemStack loreSettings = new ItemStack(Material.MAP);
        ItemMeta meta = loreSettings.getItemMeta();
        meta.setDisplayName(loreSettingsButtonName);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§r§7Exact match");
        lore.add("§r§7Contain");
        lore.add("§r§7No match");
        lore.add(" ");
        lore.add("Left Click to change Match Mode");
        meta.setLore(lore);
        loreSettings.setItemMeta(meta);

        ItemStack nbtSettings = new ItemStack(Material.MAP);
        meta = nbtSettings.getItemMeta();
        meta.setDisplayName(nbtSettingsButtonName);
        meta.setLore(lore);
        nbtSettings.setItemMeta(meta);

        ItemStack backButton = new ItemStack(Material.BOOK);
        meta = backButton.getItemMeta();
        meta.setDisplayName(backButtonName);
        lore.clear();
        lore.add("§r§eClick to return to menu.");
        meta.setLore(lore);
        backButton.setItemMeta(meta);

        for(int i = 0; i < materialSettings.getSize(); ++i) {
            materialSettings.setItem(i, _grayGlassPane);
        }
        materialSettings.setItem(0, loreSettings);
        materialSettings.setItem(1, nbtSettings);
        materialSettings.setItem(8, backButton);

        player.closeInventory();
        player.openInventory(materialSettings);
    }

    public void changeMatchMode() {
        if(loreMatchMode.equals(MatchMode.EXACT)) {
            loreMatchMode = MatchMode.CONTAIN;
        }
        else if (loreMatchMode.equals(MatchMode.CONTAIN)) {
            loreMatchMode = MatchMode.IGNORE;
        }
        else if(loreMatchMode.equals(MatchMode.IGNORE)) {
            loreMatchMode = MatchMode.EXACT;
        }
    }

    public MatchMode getLoreMatchMode() {
        return loreMatchMode;
    }

    public MatchMode getNbtMatchMode() {
        return nbtMatchMode;
    }

    public static String getLoreSettingsButtonName() {
        return loreSettingsButtonName;
    }

    public static String getNbtSettingsButtonName() {
        return nbtSettingsButtonName;
    }

    public static String getBackButtonName() {
        return backButtonName;
    }
}
