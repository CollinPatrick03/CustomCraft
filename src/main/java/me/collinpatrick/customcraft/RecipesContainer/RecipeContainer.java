package me.collinpatrick.customcraft.RecipesContainer;

import me.collinpatrick.customcraft.CustomCraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;

public class RecipeContainer {
    Plugin p;
    private ArrayList<FurnaceRecipe> customFurnaceRecipes = new ArrayList<FurnaceRecipe>();
    private ArrayList<ShapedRecipe> customShapedRecipes = new ArrayList<ShapedRecipe>();
    private ArrayList<NamespacedKey> keys = new ArrayList<NamespacedKey>();
    public RecipeContainer(Plugin p) {
        this.p = p;
    }
    public void initRecipes() {
        createCustomShapedRecipes();
        createCustomFurnaceRecipes();
        initCustomRecipes();
    }

    public void addKey(NamespacedKey k) {
        this.keys.add(k);
    }
    public ArrayList<NamespacedKey> getKeys() {
        return this.keys;
    }

    public void addCustomFurnaceRecipe(FurnaceRecipe f) {
        this.customFurnaceRecipes.add(f);
    }
    public ArrayList<FurnaceRecipe> getCustomFurnaceRecipes() {
        return this.customFurnaceRecipes;
    }

    public void addCustomShapedRecipes(ShapedRecipe s) {
        this.customShapedRecipes.add(s);
    }
    public ArrayList<ShapedRecipe> getCustomShapedRecipes() {
        return this.customShapedRecipes;
    }


    public void createCustomShapedRecipes() {
        createCustomIronHorseArmorRecipe();
        createCustomGoldHorseArmorRecipe();
        createCustomDiamondHorseArmorRecipe();
        createCustomNameTagRecipe();
        createCustomSaddleRecipe();
        createCustomEnchantedGoldenAppleRecipe();
    }

    public void createCustomFurnaceRecipes() {
        this.createFurnaceLeatherRecipe();

    }

    public void initCustomRecipes() {
        for(ShapedRecipe s : this.getCustomShapedRecipes()) {
            Bukkit.addRecipe(s);
            this.addKey(s.getKey());
        }

        for(FurnaceRecipe f : this.getCustomFurnaceRecipes()) {
            Bukkit.addRecipe(f);
            this.addKey(f.getKey());
        }
    }

    private void createFurnaceLeatherRecipe() {
        ItemStack newLeather = new ItemStack(Material.LEATHER, 1);

        FurnaceRecipe newLeatherRecipe = new FurnaceRecipe(new NamespacedKey(p, "leather"),
                newLeather, Material.ROTTEN_FLESH,
                .35f, 240);


        this.addCustomFurnaceRecipe(newLeatherRecipe);
    }

    private void createCustomSaddleRecipe() {
        ItemStack newSaddle = new ItemStack(Material.SADDLE, 1);
        ShapedRecipe newSaddleRecipe = new ShapedRecipe(new NamespacedKey(p, "saddle"), newSaddle);
        newSaddleRecipe.shape(
                "XXX",
                "S S",
                "XXX");
        newSaddleRecipe.setIngredient('X', Material.LEATHER);
        newSaddleRecipe.setIngredient('S', Material.STRING);
        this.addCustomShapedRecipes(newSaddleRecipe);
    }

    private void createCustomIronHorseArmorRecipe() {
        ItemStack ironHorseArmor = new ItemStack(Material.IRON_HORSE_ARMOR, 1);

        ShapedRecipe ironHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(p, "ironHorseArmor"), ironHorseArmor);
        ironHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        ironHorseArmorRecipe.setIngredient('X', Material.IRON_INGOT);
        ironHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.addCustomShapedRecipes(ironHorseArmorRecipe);
    }

    private void createCustomGoldHorseArmorRecipe() {
        ItemStack goldHorseArmor = new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1);

        ShapedRecipe goldHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(p, "goldHorseArmor"), goldHorseArmor);
        goldHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        goldHorseArmorRecipe.setIngredient('X', Material.GOLD_INGOT);
        goldHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.addCustomShapedRecipes(goldHorseArmorRecipe);
    }

    private void createCustomDiamondHorseArmorRecipe() {
        ItemStack diamondHorseArmor = new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1);
        ShapedRecipe diamondHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(p, "diamondHorseArmor"), diamondHorseArmor);
        diamondHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        diamondHorseArmorRecipe.setIngredient('X', Material.DIAMOND);
        diamondHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.addCustomShapedRecipes(diamondHorseArmorRecipe);
    }

    private void createCustomNameTagRecipe() {
        ItemStack nameTag = new ItemStack(Material.NAME_TAG,1);
        ShapedRecipe nameTagRecipe = new ShapedRecipe(new NamespacedKey(p, "nameTag"), nameTag);
        nameTagRecipe.shape(
                "IS",
                " L"
        );
        nameTagRecipe.setIngredient('I', Material.IRON_INGOT);
        nameTagRecipe.setIngredient('S', new RecipeChoice.MaterialChoice(
                Material.SPRUCE_SIGN,
                Material.ACACIA_SIGN,
                Material.OAK_SIGN,
                Material.BIRCH_SIGN,
                Material.JUNGLE_SIGN,
                Material.DARK_OAK_SIGN,
                Material.MANGROVE_SIGN,
                Material.CRIMSON_SIGN,
                Material.WARPED_SIGN
        ));
        nameTagRecipe.setIngredient('L', Material.LEATHER);
        this.addCustomShapedRecipes(nameTagRecipe);
    }

    private void createCustomEnchantedGoldenAppleRecipe() {
        ItemStack enchantedGoldenApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ShapedRecipe enchantedGoldenAppleRecipe = new ShapedRecipe(new NamespacedKey(p, "enchantedGoldenApple"), enchantedGoldenApple);
        enchantedGoldenAppleRecipe.shape(
                "GGG",
                "GAG",
                "GGG"
        );
        enchantedGoldenAppleRecipe.setIngredient('G', Material.GOLD_BLOCK);
        enchantedGoldenAppleRecipe.setIngredient('A', Material.APPLE);
        this.addCustomShapedRecipes(enchantedGoldenAppleRecipe);
    }
}
