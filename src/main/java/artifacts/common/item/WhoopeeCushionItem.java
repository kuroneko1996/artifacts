package artifacts.common.item;

import artifacts.Artifacts;
import artifacts.client.render.model.curio.WhoopeeCushionModel;
import artifacts.common.init.SoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class WhoopeeCushionItem extends ArtifactItem {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Artifacts.MODID, "textures/entity/curio/whoopee_cushion.png");

    public WhoopeeCushionItem() {
        super(new Item.Properties(), "whoopee_cushion");
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return Curio.createProvider(new Curio(this) {
            private Object model;

            @Override
            public void curioTick(String identifier, int index, LivingEntity entity) {
                if (!entity.world.isRemote()) {
                    CompoundNBT tag = stack.getOrCreateTag();
                    if (tag.getBoolean("HasFarted") && !entity.isSneaking()) {
                        tag.putBoolean("HasFarted", false);
                    } else if (!tag.getBoolean("HasFarted") && entity.isSneaking()) {
                        tag.putBoolean("HasFarted", true);
                        if (entity.getRNG().nextInt(8) == 0) {
                            entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), SoundEvents.FART, SoundCategory.PLAYERS, 1, 0.9F + entity.getRNG().nextFloat() * 0.2F);
                        }
                    }
                }
            }

            @Override
            protected SoundEvent getEquipSound() {
                return SoundEvents.FART;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            protected WhoopeeCushionModel getModel() {
                if (model == null) {
                    model = new WhoopeeCushionModel();
                }
                return (WhoopeeCushionModel) model;
            }

            @Override
            @OnlyIn(Dist.CLIENT)
            protected ResourceLocation getTexture() {
                return TEXTURE;
            }
        });
    }
}
