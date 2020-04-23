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
import tomoto.customanvilrecipe.anvilrecipe.AnvilRecipe;
import tomoto.customanvilrecipe.commandexecutor.AnvilExecutor;
import tomoto.customanvilrecipe.commandexecutor.AnvilTabCompleter;
import tomoto.customanvilrecipe.guiinventory.clicklistener.CreateClickEvent;
import tomoto.customanvilrecipe.guiinventory.clicklistener.MenuClickEvent;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class CustomAnvilRecipe extends JavaPlugin implements Listener {
    public static List<AnvilRecipe> recipeList;

    public static final File dataFolder = Bukkit.getPluginManager().getPlugin("CustomAnvilRecipe").getDataFolder();
    public static final FileConfiguration recipeFile = YamlConfiguration.loadConfiguration(new File(dataFolder, "recipeData.yml"));

    @Override
    public void onEnable() {
        getCommand("anvil").setTabCompleter(new AnvilTabCompleter());
        getCommand("anvil").setExecutor(new AnvilExecutor());
        Bukkit.getPluginManager().registerEvents(new MenuClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new CreateClickEvent(), this);
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
}
