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

    public InventoryGui openGui(Player player) {
        GRAY_GLASS_PANE = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = GRAY_GLASS_PANE.getItemMeta();
        meta.setDisplayName(" ");
        GRAY_GLASS_PANE.setItemMeta(meta);
        return this;
    }

    public InventoryGui setItem(ItemStack item, String name, String lore) {
        setItem(item, name, Collections.singletonList(lore));
        return this;
    }

    public static void setItem(ItemStack item, String name, List<String> lores) {
        Optional.ofNullable(item).ifPresent(i -> item.setItemMeta(Optional.of(item.getItemMeta())
                .map(m -> {
                    m.setDisplayName(name);
                    m.setLore(lores);
                    return m;
                }).get()));
    }
}
