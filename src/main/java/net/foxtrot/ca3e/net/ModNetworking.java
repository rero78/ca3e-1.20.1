package net.foxtrot.ca3e.net;

import net.foxtrot.ca3e.CataclysmAwaits;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworking {

    public static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(CataclysmAwaits.MOD_ID, "main"),
            () -> PROTOCOL, PROTOCOL::equals, PROTOCOL::equals
    );

    public static void register() {
        int id = 0;
        CHANNEL.messageBuilder(ToggleDoohickeyPacket.class, id++)
                .encoder(ToggleDoohickeyPacket::encode)
                .decoder(ToggleDoohickeyPacket::decode)
                .consumerMainThread(ToggleDoohickeyPacket::handle)
                .add();
    }
}
