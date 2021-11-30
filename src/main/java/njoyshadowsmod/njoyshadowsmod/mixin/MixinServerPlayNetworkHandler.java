package njoyshadowsmod.njoyshadowsmod.mixin;

import net.minecraft.network.MessageType;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;


import net.minecraft.server.PlayerManager;
import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import net.minecraft.text.TranslatableText;
import njoyshadowsmod.njoyshadowsmod.util.KoreanConvertorUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;


@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
            @Shadow public abstract ServerPlayerEntity getPlayer();
            @Shadow @Final private MinecraftServer server;

    @Shadow public ServerPlayerEntity player;

    @Inject(method = "handleMessage", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Ljava/util/function/Function;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"

    ),
    cancellable = true)
    public void receiveMessage(TextStream.Message message, CallbackInfo ci) {
        //cancel send message
        ci.cancel();
        //message
        String Rawstring = message.getRaw();
        String string = message.getFiltered();
        //s
        if(Rawstring.startsWith("-")){
            var s = Rawstring.substring(1).toCharArray();
            //string = new KoreanConvertorUtil.EngChar(string.substring(1)); 왜안됨?
        }

        TranslatableText text = string.isEmpty() ? null : new TranslatableText("chat.type.text", this.player.getDisplayName(), string + " <- 응디" ); //쓸모 있는지 모르겠음
        TranslatableText text2 = new TranslatableText("chat.type.text", this.player.getDisplayName(), string + " <- 빵디");
        this.server.getPlayerManager().broadcast(text2, player -> this.player.shouldFilterMessagesSentTo((ServerPlayerEntity)player) ? text : text2, MessageType.CHAT, this.player.getUuid());
    }

}
