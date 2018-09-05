package bubbletrouble;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFoodDecay 
{	
	public long getDecayStart();
	
	public float getDecayModifier();
	
	public void setDecayModifier(float decayModifier);

	public void setDecayStart(long decayStart);
	
	public void decayTick(IInventory inventory, int slotId, float decayModifier, ItemStack stack, World world);

	public void decayTick(IInventory inventory, int slotId, float decayModifier, ItemStack stack);
	
	public boolean shouldRemove();

	public long getRemovalTime();

	public long getDecayTimeLeft();
	
	public long getDecayTime();
	
	public void setDecayTime(long decayTime);
}
