package net.foxtrot.ca3e.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.entity.custom.MushletEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MushletRenderer extends MobRenderer<MushletEntity, MushletModel<MushletEntity>> {
    public MushletRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MushletModel<>(pContext.bakeLayer(ModModelLayers.MUSHLET_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MushletEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "textures/entity/mushlet.png");
    }

    @Override
    public void render(MushletEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}