package me.collinpatrick.customcraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CustomCraft extends JavaPlugin implements Listener {
    private ArrayList<FurnaceRecipe> customFurnaceRecipes = new ArrayList<FurnaceRecipe>();
    private ArrayList<ShapedRecipe> customRecipes = new ArrayList<ShapedRecipe>();
    private ArrayList<NamespacedKey> keys = new ArrayList<NamespacedKey>();
    @Override
    public void onEnable() {
        //Create custom shaped recipes
        createCustomShapedRecipes();
        //Create custom furnace recipe
        createCustomFurnaceRecipes();
        //Init all custom recipes
        initCustomRecipes();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.discoverRecipes(this.keys);
    }

    public void createCustomShapedRecipes() {
        createCustomIronHorseArmorRecipe();
        createCustomGoldHorseArmorRecipe();
        createCustomDiamondHorseArmorRecipe();
        createCustomNameTagRecipe();
        createCustomSaddleRecipe();
    }

    public void createCustomFurnaceRecipes() {
        this.createFurnaceLeatherRecipe();

    }




    public void initCustomRecipes() {
        for(ShapedRecipe s : this.customRecipes) {
            Bukkit.addRecipe(s);
            this.keys.add(s.getKey());
        }

        for(FurnaceRecipe f : this.customFurnaceRecipes) {
            Bukkit.addRecipe(f);
            this.keys.add(f.getKey());
        }
    }

    private void createFurnaceLeatherRecipe() {
        ItemStack newLeather = new ItemStack(Material.LEATHER, 1);

        FurnaceRecipe newLeatherRecipe = new FurnaceRecipe(new NamespacedKey(this, "leather"),
                newLeather, Material.ROTTEN_FLESH,
                .35f, 240);


        this.customFurnaceRecipes.add(newLeatherRecipe);
    }

    private void createCustomSaddleRecipe() {
        ItemStack newSaddle = new ItemStack(Material.SADDLE, 1);
        ShapedRecipe newSaddleRecipe = new ShapedRecipe(new NamespacedKey(this, "saddle"), newSaddle);
        newSaddleRecipe.shape(
                "XXX",
                "S S",
                "XXX");
        newSaddleRecipe.setIngredient('X', Material.LEATHER);
        newSaddleRecipe.setIngredient('S', Material.STRING);
        this.customRecipes.add(newSaddleRecipe);
    }

    private void createCustomIronHorseArmorRecipe() {
        ItemStack ironHorseArmor = new ItemStack(Material.IRON_HORSE_ARMOR, 1);

        ShapedRecipe ironHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(this, "ironHorseArmor"), ironHorseArmor);
        ironHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        ironHorseArmorRecipe.setIngredient('X', Material.IRON_INGOT);
        ironHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.customRecipes.add(ironHorseArmorRecipe);
    }

    private void createCustomGoldHorseArmorRecipe() {
        ItemStack goldHorseArmor = new ItemStack(Material.GOLDEN_HORSE_ARMOR, 1);

        ShapedRecipe goldHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(this, "goldHorseArmor"), goldHorseArmor);
        goldHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        goldHorseArmorRecipe.setIngredient('X', Material.GOLD_INGOT);
        goldHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.customRecipes.add(goldHorseArmorRecipe);
    }

    private void createCustomDiamondHorseArmorRecipe() {
        ItemStack diamondHorseArmor = new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1);
        ShapedRecipe diamondHorseArmorRecipe = new ShapedRecipe(new NamespacedKey(this, "diamondHorseArmor"), diamondHorseArmor);
        diamondHorseArmorRecipe.shape(
                "XXX",
                "XLX",
                "XXX"
        );
        diamondHorseArmorRecipe.setIngredient('X', Material.DIAMOND);
        diamondHorseArmorRecipe.setIngredient('L', Material.LEATHER_HORSE_ARMOR);
        this.customRecipes.add(diamondHorseArmorRecipe);
    }

    private void createCustomNameTagRecipe() {
        ItemStack nameTag = new ItemStack(Material.NAME_TAG,1);
        ShapedRecipe nameTagRecipe = new ShapedRecipe(new NamespacedKey(this, "nameTag"), nameTag);
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
        this.customRecipes.add(nameTagRecipe);
    }
}
