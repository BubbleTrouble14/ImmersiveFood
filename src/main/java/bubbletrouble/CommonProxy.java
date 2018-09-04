package bubbletrouble;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;

public abstract class CommonProxy {

  public void preInit()
  {
	  
  }

  public void init()
  {
      CapabilityManager.INSTANCE.register(IFoodDecay.class, new IFoodDecayStorage(), FoodDecay.class);

      MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
      MinecraftForge.EVENT_BUS.register(new EventHandler());
  }

  public void postInit()
  {

  }
  
  public abstract long getWorldTime();
  
  public abstract long getTime();

  abstract public boolean playerIsInCreativeMode(EntityPlayer player);

  abstract public boolean isDedicatedServer();
}
