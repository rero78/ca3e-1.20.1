package net.foxtrot.ca3e.client.screen;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.foxtrot.ca3e.menu.MiningDoohickeyMenu;
import net.foxtrot.ca3e.net.ModNetworking;
import net.foxtrot.ca3e.net.ToggleDoohickeyPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MiningDoohickeyScreen extends AbstractContainerScreen<MiningDoohickeyMenu> {

    private static final ResourceLocation TEX =
            ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "textures/gui/mining_doohickey_gui.png");


    public MiningDoohickeyScreen(MiningDoohickeyMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        int x = leftPos + 152;
        int y = topPos + 6;

    }

    @Override
    protected void renderBg(GuiGraphics g, float partialTick, int mouseX, int mouseY) {
        g.blit(TEX, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int running = menu.getData().get(0);
        int u = running == 1 ? 18 : 0;
        g.blit(TEX, leftPos + 152, topPos + 6, u, 166, 18, 18);
    }

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        renderBackground(g);
        super.render(g, mouseX, mouseY, partialTick);
        renderTooltip(g, mouseX, mouseY);
    }
}
