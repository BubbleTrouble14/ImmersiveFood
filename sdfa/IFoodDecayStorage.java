package bubbletrouble;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class IFoodDecayStorage implements IStorage<IFoodDecay> 
{
	@Override
	public NBTBase writeNBT(Capability<IFoodDecay> capability, IFoodDecay instance, EnumFacing side) 
	{
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setLong("decayStart", instance.getDecayStart());
		tag.setFloat("decayModifier", instance.getDecayModifier());
		return tag;
	}

	@Override
	public void readNBT(Capability<IFoodDecay> capability, IFoodDecay instance, EnumFacing side, NBTBase nbt) 
	{
		final NBTTagCompound tag = (NBTTagCompound) nbt;
		instance.setDecayStart(tag.getLong("decayStart"));
		instance.setDecayStart(tag.getLong("decayModifier"));
	}
}
