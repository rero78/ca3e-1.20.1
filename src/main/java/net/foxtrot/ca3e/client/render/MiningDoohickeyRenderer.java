package net.foxtrot.ca3e.client.render;

import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.foxtrot.ca3e.client.model.MiningDoohickeyModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MiningDoohickeyRenderer extends GeoBlockRenderer<MiningDoohickeyBlockEntity> {
    public MiningDoohickeyRenderer(BlockEntityRendererProvider.Context ctx) {
        super(new MiningDoohickeyModel());
    }
}
