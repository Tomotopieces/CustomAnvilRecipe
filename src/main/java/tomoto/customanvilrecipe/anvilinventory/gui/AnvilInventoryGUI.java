package tomoto.customanvilrecipe.anvilinventory.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class AnvilInventoryGUI {
    protected static String _GUIName = "GUIName";
    protected Player _player;
    protected ItemStack _blackGlassPane;

    public AnvilInventoryGUI(Player player) {
        this._player = player;

        _blackGlassPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = _blackGlassPane.getItemMeta();
        meta.setDisplayName("");
        _blackGlassPane.setItemMeta(meta);
    }

    public abstract void openInventoryGUI();

    public static String getGUIName() {
        return _GUIName;
    }
}
