package bubbletrouble;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CapabilityHandler {
	public static final ResourceLocation FOODDECAY_CAP = new ResourceLocation(Main.MODID, "food_decay");

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if (event.getObject().getItem() instanceof ItemFood) {
			event.addCapability(FOODDECAY_CAP, new FoodDecayCapability(2000000, Main.proxy.getWorldTime()));
		}
	}
}