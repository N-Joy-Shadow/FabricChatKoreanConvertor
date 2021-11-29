package njoyshadowsmod.njoyshadowsmod.mixin;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;


import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(ServerPlayNetworkHandler.class)
public abstract class ReceiveMessage {

    @Shadow public abstract ServerPlayerEntity getPlayer();

    @Inject(method = "handleMessage", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/PlayerManager;broadcast(Lnet/minecraft/text/Text;Ljava/util/function/Function;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V"
    ),
    cancellable = true)
    public void receiveMessage(TextStream.Message message, CallbackInfo ci) {
        ci.cancel();
        System.out.println(message.getFiltered());
        System.out.println(this.getPlayer().getGameProfile().getName());
        System.out.println(this.getPlayer().getGameProfile().getId());
    }

}
