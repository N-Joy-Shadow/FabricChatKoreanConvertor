package njoyshadowsmod.njoyshadowsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import njoyshadowsmod.njoyshadowsmod.mixin.ReceiveMessage;

@Environment(EnvType.CLIENT)
public class NJoyShadowsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("응아잇~~어");
    }
}
