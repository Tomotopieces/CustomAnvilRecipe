package tomoto.customanvilrecipe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tomoto.customanvilrecipe.anvillistener.AnvilClickResultEvent;
import tomoto.customanvilrecipe.anvillistener.AnvilSetItemEvent;
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.command.AnvilExecutor;
import tomoto.customanvilrecipe.command.AnvilTabCompleter;
import tomoto.customanvilrecipe.guiinventory.clicklistener.CreateClickEvent;
import tomoto.customanvilrecipe.guiinventory.clicklistener.MenuClickEvent;

import java.io.File;
import java.util.*;

public final class CustomAnvilRecipe extends JavaPlugin implements Listener {
    private static final String RECIPE_FILE_NAME = "recipeData.yml";
    public static List<AnvilRecipe> recipeList = new ArrayList<>();
    public static FileConfiguration recipeFile;

    @Override
    public void onLoad() {
        saveResource(RECIPE_FILE_NAME, false);
        recipeFile = YamlConfiguration.loadConfiguration(new File(getDataFolder(), RECIPE_FILE_NAME));
        loadRecipes();
        getLogger().info(recipeList.size() + " recipe(s) loaded.");
    }

    @Override
    public void onEnable() {
        getCommand("anvil").setTabCompleter(new AnvilTabCompleter());
        getCommand("anvil").setExecutor(new AnvilExecutor());
        Bukkit.getPluginManager().registerEvents(new MenuClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CreateClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new AnvilSetItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new AnvilClickResultEvent(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        saveRecipeFile();
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(0, Optional.of(new ItemStack(Material.DIAMOND))
                .map(item -> {
                    item.setItemMeta(Optional.of(item.getItemMeta()).map(meta -> {
                        meta.setLore(Collections.singletonList("This is a magic diamond."));
                        meta.setDisplayName(ChatColor.AQUA + "Magic Diamond");
                        return meta;
                    }).get());
                    return item;
                })
                .get());
    }

    public static void saveRecipeFile() {
        try {
            recipeFile.save(new File(Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getDataFolder(), RECIPE_FILE_NAME));
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadRecipes() {
        for(String key : recipeFile.getKeys(false)) {
            AnvilRecipe recipe = new AnvilRecipe();
            //https://www.spigotmc.org/threads/create-an-itemstack-list-and-drop-the-items-config-yml.318010/#post-2994272
            recipe.setLeftItem(ItemStack.deserialize(recipeFile.getConfigurationSection(key + ".LeftItem").getValues(true)));
            recipe.setRightItem(ItemStack.deserialize(recipeFile.getConfigurationSection(key + ".RightItem").getValues(true)));
            recipe.setResultItem(ItemStack.deserialize(recipeFile.getConfigurationSection(key + ".ResultItem").getValues(true)));
            recipeList.add(recipe);
        }
    }

    public static AnvilRecipe matchAnvilRecipe(ItemStack leftItem, ItemStack rightItem) {
        return recipeList.stream()
                .filter(r -> r.getLeftItem().equals(leftItem))
                .filter(r -> r.getRightItem().equals(rightItem))
                .findFirst().orElse(null);
    }
}
