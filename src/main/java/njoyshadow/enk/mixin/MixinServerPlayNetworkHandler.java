package njoyshadow.enk.mixin;



import net.minecraft.network.message.MessageType;

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

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static njoyshadow.enk.utils.UUidUtil.IsCrime;


@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
    @Shadow
    @Final
    private MinecraftServer server;

    @Shadow public ServerPlayerEntity player;

    @Shadow public abstract ServerPlayerEntity getPlayer();

    @Shadow protected abstract CompletableFuture<Text> decorateChat(String query);

    @Inject(method = "onChatMessage",at = @At(value ="HEAD"),cancellable = true)
    public void receiveMessage(ChatMessageC2SPacket packet, CallbackInfo ci) throws ExecutionException, InterruptedException, TimeoutException {
        ci.cancel();
        String Message = packet.chatMessage();
        for (UUidUtil.EnKData playerdata : UUidUtil.playerList) {
            if (this.getPlayer().getUuid().equals(playerdata.getUUID()) && playerdata.getIsEnable()) {
                Message = new ExceptionStringUtil().getString(Message);
                break;
            }
        }

        String Name_Message = !IsCrime ? String.format("<%s> %s",this.player.getName().getString(),Message) : Message ;

        //TODO what is this?
        this.server.getMessageDecorator().decorate(this.player,Text.of(Message)).thenAccept(x ->{
            System.out.println(String.format("DecoTxt %s ",x.getString()));
        });
        this.server.logChatMessage(Text.of(Message),MessageType.params(MessageType.CHAT, this.player),null);
        Iterator IteratorPlayer = Arrays.stream(this.server.getPlayerNames()).iterator();
        while(IteratorPlayer.hasNext()){
            ServerPlayerEntity serverPlayerEntity = this.server.getPlayerManager().getPlayer((String)IteratorPlayer.next());
            Text text = Text.of(Name_Message);
            if (text != null) {
                serverPlayerEntity.sendMessageToClient(text, false);
                //serverPlayerEntity.sendChatMessage((SentMessage) Text.of(Message),false,MessageType.params(MessageType.CHAT, this.player));
            }
        }
    }
}
