package njoyshadowsmod.fabricchatkoreanconvertor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.network.C2SPacketTypeCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.text.Text;
import njoyshadowsmod.fabricchatkoreanconvertor.utils.EnKData;
import njoyshadowsmod.fabricchatkoreanconvertor.utils.UUidUtil;

import javax.sql.rowset.BaseRowSet;
import java.util.Arrays;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;


public class FabricChatKoreanConvertor implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("enk").executes(context -> {

                UUID playerUUID = context.getSource().getPlayer().getUuid();
                boolean IsExist = false;

                for (EnKData playerdata: UUidUtil.playerList) {
                    if(playerdata.getUUID() == playerUUID){
                        IsExist = true;

                        playerdata.setIsEnable(!playerdata.getIsEnable());
                        context.getSource().getPlayer().sendSystemMessage(Text.of(String.format("EnK is %s", (playerdata.getIsEnable() ? "enable" : "disable" ))),playerUUID);
                        break;
                    }
                }

                if(!IsExist){
                    UUidUtil.playerList.add(new EnKData(playerUUID,true));
                    context.getSource().getPlayer().sendSystemMessage(Text.of(String.format("EnK is %s", "enable")),playerUUID);

                }

                return 1;
            }));
            /*
            dispatcher.register((literal("enklist").executes(context -> {
                for (EnKData playerdata : UUidUtil.playerList){
                    System.out.println(playerdata.getUUID());
                }
                return 1;
            })));*/
        });
    }
}
