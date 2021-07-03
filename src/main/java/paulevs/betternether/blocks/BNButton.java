package paulevs.betternether.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import paulevs.betternether.BlocksHelper;

import java.util.Collections;
import java.util.List;

public class BNButton extends WoodenButtonBlock {
	public BNButton(Block block) {
		super(BlocksHelper.copySettingsOf(block).nonOpaque().noCollision().strength(0.5F));
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		return Collections.singletonList(new ItemStack(this.asItem()));
	}
}
