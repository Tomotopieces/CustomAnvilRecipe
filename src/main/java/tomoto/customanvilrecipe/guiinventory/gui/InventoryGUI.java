package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class InventoryGUI {
    protected static String guiName = "GUIName";
    protected Player player;
    protected ItemStack _grayGlassPane;

    public InventoryGUI(Player player) {
        this.player = player;

        _grayGlassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = _grayGlassPane.getItemMeta();
        meta.setDisplayName(" ");
        _grayGlassPane.setItemMeta(meta);
    }

    public abstract void openInventoryGUI();

    public static String getGUIName() {
        return guiName;
    }
}
