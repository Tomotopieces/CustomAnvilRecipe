package tomoto.customanvilrecipe;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.io.NbtTextSerializer;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class CustomAnvilRecipe extends JavaPlugin implements Listener {
    public static List<AnvilRecipe> recipeList = new ArrayList<>();
    public static FileConfiguration recipeFile;

    @Override
    public void onLoad() {
        saveResource("recipeData.yml", false);
        recipeFile = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "recipeData.yml"));
        loadRecipes(recipeList);
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

    public static void loadRecipes(List<AnvilRecipe> list) {
        int i = 0;
        for(String key : recipeFile.getKeys(false)) {
            AnvilRecipe recipe = new AnvilRecipe();
            recipe.setLeftMaterial(Material.getMaterial(recipeFile.getString(key + ".LeftMaterial")));
            recipe.setRightMaterial(Material.getMaterial(recipeFile.getString(key + ".RightMaterial")));
            recipe.setRightMaterial(Material.getMaterial(recipeFile.getString(key + ".ResultMaterial")));
            try {
                recipe.setLeftNbt(NbtTextSerializer.DEFAULT.deserializeCompound(recipeFile.getString(key + ".LeftNbt")));
            }
            catch(java.io.IOException e) {
                e.printStackTrace();
            }
            try {
                recipe.setRightNbt(NbtTextSerializer.DEFAULT.deserializeCompound(recipeFile.getString(key + ".RightNbt")));
            }
            catch(java.io.IOException e) {
                e.printStackTrace();
            }
            try {
                recipe.setResultNbt(NbtTextSerializer.DEFAULT.deserializeCompound(recipeFile.getString(key + ".ResultNbt")));
            }
            catch(java.io.IOException e) {
                e.printStackTrace();
            }
            recipeList.add(recipe);
            i++;
        }
        Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getLogger().info(i + " recipes loaded.");
    }

    public static AnvilRecipe matchAnvilRecipe(ItemStack leftItem, NbtCompound leftNbt, ItemStack rightItem, NbtCompound rightNbt) {
        return recipeList.stream()
                .filter(r -> r.getLeftMaterial().equals(leftItem.getType()))
                .filter(r -> r.getLeftNbt().toString().equals(leftNbt.toString()))
                .filter(r -> r.getRightMaterial().equals(rightItem.getType()))
                .filter(r -> r.getRightNbt().toString().equals(rightNbt.toString()))
                .findFirst().orElse(null);
    }
}
