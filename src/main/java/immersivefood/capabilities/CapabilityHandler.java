package immersivefood.capabilities;

import com.google.common.collect.ImmutableList;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import immersivefood.Main;
import immersivefood.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class CapabilityHandler {

	public static final ResourceLocation FOODDECAY_CAP = new ResourceLocation(Main.MODID, "food_decay");

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<ItemStack> event) {
		if (event.getObject().getItem() instanceof ItemFood) {
			long worldTime = Main.proxy().getWorldTime();
			worldTime = worldTime - worldTime % 20;

			ItemFood item = (ItemFood) event.getObject().getItem();

			int healAmount = item.getHealAmount(event.getObject());
			float saturation = item.getSaturationModifier(event.getObject());

			int baseDecayTime = ModConfig.decayTimer *20;
			float multiplier = healAmount * saturation;
			long decayTime = (long) (baseDecayTime * multiplier);
			event.addCapability(FOODDECAY_CAP, new FoodDecayCapability(decayTime * ModConfig.decayTimerMultiplier, worldTime));

		}
	}
}
