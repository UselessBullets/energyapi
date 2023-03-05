package sunsetsatellite.energyapi;

import net.fabricmc.api.ModInitializer;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sunsetsatellite.energyapi.template.blocks.BlockBatteryBox;
import sunsetsatellite.energyapi.template.items.ItemBattery;
import sunsetsatellite.energyapi.template.items.ItemBatteryUnlimited;
import sunsetsatellite.energyapi.template.items.ItemBatteryVoid;
import sunsetsatellite.energyapi.template.tiles.TileEntityBatteryBox;
import sunsetsatellite.energyapi.util.Config;
import turniplabs.halplibe.helper.BlockHelper;
import turniplabs.halplibe.helper.EntityHelper;
import turniplabs.halplibe.helper.ItemHelper;
import turniplabs.halplibe.helper.TextureHelper;


public class EnergyAPI implements ModInitializer {
    public static final String MOD_ID = "energyapi";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Block batteryBox;
    public static Block generator;
    public static Block machine;
    public static Block wire;
    public static Item battery;
    public static Item batteryUnlimited;
    public static Item batteryVoid;

    public static int[][] batteryTex = new int[7][];

    @Override
    public void onInitialize() {
        LOGGER.info("EnergyAPI initialized.");
    }

    public EnergyAPI(){
        if(Config.getFromConfig("enableTemplateBatteryBox",1) == 1){
            batteryBox = BlockHelper.createBlock(MOD_ID,new BlockBatteryBox(1000, Material.iron),"batteryBox","machineside.png","batterybox.png", Block.soundMetalFootstep,1,1,0);
            EntityHelper.createTileEntity(TileEntityBatteryBox.class,"Battery Box");
        }
        if(Config.getFromConfig("enableTemplateBattery",1) == 1){
            int[] tex = TextureHelper.registerItemTexture(MOD_ID,"battery0.png");
            batteryTex[0] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery1.png");
            batteryTex[1] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery2.png");
            batteryTex[2] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery3.png");
            batteryTex[3] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery4.png");
            batteryTex[4] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery5.png");
            batteryTex[5] = tex;
            tex = TextureHelper.registerItemTexture(MOD_ID,"battery6.png");
            batteryTex[6] = tex;

            battery = ItemHelper.createItem(MOD_ID,new ItemBattery(400),"battery","battery0.png").setMaxStackSize(1);
            batteryUnlimited = ItemHelper.createItem(MOD_ID,new ItemBatteryUnlimited(401),"batteryUnlimited","batteryUnlimited.png").setMaxStackSize(1);
            batteryVoid = ItemHelper.createItem(MOD_ID,new ItemBatteryVoid(402),"batteryVoid","batteryVoid.png").setMaxStackSize(1);
        }
        Config.init();
    }

    public static double map(double valueCoord1,
                             double startCoord1, double endCoord1,
                             double startCoord2, double endCoord2) {

        final double EPSILON = 1e-12;
        if (Math.abs(endCoord1 - startCoord1) < EPSILON) {
            throw new ArithmeticException("Division by 0");
        }

        double ratio = (endCoord2 - startCoord2) / (endCoord1 - startCoord1);
        return ratio * (valueCoord1 - startCoord1) + startCoord2;
    }

}