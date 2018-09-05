package bubbletrouble;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {
	@SubscribeEvent
	public static void tooltipEvent(ItemTooltipEvent event) {
		ItemStack stack = event.getItemStack();
		if (event.getEntityPlayer() != null) {
			if (!Main.proxy().playerIsInCreativeMode(event.getEntityPlayer()) && stack.getItem() instanceof ItemFood) {
				//System.out.println(stack);
				IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				System.out.println(food_decay.getDecayTimeLeft());

				//event.getToolTip().add(String.valueOf(food_decay.getDecayTimeLeft()));
			}
		}
	}
}
