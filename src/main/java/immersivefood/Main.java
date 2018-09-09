package immersivefood;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)  //delete guiFactory if MBE70 not present and you don't have a configuration GUI
public class Main
{
  // you also need to update the modid and version in two other places as well:
  //  build.gradle file (the version, group, and archivesBaseName parameters)
  //  resources/mcmod.info (the name, description, and version parameters)
   public static final String MODID = "immersivefood";
    public static final String VERSION = "1.12.2a";

   // public static final String GUIFACTORY = "immersivefood.config.IFGuiFactory"; //delete if MBE70 not present

    // The instance of your mod that Forge uses.  Optional.
    @Mod.Instance(Main.MODID)
    public static Main instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="immersivefood.ClientOnlyProxy", serverSide="immersivefood.DedicatedServerProxy")
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
