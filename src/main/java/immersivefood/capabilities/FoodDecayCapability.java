package immersivefood.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class FoodDecayCapability implements ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject(IFoodDecay.class)
	public static final Capability<IFoodDecay> FOOD_DECAY_CAP = null;

	private IFoodDecay instance = FOOD_DECAY_CAP.getDefaultInstance();

	public FoodDecayCapability(long decayTime, long worldTime) {
		this.instance.setDecayTime(decayTime);
		this.instance.setDecayStart(worldTime);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == FOOD_DECAY_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == FOOD_DECAY_CAP ? FOOD_DECAY_CAP.<T>cast(this.instance) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) FOOD_DECAY_CAP.getStorage().writeNBT(FOOD_DECAY_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		FOOD_DECAY_CAP.getStorage().readNBT(FOOD_DECAY_CAP, this.instance, null, nbt);
	}
}
