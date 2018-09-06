package immersivefood.capabilities;

import immersivefood.Main;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FoodDecay implements IFoodDecay
{
	private long decayStart = -1;
	private float decayModifier = 1;
	private long decayTime;

	@Override
	public long getDecayStart() {
		return decayStart;
	}
	
	@Override
	public float getDecayModifier() {
		return decayModifier;
	}

	@Override
	public void setDecayModifier(float decayModifier) {
		this.decayModifier = decayModifier;
	}

	@Override
	public void setDecayStart(long decayStart) {
		this.decayStart = decayStart;
	}

	@Override
	public boolean shouldRemove() {
		long decayStart = getDecayStart();
		return decayStart >= 0 && getRemovalTime() <= Main.proxy().getWorldTime();
	}

	@Override
	public long getRemovalTime() {
		return (long) (getDecayStart() + getDecayTime() * decayModifier);
	}

	@Override
	public long getDecayTimeLeft() {
		return getDecayStart() > -1 ? getRemovalTime() - Main.proxy().getWorldTime() : 0;
	}

	@Override
	public long getDecayTime()
	{
		return this.decayTime;
	}
	
	@Override
	public void setDecayTime(long decayTime)
	{
		this.decayTime = decayTime;
	}

	@Override
	public void decayTick(IInventory inventory, int slotId, float decayModifier, ItemStack stack, World world) {
		//if (world.isRemote || world.getTotalWorldTime() % 20 != 0) return;
		decayTick(inventory, slotId, decayModifier, stack);		
	}

	@Override
	public void decayTick(IInventory inventory, int slotId, float decayModifier, ItemStack stack) {
		//setDecayModifier(decayModifier);
		if (getDecayStart() < 0) 
		{
			setDecayStart(Main.proxy().getWorldTime());
		}
		if (shouldRemove()) {
			stack.shrink(1);
			setDecayStart(Main.proxy().getWorldTime());
			if (stack.getCount() <= 0) inventory.setInventorySlotContents(slotId, ItemStack.EMPTY);
		}		
	}
}
