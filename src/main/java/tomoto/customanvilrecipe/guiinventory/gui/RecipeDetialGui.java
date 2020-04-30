package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.guiinventory.gui.CreateGui.LEVEL_BUTTON_NAME;

public class RecipeDetialGui extends InventoryGui {
    public static final String GUI_NAME = "Recipe Detials";
    public static final String DELETE_BUTTON_NAME = "§r§4§lDelete the Recipe";

    private AnvilRecipe recipe;

    public RecipeDetialGui(AnvilRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public RecipeDetialGui openGui(Player player) {
        Optional.of(player)
                .map(p -> {
                    super.openGui(p);
                    p.closeInventory();
                    p.openInventory(Optional.of(Bukkit.createInventory(p, 9, GUI_NAME))
                            .map(inv -> {
                                Stream.iterate(0, i -> i + 1)
                                        .forEach(i -> {
                                            switch (i) {
                                                case 0:
                                                    inv.setItem(i, Optional.of(new ItemStack(Material.BARRIER))
                                                            .map(b -> setButton(b, DELETE_BUTTON_NAME, "§r§8Click to delete the recipe.")).get());
                                                    return;
                                                case 2:
                                                    inv.setItem(i, recipe.getLeftItem());
                                                    return;
                                                case 4:
                                                    inv.setItem(i, recipe.getRightItem());
                                                    return;
                                                case 5:
                                                    inv.setItem(i, Optional.of(new ItemStack(Material.GLASS_BOTTLE))
                                                            .map(eb -> setButton(eb, LEVEL_BUTTON_NAME, Optional.of(new ArrayList<String>()).map(l -> {
                                                                l.add("§r§eLeft (+ shift) click to increase.");
                                                                l.add("§r§eRight (+ shift) click to decrease.");
                                                                l.add("§r§eEmpty bottle means zero.");
                                                                return l;
                                                            }).get())).get());
                                                    return;
                                                case 6:
                                                    inv.setItem(i, recipe.getResultItem());
                                                    return;
                                                case 8:
                                                    inv.setItem(i, Optional.of(new ItemStack(Material.BOOK))
                                                            .map(sb -> setButton(sb, BACK_BUTTON_NAME, "§r§eClick to return to the menu.")).get());
                                                    return;
                                            }
                                        });
                                return inv;
                            }).get());
                    return p;
                });
        return this;
    }
}
