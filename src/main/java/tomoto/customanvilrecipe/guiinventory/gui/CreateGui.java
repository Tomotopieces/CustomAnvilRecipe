package tomoto.customanvilrecipe.guiinventory.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateGui extends InventoryGui {
    public static final String leftLoreSettingButtonName = "§r§b§l§oLeft Material Lore Match Mode";
    public static final String leftNbtSettingButtonName = "§r§b§l§oLeft Material NBT Match Mode";
    public static final String rightLoreSettingButtonName = "§r§b§l§oRight Material Lore Match Mode";
    public static final String rightNbtSettingButtonName = "§r§b§l§oRight Material NBT Match Mode";
    public static final String backButtonName = "§r§b§l§oBack";
    public static final String saveButtonName = "§r§b§l§oSave";

    private static ItemStack leftHint = new ItemStack(Material.ANVIL);
    private static ItemStack rightHint = new ItemStack(Material.ANVIL);
    private static ItemStack resultHint = new ItemStack(Material.ANVIL);
    private ItemStack leftLoreSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack leftNbtSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack rightLoreSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private ItemStack rightNbtSettingButton = new ItemStack(Material.DRAGON_BREATH);
    private static ItemStack backButton = new ItemStack(Material.BOOK);
    private static ItemStack saveButton = new ItemStack(Material.MAP);

    private static final String exactTrue = "§r§a[Exact]";
    private static final String exactFalse = "§r§7Exact";
    private static final String containTrue = "§r§a[Contain]";
    private static final String containFalse = "§r§7Contain";
    private static final String ignoreTrue = "§r§a[Ignore]";
    private static final String ignoreFalse = "§r§7Ignore";

    private Inventory create = Bukkit.createInventory(player, 6*9, guiName);

    private static boolean isInitialized = false;

    public CreateGui(Player player) {
        super(player);
        guiName = "Anvil Recipe Create";

        initStaticButtons();
        initDynamicButtons();

        setButtons();
    }

    @Override
    public void openInventoryGUI() {
        player.closeInventory();
        player.openInventory(create);
    }

    public static ItemStack changeMatchMode(ItemStack button) {
        ItemMeta meta = button.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        if(meta.getLore().contains(exactTrue)) {
            lore.add(exactFalse);
            lore.add(containTrue);
            lore.add(ignoreFalse);
            button = new ItemStack(Material.POTION);
        }
        else if(meta.getLore().contains(containTrue)) {
            lore.add(exactFalse);
            lore.add(containFalse);
            lore.add(ignoreTrue);
            button = new ItemStack(Material.GLASS_BOTTLE);
        }
        else {
            lore.add(exactTrue);
            lore.add(containFalse);
            lore.add(ignoreFalse);
            button = new ItemStack(Material.DRAGON_BREATH);
        }
        lore.add(" ");
        lore.add("Left Click to change Match Mode");
        meta.setLore(lore);
        button.setItemMeta(meta);
        return button;
    }

    private void initStaticButtons(){
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

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
    }

    private void initDynamicButtons() {
        ItemMeta meta;
        ArrayList<String> lore = new ArrayList<>();

        meta = leftLoreSettingButton.getItemMeta();
        meta.setDisplayName(leftLoreSettingButtonName);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lore.add(exactTrue);
        lore.add(containFalse);
        lore.add(ignoreFalse);
        lore.add(" ");
        lore.add("Left Click to change Match Mode");
        meta.setLore(lore);
        leftLoreSettingButton.setItemMeta(meta);

        meta = leftNbtSettingButton.getItemMeta();
        meta.setDisplayName(leftNbtSettingButtonName);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leftNbtSettingButton.setItemMeta(meta);

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
    }

    private void setButtons() {
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
        create.setItem(29, leftNbtSettingButton);
        create.setItem(22, rightLoreSettingButton);
        create.setItem(31, rightNbtSettingButton);
        create.setItem(45, saveButton);
        create.setItem(53, backButton);
    }
}
