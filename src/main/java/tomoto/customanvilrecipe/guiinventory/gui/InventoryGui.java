package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class InventoryGui implements Inventory {
    protected static String guiName = "GUIName";
    protected Player player;
    protected ItemStack grayGlassPane;

    public InventoryGui(Player player) {
        this.player = player;

        grayGlassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = grayGlassPane.getItemMeta();
        meta.setDisplayName(" ");
        grayGlassPane.setItemMeta(meta);
    }

    public abstract void openGui();

    public static String getGUIName() {
        return guiName;
    }
}
