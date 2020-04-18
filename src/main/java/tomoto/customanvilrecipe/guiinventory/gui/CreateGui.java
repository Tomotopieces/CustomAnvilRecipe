package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateGui extends InventoryGui {
    public static final String guiName = "Anvil Recipe Create";
    public static final String backButtonName = "§r§b§l§oBack";
    public static final String saveButtonName = "§r§b§l§oSave";

    private static ItemStack backButton = new ItemStack(Material.BOOK);
    private static ItemStack saveButton = new ItemStack(Material.MAP);

    private Inventory create;

    /**
     * Open Create gui.
     */
    @Override
    public void openGui(Player player) {
        super.openGui(player);
        create = Bukkit.createInventory(player, 1*9, guiName);
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        meta = backButton.getItemMeta();
        meta.setDisplayName(backButtonName);
        lore.add("§r§eClick to return to menu.");
        meta.setLore(lore);
        backButton.setItemMeta(meta);

        meta = saveButton.getItemMeta();
        meta.setDisplayName(saveButtonName);
        lore.clear();
        lore.add("§r§eClick to save recipe.");
        meta.setLore(lore);
        saveButton.setItemMeta(meta);

        for(int i = 0; i < create.getSize(); ++i) {
            create.setItem(i, grayGlassPane);
        }
        create.setItem(2, null);
        create.setItem(4, null);
        create.setItem(6, null);
        create.setItem(0, saveButton);
        create.setItem(8, backButton);

        player.closeInventory();
        player.openInventory(create);
    }

    /**
     * Check if the slot is a material slot or a result slot.
     * @param index The raw slot index to check for.
     * @return true if the slot is a material or a result slot.
     */
    public static boolean isMaterialSlot(int index) {
        return index == 2 || index == 4 || index == 6;
    }

    @Override
    public Inventory getInventory() {
        return this.create;
    }
}
