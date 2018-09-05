package bubbletrouble;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.items.IItemHandler;

@Mod.EventBusSubscriber
public class EventHandler 
{
	private Map<Integer, TickStorage> tick = new HashMap<>();
	
	public static void checkInventoryForDecayable(IItemHandler inventory) {
		 for (int i = 0; i < inventory.getSlots(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack.hasCapability(FoodDecayCapability.FOOD_DECAY_CAP, null)) {
				IFoodDecay foodDecay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				foodDecay.decayTick(inventory, i, 1, stack);
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
					if (event.world.loadedTileEntityList.get(i) instanceof IItemHandler) {
						checkInventoryForDecayable((IItemHandler) event.world.loadedTileEntityList.get(i));
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
}
