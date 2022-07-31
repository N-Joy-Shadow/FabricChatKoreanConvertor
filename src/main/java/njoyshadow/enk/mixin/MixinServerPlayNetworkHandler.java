package njoyshadow.enk.mixin;



import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import njoyshadow.enk.utils.ExceptionStringUtil;
import njoyshadow.enk.utils.UUidUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
    @Shadow
    @Final
    private MinecraftServer server;

    @Shadow public ServerPlayerEntity player;

    @Shadow public abstract ServerPlayerEntity getPlayer();

    @Inject(method = "onChatMessage",at = @At(value ="HEAD"),cancellable = true)
    public void receiveMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        ci.cancel();
        String Message = packet.getChatMessage();
        for (UUidUtil.EnKData playerdata : UUidUtil.playerList) {
            if (this.getPlayer().getUuid().equals(playerdata.getUUID()) && playerdata.getIsEnable()) {
                Message = new ExceptionStringUtil().getString(Message);
                break;
            }
        }

        this.server.getPlayerManager().broadcast(SignedMessage.of(Text.of(Message)),this.getPlayer().asMessageSender(),MessageType.CHAT);

    }
}
