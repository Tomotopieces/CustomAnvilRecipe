package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class CreateGui extends InventoryGui {
    public static final String GUI_NAME = "Anvil Recipe Create";
    public static final String BACK_BUTTON_NAME = "§r§b§l§oBack";
    public static final String SAVE_BUTTON_NAME = "§r§b§l§oSave";
    public static final String LEVEL_BUTTON_NAME = "§r§a§l§oRequired Level";

    private static final ItemStack BACK_BUTTON = new ItemStack(Material.BOOK);
    private static final ItemStack SAVE_BUTTON = new ItemStack(Material.MAP);
    private static final ItemStack LEVEL_BUTTON = new ItemStack(Material.GLASS_BOTTLE);

    /**
     * Open Create gui.
     */
    @Override
    public CreateGui openGui(Player player) {
        super.openGui(player);

        //Thanks 9032676
        player.closeInventory();
        player.openInventory(Optional.of(Bukkit.createInventory(player, 9, GUI_NAME)).map(inv -> {
            Stream.iterate(0, i -> i + 1)
                    .limit(inv.getSize())
                    .filter(i -> !isMaterialSlot(i))
                    .forEach(i -> {
                        switch (i) {
                            case 0: inv.setItem(i, Optional.of(SAVE_BUTTON)
                                    .map(bb -> setButton(bb, SAVE_BUTTON_NAME, "§r§eClick to return to menu.")).get());
                                return;
                            case 5: inv.setItem(i, Optional.of(LEVEL_BUTTON)
                                    .map(eb -> setButton(eb, LEVEL_BUTTON_NAME, Optional.of(new ArrayList<String>()).map(l -> {
                                        l.add("§r§eLeft click to increase.");
                                        l.add("§r§eRight click to decrease.");
                                        l.add("§r§eEmpty bottle means zero.");
                                        return l;
                                    }).get())).get());
                                return;
                            case 8: inv.setItem(i, Optional.of(BACK_BUTTON)
                                    .map(sb -> setButton(sb, BACK_BUTTON_NAME, "§r§eClick to save recipe.")).get());
                                return;
                            default: inv.setItem(i, GRAY_GLASS_PANE);
                        }
                    });
            return inv;
        }).get());

        return this;
    }

    /**
     * Check if the slot is a material slot or a result slot.
     * @param index The raw slot index to check for.
     * @return true if the slot is a material or a result slot.
     */
    public static boolean isMaterialSlot(int index) {
        return index == 2 || index == 4 || index == 6;
    }
}
