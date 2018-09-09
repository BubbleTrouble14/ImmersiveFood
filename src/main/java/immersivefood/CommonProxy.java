package immersivefood;

import immersivefood.capabilities.FoodDecay;
import immersivefood.capabilities.IFoodDecay;
import immersivefood.capabilities.IFoodDecayStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.capabilities.CapabilityManager;

public abstract class CommonProxy {

	public void preInit() {
/*
		IFConfiguration.preInit();
		System.out.println("MBE70: myInteger=" + IFConfiguration.myInteger
				+ "; myBoolean=" + IFConfiguration.myBoolean
				+ "; myString=" + IFConfiguration.myString);
		System.out.println("MBE70: myDouble=" + IFConfiguration.myDouble
				+ "; myColour=" + IFConfiguration.myColour);
		System.out.print("MBE70: myIntList=");
		for (int value : IFConfiguration.myIntList) {
			System.out.print(value + "; ");
		}
		System.out.println();*/

	    CapabilityManager.INSTANCE.register(IFoodDecay.class, new IFoodDecayStorage(), FoodDecay::new);

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
