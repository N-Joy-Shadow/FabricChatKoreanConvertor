package njoyshadowsmod.fabricchatkoreanconvertor.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import njoyshadowsmod.fabricchatkoreanconvertor.utils.UuidUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinOnDisconnected {
    @Shadow public abstract ServerPlayerEntity getPlayer();
    @Inject(method = "onDisconnected", at = @At(value = "HEAD"))
    public void onDisconnected(Text reason, CallbackInfo ci){
        UUID PlayerUUID = this.getPlayer().getUuid();

        for(int i = 0; i < UuidUtil.playerList.size(); i++){
            if(UuidUtil.playerList.get(i).getUUID() == PlayerUUID){
                try{
                    UuidUtil.playerList.remove(i);
                }
                catch (Exception E){
                    System.out.println(E.getMessage());
                }
                break;
            }
        }

    }
}
