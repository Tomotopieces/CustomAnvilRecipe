package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class InventoryGui {
    protected ItemStack grayGlassPane;

    public void openGui(Player player) {
        grayGlassPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = grayGlassPane.getItemMeta();
        meta.setDisplayName(" ");
        grayGlassPane.setItemMeta(meta);
    }
}
