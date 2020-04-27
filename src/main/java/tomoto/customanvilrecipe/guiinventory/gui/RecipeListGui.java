package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.stream.Stream;

import static tomoto.customanvilrecipe.CustomAnvilRecipe.recipeList;

public class RecipeListGui extends InventoryGui{
    public static final String GUI_NAME = "Recipe List";

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
                                        .limit(recipeList.size())
                                        .forEach(i -> inv.setItem(i, Optional.of(recipeList.get(i).getResultItem())
                                                //.map(...)
                                                .get()));
                                return inv;
                            }).get());
                    return p;
        });
        return this;
    }
}
