package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class RecipeListGui extends InventoryGui {
    public static final String GUI_NAME = "Recipe List";
    public static final String PREVIOUS_BUTTON_NAME_AVAILABLE = "§r§b§l§oPrevious page";
    public static final String PREVIOUS_BUTTON_NAME_UNAVAILABLE = "§r§8§l§oPrevious page";
    public static final String NEXT_BUTTON_NAME_AVAILABLE = "§r§b§l§oNext page";
    public static final String NEXT_BUTTON_NAME_UNAVAILABLE = "§r§8§l§oNext page";
    public static final String BACK_BUTTON_NAME = "§r§b§l§oBack";
    public static final String CURRENT_PAGE = "§r§b§l§oCurrent page:";

    @Override
    public RecipeListGui openGui(Player player) {
        Optional.of(player)
                .map(p -> {
                    super.openGui(p);
                    return p;
                })
                .map(p -> {
                    p.closeInventory();
                    p.openInventory(Optional.of(Bukkit.createInventory(p, 54, GUI_NAME))
                            .map(inv -> {
                                Stream.iterate(0, i -> i + 1)
                                        .limit(Math.min(recipeList.size(), 45))
                                        .forEach(i -> inv.setItem(i, recipeList.get(i).getResultItem()));
                                return inv;})
                            .map(inv -> {
                                Optional.of(new ItemStack(Material.MAP)).map(b -> {
                                    setButton(b, PREVIOUS_BUTTON_NAME_UNAVAILABLE);
                                    inv.setItem(45, b);
                                    setButton(b, NEXT_BUTTON_NAME_AVAILABLE);
                                    inv.setItem(53, b);
                                    b.setType(Material.PAPER);
                                    setButton(b, CURRENT_PAGE, "1");
                                    inv.setItem(49, b);
                                    b.setType(Material.BOOK);
                                    setButton(b, BACK_BUTTON_NAME);
                                    inv.setItem(52, b);
                                    return inv;
                                });
                                return inv;
                            }).get());
                    return p;
        });
        return this;
    }

    /**
     * Make the page in gui.
     * @param list Current RecipeListGui.
     * @param page Page number.
     */
    private void makePage(Inventory list, int page) {
        if(page > pageSize()) {
            Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getLogger().info("Page out of bounds.");
            return;
        }
        Optional.of(list).map(inv -> {
            Stream.iterate((page - 1) * 45, i -> i + 1)
                    .limit(Math.min(recipeList.size() - (page - 1) * 45, 45))
                    .forEach(i -> inv.setItem(i, recipeList.get(i).getResultItem()));
            return inv;
        });
        Optional.of(getPageButton(list)).map(b -> {
            setButton(b, b.getItemMeta().getDisplayName(), Integer.toString(page));
            return b;
        });
    }

    /**
     * Get the number of pages.
     * @return The number of pages.
     */
    public static int pageSize() {
        return recipeList.size() / 45 + 1;
    }

    /**
     * Get the PageButton of the gui.
     * @param list Current RecipeListGui.
     * @return The PageButton of current gui.
     */
    public static ItemStack getPageButton(Inventory list) {
        return list.getItem(49);
    }

    public static int getPage(ItemStack pageButton) {
        return Integer.parseInt(pageButton.getItemMeta().getLore().get(0));
    }
}
