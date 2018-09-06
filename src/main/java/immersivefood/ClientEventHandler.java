package immersivefood;

import immersivefood.capabilities.FoodDecayCapability;
import immersivefood.capabilities.IFoodDecay;
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
			if (stack.getItem() instanceof ItemFood && !Main.proxy().playerIsInCreativeMode(event.getEntityPlayer())) {
				IFoodDecay food_decay = stack.getCapability(FoodDecayCapability.FOOD_DECAY_CAP, null);
				String ret = formatTime(food_decay.getDecayTimeLeft());
				event.getToolTip().add("Decomposes in" + ret);
			}
		}
	}
	
	public static String formatTime(long ticks) {
		long seconds = (long) Math.ceil(ticks / 20d);
		String toAdd = "";
		if (seconds > 0) {
			if (seconds > 59) {
				long minutes = seconds / 60;
				seconds = seconds % 60;
				if (minutes > 59) {
					long hours = minutes / 60;
					minutes = minutes % 60;
					if (hours > 23) {
						long days = hours / 24;
						hours = hours % 24;
						toAdd += " " + (days == 1 ? I18n.format("immersivefood.day", days) : I18n.format("immersivefood.days",
								days));
					}
					toAdd += " " + (hours == 1 ? I18n.format("immersivefood.hour", hours) : I18n.format("immersivefood.hours",
							hours));
				}
				toAdd += " " + (minutes == 1 ? I18n.format("immersivefood.minute", minutes) : I18n.format("immersivefood.minutes",
						minutes));
			}
			toAdd += " " + (seconds == 1 ? I18n.format("immersivefood.second", seconds) : I18n.format("immersivefood.seconds",
					seconds));
		}
		return toAdd;
	}
}

class I18n
{
	public static String format(String key, Object... args)
	{
		return net.minecraft.client.resources.I18n.format(key, args);
	}

	public static String translate(String key)
	{
		return format(key);
	}
}
