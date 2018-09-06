package immersivefood.capabilities;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public interface IFoodDecay 
{	
	public long getDecayStart();
	
	public float getDecayModifier();
	
	public void setDecayModifier(float decayModifier);

	public void setDecayStart(long decayStart);
	
	public void decayTick(IItemHandlerModifiable inventory, int slotId, float decayModifier, ItemStack stack, World world);

	public void decayTick(IItemHandlerModifiable inventory, int slotId, float decayModifier, ItemStack stack);
	
	public boolean shouldRemove();

	public long getRemovalTime();

	public long getDecayTimeLeft();
	
	public long getDecayTime();
	
	public void setDecayTime(long decayTime);
}
