package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tomoto.customanvilrecipe.recipe.AnvilRecipe;
import tomoto.customanvilrecipe.recipe.MatchMode;

import java.util.ArrayList;

/**
 * Abstract class for other InventoryGUI
 */
public class CreateGUI extends InventoryGUI {
    private static final String leftLoreSettingButtonName = "§r§b§l§oLeft Material Settings";
    private static final String leftNbtSettingButtonName = "§r§b§l§oLeft Material Settings";
    private static final String rightLoreSettingButtonName = "§r§b§l§oRight Material Settings";
    private static final String rightNbtSettingButtonName = "§r§b§l§oRight Material Settings";
    private static final String backButtonName = "§r§b§l§oBack";
    private static final String saveButtonName = "§r§b§l§oSave";

    private static ItemStack leftHint = new ItemStack(Material.ANVIL);
    private static ItemStack rightHint = new ItemStack(Material.ANVIL);
    private static ItemStack resultHint = new ItemStack(Material.ANVIL);
    private ItemStack leftLoreSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack leftNbtSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack rightLoreSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack rightNbtSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private static ItemStack backButton = new ItemStack(Material.BOOK);
    private static ItemStack saveButton = new ItemStack(Material.MAP);
    private Inventory create = Bukkit.createInventory(player, 4*9, guiName);

    private MatchMode leftLoreMatchMode = MatchMode.EXACT;
    private MatchMode leftNbtMatchMode = MatchMode.EXACT;
    private MatchMode rightLoreMatchMode = MatchMode.EXACT;
    private MatchMode rightNbtMatchMode = MatchMode.EXACT;

    private AnvilRecipe recipe = new AnvilRecipe();

    private static boolean isInitialized = false;

    public CreateGUI(Player player,
                     MatchMode leftLoreMatchMode, MatchMode leftNbtMatchMode,
                     MatchMode rightLoreMatchMode, MatchMode rightMatchMode) {
        super(player);
        guiName = "Anvil Recipe Create";

        ItemMeta meta;
        meta = leftLoreSettingButton.getItemMeta();
        meta.setDisplayName(leftLoreSettingButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§r§7Exact match");
        lore.add("§r§7Contain");
        lore.add("§r§7No match");
        lore.add(" ");
        lore.add("Left Click to change Match Mode");
        meta.setLore(lore);
        leftLoreSettingButton.setItemMeta(meta);

        meta = leftNbtSettingButton.getItemMeta();
        meta.setDisplayName(rightNbtSettingButtonName);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leftLoreSettingButton.setItemMeta(meta);

        meta = rightLoreSettingButton.getItemMeta();
        meta.setDisplayName(rightLoreSettingButtonName);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        rightLoreSettingButton.setItemMeta(meta);

        meta = rightNbtSettingButton.getItemMeta();
        meta.setDisplayName(rightNbtSettingButtonName);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        rightNbtSettingButton.setItemMeta(meta);

        if(!isInitialized) {
            isInitialized = true;

            meta = leftHint.getItemMeta();
            meta.setDisplayName("§r§c§l§o###Please put left material up here###");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            leftHint.setItemMeta(meta);


            meta = rightHint.getItemMeta();
            meta.setDisplayName("§r§c§l§o###Please put right material up here###");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            rightHint.setItemMeta(meta);


            meta = resultHint.getItemMeta();
            meta.setDisplayName("§r§c§l§o###Please put Result up here###");
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            resultHint.setItemMeta(meta);


            meta = backButton.getItemMeta();
            meta.setDisplayName(backButtonName);
            lore.clear();
            lore.add("§r§eClick to return to menu.");
            meta.setLore(lore);
            backButton.setItemMeta(meta);


            meta = saveButton.getItemMeta();
            meta.setDisplayName(saveButtonName);
            lore.clear();
            lore.add("§r§eClick to save recipe.");
            meta.setLore(lore);
            saveButton.setItemMeta(meta);

            for(int i = 0; i < create.getSize(); ++i) {
                create.setItem(i, _grayGlassPane);
            }
            create.setItem(2, null);
            create.setItem(4, null);
            create.setItem(6, null);
            create.setItem(11, leftHint);
            create.setItem(13, rightHint);
            create.setItem(15, resultHint);
            create.setItem(20, leftLoreSettingButton);
            create.setItem(22, rightLoreSettingButton);
            create.setItem(18, backButton);
            create.setItem(26, saveButton);
        }
    }

    @Override
    public void openInventoryGUI() {
        player.closeInventory();
        player.openInventory(create);
    }

    public static void changeMatchMode(ItemStack item, MatchMode matchMode) {
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        if(matchMode.equals(MatchMode.EXACT)) {
            matchMode = MatchMode.CONTAIN;
            lore.add("§r§7Exact");
            lore.add("§r§a[Contain]");
            lore.add("§r§7Ignore");
        }
        else if (matchMode.equals(MatchMode.CONTAIN)) {
            matchMode = MatchMode.IGNORE;
            lore.add("§r§7Exact");
            lore.add("§r§7Contain");
            lore.add("§r§a[Ignore]");
        }
        else if(matchMode.equals(MatchMode.IGNORE)) {
            matchMode = MatchMode.EXACT;
            lore.add("§r§a[Exact]");
            lore.add("§r§7Contain");
            lore.add("§r§7Ignore");
        }
        item.setItemMeta(meta);
    }

    public static String getLeftLoreSettingButtonName() {
        return leftLoreSettingButtonName;
    }

    public static String getLeftNbtSettingButtonName() {
        return leftNbtSettingButtonName;
    }

    public static String getRightLoreSettingButtonName() {
        return rightLoreSettingButtonName;
    }

    public static String getRightNbtSettingButtonName() {
        return rightNbtSettingButtonName;
    }

    public static String getBackButtonName() {
        return backButtonName;
    }

    public static String getSaveButtonName() {
        return saveButtonName;
    }

    public ItemStack getLeftLoreSettingButton() {
        return leftLoreSettingButton;
    }

    public ItemStack getLeftNbtSettingButton() {
        return leftNbtSettingButton;
    }

    public ItemStack getRightLoreSettingButton() {
        return rightLoreSettingButton;
    }

    public ItemStack getRightNbtSettingButton() {
        return rightNbtSettingButton;
    }

    public MatchMode getLeftLoreMatchMode() {
        return leftLoreMatchMode;
    }

    public MatchMode getLeftNbtMatchMode() {
        return leftNbtMatchMode;
    }

    public MatchMode getRightLoreMatchMode() {
        return rightLoreMatchMode;
    }

    public MatchMode getRightNbtMatchMode() {
        return rightNbtMatchMode;
    }
}
