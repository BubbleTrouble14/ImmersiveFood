package immersivefood.config;

import immersivefood.Main;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@Config(modid = Main.MODID)
@Config.LangKey("immersivefood.config.mainTitle")
public class ModConfig {

/*    @Config.Comment("This is an example boolean property.")
    public static boolean fooBar = false;

    @Config.Comment({"This is an example enum property.", "It will use a GuiConfigEntries.CycleValueEntry in the config GUI."})
    public static EnumExample exampleEnumProperty = EnumExample.VALUE_1;

    @Config.Comment({"This an example Map field.", "It will be converted to a category containing a property for each key-value pair."})
    public static final Map<String, Double> exampleMapField = new HashMap<>();*/

    @Config.Comment("Value for decay timer (seconds)")
    public static int decayTimer = 200;

    @Config.Comment("Multiplier value for decay timer")
    public static int decayTimerMultiplier = 1;

/*
    static {
        exampleMapField.put("foobar", 2.0);
        exampleMapField.put("foobaz", 100.5);
        exampleMapField.put("barbaz", Double.MAX_VALUE);
    }
*/

/*
    public static final Client client = new Client();

    public enum EnumExample {
        VALUE_1,
        VALUE_2,
        VALUE_3,
        VALUE_4
    }
*/

/*    public static class Client {

        @Config.Comment("This is an example int property.")
        public int baz = -100;

        @Config.Comment("This is an example enum property in a subcategory of the main category.")
        public EnumExample exampleSubcategoryEnumProperty = EnumExample.VALUE_3;

        @Config.Comment("This is an example enum property that uses an enum defined in a nested class.")
        public EnumExampleNested exampleNestedEnumProperty = EnumExampleNested.NESTED_2;

        public final HUDPos chunkEnergyHUDPos = new HUDPos(0, 0);

        public enum EnumExampleNested {
            NESTED_1,
            NESTED_2,
            NESTED_3,
            NESTED_4,
            NESTED_5
        }

        public static class HUDPos {
            public HUDPos(final int x, final int y) {
                this.x = x;
                this.y = y;
            }

            @Config.Comment("The x coordinate")
            public int x;

            @Config.Comment("The y coordinate")
            public int y;
        }
    }*/

    @Mod.EventBusSubscriber(modid = Main.MODID)
    private static class EventHandler {

        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Main.MODID)) {
                ConfigManager.sync(Main.MODID, Config.Type.INSTANCE);
            }
        }
    }
}