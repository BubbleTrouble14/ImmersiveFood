package immersivefood;

import immersivefood.capabilities.FoodDecayCapability;
import immersivefood.capabilities.IFoodDecay;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class EventHandler {

	private static Map<Integer, TickStorage> tick = new HashMap<>();
	private static int tick_player = 0;

	public static void checkInventoryForDecayable(IItemHandlerModifiable inventory, World world) {
		for (int i = 0; i < inventory.getSlots(); i++) {
			ItemStack stack = inventory.getStackInSlot(i);
			if (stack.getItem() instanceof ItemFood && stack.hasCapability(FoodDecayCapability.FOOD_DECAY_CAP, null)) {
				IFoodDecay foodDecay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				foodDecay.decayTick(inventory, i, 1, stack, world);
			}
		}
	}

	@SubscribeEvent
	public static void onPlayerEvent(PlayerTickEvent event) {
		if (event.phase == Phase.START) {
			if (tick_player > 20) {
				tick_player = 0;
				EntityPlayer player = event.player;
				if (player.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
					IItemHandler itemHandler = player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
					if (itemHandler instanceof IItemHandlerModifiable) {
						checkInventoryForDecayable((IItemHandlerModifiable) itemHandler, player.world);
					}
				}
			} else {
				tick_player++;
			}
		}
	}

	@SubscribeEvent
	public static void onUpdateEvent(WorldTickEvent event) {
		if (event.phase == Phase.START) {
			TickStorage t = tick.get(event.world.provider.getDimension());
			if (t == null) {
				t = new TickStorage(event.world);
				tick.put(event.world.provider.getDimension(), t);
			}
			if (t.shouldTick()) {
				for (TileEntity tileEntity : event.world.loadedTileEntityList) {
					if (tileEntity.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
						IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
						if (itemHandler instanceof IItemHandlerModifiable) {
							checkInventoryForDecayable((IItemHandlerModifiable) itemHandler, event.world);
						}
					}
				}
			}
		}
	}

/*	@SubscribeEvent
	public static void onItemPickedUp(ItemPickupEvent event) {
		ItemStack stack = event.pickedUp.getItem();
		if (stack.getItem() instanceof ItemFood) {
			IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
			food_decay.setDecayTime(10 * 20);
		}
	}*/

	public static class TickStorage {
		private final World world;

		public TickStorage(World world) {
			this.world = world;
		}

		public boolean shouldTick() {
			return world.getTotalWorldTime() % 20 == 0;
		}
	}
}
