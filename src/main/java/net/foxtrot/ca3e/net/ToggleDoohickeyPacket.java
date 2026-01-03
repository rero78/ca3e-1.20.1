package net.foxtrot.ca3e.net;

import net.foxtrot.ca3e.blockentity.MiningDoohickeyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ToggleDoohickeyPacket(BlockPos pos, boolean running) {

    public static void encode(ToggleDoohickeyPacket msg, FriendlyByteBuf buf) {
        buf.writeBlockPos(msg.pos);
        buf.writeBoolean(msg.running);
    }

    public static ToggleDoohickeyPacket decode(FriendlyByteBuf buf) {
        return new ToggleDoohickeyPacket(buf.readBlockPos(), buf.readBoolean());
    }

    public static void handle(ToggleDoohickeyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;
            var level = player.serverLevel();
            var be = level.getBlockEntity(msg.pos);
            if (be instanceof MiningDoohickeyBlockEntity d) {
                d.setChanged();
                level.sendBlockUpdated(msg.pos, d.getBlockState(), d.getBlockState(), 3);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
