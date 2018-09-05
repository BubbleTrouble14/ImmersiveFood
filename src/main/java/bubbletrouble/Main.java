package bubbletrouble;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION) 
public class Main
{
   public static final String MODID = "immersive_food";
    public static final String VERSION = "1.12.2a";

    @Mod.Instance(Main.MODID)
    public static Main instance;

    @SidedProxy(clientSide="bubbletrouble.ClientOnlyProxy", serverSide="bubbletrouble.DedicatedServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
      proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
      proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
      proxy.postInit();
    }

	public static CommonProxy proxy() {
		return proxy;
	}

}
