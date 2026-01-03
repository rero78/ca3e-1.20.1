package net.foxtrot.ca3e.client.model;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MiningDoohickeyModel extends GeoModel<MiningDoohickeyBlockEntity> {

    private static final ResourceLocation MODEL = ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "geo/mining_doohickey.geo.json");
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "textures/block/mining_doohickey.png");
    private static final ResourceLocation ANIM = ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "animations/mining_doohickey.animation.json");

    @Override
    public ResourceLocation getModelResource(MiningDoohickeyBlockEntity animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(MiningDoohickeyBlockEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(MiningDoohickeyBlockEntity animatable) {
        return ANIM;
    }
}
