package bubbletrouble;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;

public abstract class CommonProxy {

	public void preInit() {
		CapabilityManager.INSTANCE.register(IFoodDecay.class, new IFoodDecayStorage(), FoodDecay.class);
	}

	public void init() {
	}

	public void postInit() {

	}

	public abstract long getWorldTime();

	public abstract long getTime();

	abstract public boolean playerIsInCreativeMode(EntityPlayer player);

	abstract public boolean isDedicatedServer();
}
