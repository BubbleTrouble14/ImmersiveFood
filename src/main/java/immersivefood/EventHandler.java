package immersivefood;

import java.util.HashMap;
import java.util.Map;

import immersivefood.capabilities.FoodDecayCapability;
import immersivefood.capabilities.IFoodDecay;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

@Mod.EventBusSubscriber
public class EventHandler {

	private static Map<Integer, TickStorage> tick = new HashMap<>();

	public static void checkInventoryForDecayable(IInventory inventory, World world) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack.hasCapability(FoodDecayCapability.FOOD_DECAY_CAP, null)) {
				IFoodDecay foodDecay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				foodDecay.decayTick(inventory, i, 1, stack, world);
			}
		}
	}

	@SubscribeEvent
	public static void onUpdateEvent(WorldTickEvent event) {
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
						checkInventoryForDecayable((IInventory) event.world.loadedTileEntityList.get(i), event.world);
					}
				}
			} else {
				t.tick++;
			}
		}
	}

	@SubscribeEvent
	public static void onItemPickedUp(ItemPickupEvent event) {
		ItemStack stack = event.pickedUp.getItem();
		if (stack.getItem() instanceof ItemFood) {
			IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
			food_decay.setDecayTime(10 * 20);
		}
	}

	public static class TickStorage {
		private int tick;
	}
}