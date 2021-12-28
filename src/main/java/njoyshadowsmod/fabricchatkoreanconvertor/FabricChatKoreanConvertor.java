package njoyshadowsmod.fabricchatkoreanconvertor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
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
                boolean IsTwice = false;
                for (EnKData playerdata: UUidUtil.playerList) {
                    if(playerdata.getUUID() == playerUUID){
                        IsExist = true;
                        break;
                    }
                }
                if(!IsExist){
                    UUidUtil.playerList.add(new EnKData(playerUUID,true));
                }
                return 1;
            }));
        });

    }
}
