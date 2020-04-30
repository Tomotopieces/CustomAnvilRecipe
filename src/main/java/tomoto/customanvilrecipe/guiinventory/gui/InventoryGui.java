package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class InventoryGui {
    protected ItemStack GRAY_GLASS_PANE;
    public static final String BACK_BUTTON_NAME = "§r§b§l§oBack";

    /**
     * Open the gui.
     * @param player Player who open the gui.
     * @return The gui.
     */
    public InventoryGui openGui(Player player) {
        GRAY_GLASS_PANE = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = GRAY_GLASS_PANE.getItemMeta();
        meta.setDisplayName(" ");
        GRAY_GLASS_PANE.setItemMeta(meta);
        return this;
    }

    /**
     * Set button info.
     * @param item Button to be set.
     * @param name Button name.
     * @return The new Button.
     */
    public static ItemStack setButton(ItemStack item, String name) {
        setButton(item, name, "NULL");
        return item;
    }

    /**
     * Set button info.
     * @param item Button to be set.
     * @param name Button name.
     * @param lore Button lore.
     * @return The new Button.
     */
    public static ItemStack setButton(ItemStack item, String name, String lore) {
        setButton(item, name, Collections.singletonList(lore));
        return item;
    }

    /**
     * Set button info.
     * @param item Button to be set.
     * @param name Button name.
     * @param lore Button lore.
     * @return The new Button.
     */
    public static ItemStack setButton(ItemStack item, String name, List<String> lore) {
        Optional.ofNullable(item).ifPresent(i -> item.setItemMeta(Optional.of(item.getItemMeta())
                .map(m -> {
                    m.setDisplayName(name);
                    m.setLore(lore);
                    if(lore.get(0).equals("NULL")) {
                        m.setLore(null);
                    }
                    return m;
                }).get()));
        return item;
    }
}
