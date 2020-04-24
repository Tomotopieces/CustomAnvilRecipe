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
import tomoto.customanvilrecipe.commandexecutor.AnvilExecutor;
import tomoto.customanvilrecipe.commandexecutor.AnvilTabCompleter;
import tomoto.customanvilrecipe.guiinventory.clicklistener.CreateClickEvent;
import tomoto.customanvilrecipe.guiinventory.clicklistener.MenuClickEvent;

import java.io.File;
import java.util.*;

public final class CustomAnvilRecipe extends JavaPlugin implements Listener {
    public static List<AnvilRecipe> recipeList = new ArrayList<>();
    public static FileConfiguration recipeFile;

    @Override
    public void onLoad() {
        saveResource("recipeData.yml", false);
        recipeFile = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "recipeData.yml"));
        loadRecipes();
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
        // Plugin shutdown logic
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
            recipeFile.save(new File(Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getDataFolder(), "recipeData.yml"));
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRecipes() {
        int i = 0;
        for(String key : recipeFile.getKeys(false)) {
            AnvilRecipe recipe = new AnvilRecipe();
            recipe.setLeftItem(recipeFile.getItemStack(key + ".LeftItem"));
            recipe.setRightItem(recipeFile.getItemStack(key + ".RightItem"));
            recipe.setResultItem(recipeFile.getItemStack(key + ".ResultItem"));
            recipeList.add(recipe);
            i++;
        }
        getLogger().info(i + " recipe(s) loaded.");
    }

    public static AnvilRecipe matchAnvilRecipe(ItemStack leftItem, ItemStack rightItem) {
        return recipeList.stream()
                .filter(r -> r.getLeftItem().equals(leftItem))
                .filter(r -> r.getRightItem().equals(rightItem))
                .findFirst().orElse(null);
    }
}
