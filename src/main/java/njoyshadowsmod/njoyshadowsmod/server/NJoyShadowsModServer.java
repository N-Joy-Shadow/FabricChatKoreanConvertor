package njoyshadowsmod.njoyshadowsmod.server;


import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.SERVER)
public class NJoyShadowsModServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        System.out.println("Shout");

    }
}
