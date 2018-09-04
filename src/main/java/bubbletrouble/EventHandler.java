package bubbletrouble;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler 
{
	
	private Map<Integer, TickStorage> tick = new HashMap<>();
	
	@SubscribeEvent
	public void tooltipEvent(ItemTooltipEvent event)
	{
		ItemStack stack = event.getItemStack();
		if(event.getEntityPlayer() != null)
		{
			if(stack.getItem() instanceof ItemFood)
			{
				IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				event.getToolTip().add(String.valueOf(food_decay.getDecayTimeLeft()));
			}
		}
	}
	
	@SubscribeEvent
	public void onUpdateEvent(WorldTickEvent event)
	{
		if (event.phase == Phase.START) {
			TickStorage t = tick.get(event.world.provider.getDimension());
			if (t == null) {
				t = new TickStorage();
				tick.put(event.world.provider.getDimension(), t);
			}
			if (t.tick > 20) {
				t.tick = 0;
				for (int i = 0; i < event.world.loadedTileEntityList.size(); i++) {
					if (event.world.loadedTileEntityList.get(i) instanceof IInventory) {
						checkInventoryForDecayable((IInventory) event.world.loadedTileEntityList.get(i));
					}
				}
			} else {
				t.tick++;
			}
		}
	}
	
	public static class TickStorage {
		private int tick;
	}
	
	public static void checkInventoryForDecayable(IInventory inventory) {
		 for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (!stack.isEmpty() && stack.getItem() instanceof ItemFood) {				
				IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				food_decay.decayTick(inventory, i, 1, stack);
			}
		}
	}
	
}
