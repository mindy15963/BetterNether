package paulevs.betternether.advancements;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import paulevs.betternether.BetterNether;

public class ConvertByLightningTrigger extends SimpleCriterionTrigger<ConvertByLightningTrigger.TriggerInstance> {
	static final ResourceLocation ID = BetterNether.makeID("convert_by_lightning");
	
	public ResourceLocation getId() {
		return ID;
	}
	
	public ConvertByLightningTrigger.TriggerInstance createInstance(JsonObject jsonObject, EntityPredicate.Composite composite, DeserializationContext deserializationContext) {
		return new ConvertByLightningTrigger.TriggerInstance(composite, ItemPredicate.fromJson(jsonObject.get("item")));
	}
	
	public void trigger(ServerPlayer serverPlayer, ItemLike item) {
		this.trigger(serverPlayer, (triggerInstance) -> {
			return triggerInstance.matches(new ItemStack(item));
		});
	}
	
	public static class TriggerInstance extends AbstractCriterionTriggerInstance {
		private final ItemPredicate item;
		
		public TriggerInstance(EntityPredicate.Composite composite, ItemPredicate itemPredicate) {
			super(ConvertByLightningTrigger.ID, composite);
			this.item = itemPredicate;
		}
		
		
		public boolean matches(ItemStack itemStack) {
			return this.item.matches(itemStack);
		}
		
		public JsonObject serializeToJson(SerializationContext serializationContext) {
			JsonObject jsonObject = super.serializeToJson(serializationContext);
			jsonObject.add("item", this.item.serializeToJson());
			return jsonObject;
		}
	}
}
