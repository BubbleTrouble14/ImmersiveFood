package immersivefood;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ClientOnlyProxy extends CommonProxy
{

  public void preInit()
  {
    super.preInit();
  }

  public void init()
  {
    super.init();
  }

  public void postInit()
  {
    super.postInit();
  }

  @Override
  public boolean playerIsInCreativeMode(EntityPlayer player) {
    if (player instanceof EntityPlayerMP) {
      EntityPlayerMP entityPlayerMP = (EntityPlayerMP)player;
      return entityPlayerMP.interactionManager.isCreative();
    } else if (player instanceof EntityPlayerSP) {
      return Minecraft.getMinecraft().playerController.isInCreativeMode();
    }
    return false;
  }
  
  @Override
  public long getTime() {
	  return Minecraft.getSystemTime();
  }

  @Override
  public long getWorldTime() {
	  if (Minecraft.getMinecraft().world != null)
		  return Minecraft.getMinecraft().world.getTotalWorldTime();
	  return 0;
  }  

  @Override
  public boolean isDedicatedServer() {return false;}

}
