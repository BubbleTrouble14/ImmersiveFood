package bubbletrouble;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DedicatedServerProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
	}

	public void init() {
		super.init();
	}

	/**
	 * Handle interaction with other mods, complete your setup based on this.
	 */
	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
			return entityPlayerMP.interactionManager.isCreative();
		}
		return false;
	}

	@Override
	public boolean isDedicatedServer() {
		return true;
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
}
