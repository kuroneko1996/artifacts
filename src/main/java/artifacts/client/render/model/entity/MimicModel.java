package artifacts.client.render.model.entity;

import artifacts.common.entity.MimicEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class MimicModel extends EntityModel<MimicEntity> {

    protected final ModelRenderer upperTeeth;
    protected final ModelRenderer lowerTeeth;
    protected final ModelRenderer upperMouthOverlay;
    protected final ModelRenderer lowerMouthOverlay;

    public MimicModel() {
        texWidth = 64;
        texHeight = 32;

        upperTeeth = new ModelRenderer(this, 0, 0);
        lowerTeeth = new ModelRenderer(this, 0, 15);
        upperMouthOverlay = new ModelRenderer(this, 24, 0);
        lowerMouthOverlay = new ModelRenderer(this, 36, 15);

        upperTeeth.addBox(-6, 0, -13, 12, 3, 12);
        lowerTeeth.addBox(-6, -4, -13, 12, 3, 12);
        upperMouthOverlay.addBox(-6, 0, -13, 12, 0, 12, 0.02F);
        lowerMouthOverlay.addBox(-6, -1, -13, 12, 0, 12, 0.02F);

        upperTeeth.setPos(0, 15, 7);
        lowerTeeth.setPos(0, 15, 7);
        upperMouthOverlay.setPos(0, 15, 7);
        lowerMouthOverlay.setPos(0, 15, 7);
    }

    @Override
    public void setupAnim(MimicEntity mimic, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void prepareMobModel(MimicEntity mimic, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (mimic.ticksInAir > 0) {
            upperTeeth.xRot = upperMouthOverlay.xRot = Math.max(-60, (mimic.ticksInAir - 1 + partialTicks) * -6) * 0.0174533F;
            lowerTeeth.xRot = lowerMouthOverlay.xRot = Math.min(30, (mimic.ticksInAir - 1 + partialTicks) * 3) * 0.0174533F;
        } else {
            upperTeeth.xRot = upperMouthOverlay.xRot = 0;
            lowerTeeth.xRot = lowerMouthOverlay.xRot = 0;
        }
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        upperTeeth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        lowerTeeth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        upperMouthOverlay.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        lowerMouthOverlay.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
