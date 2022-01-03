package njoyshadowsmod.fabricchatkoreanconvertor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.text.Text;
import njoyshadowsmod.fabricchatkoreanconvertor.utils.UuidUtil;

import java.util.UUID;

import static net.minecraft.server.command.CommandManager.literal;


public class FabricChatKoreanConvertor implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(literal("enk").executes(context -> {

                UUID playerUUID = context.getSource().getPlayer().getUuid();
                boolean IsExist = false;

                for (UuidUtil.EnKData playerdata: UuidUtil.playerList) {
                    if(playerdata.getUUID() == playerUUID){
                        IsExist = true;

                        playerdata.setIsEnable(!playerdata.getIsEnable());
                        context.getSource().getPlayer().sendSystemMessage(Text.of(String.format("EnK is %s", (playerdata.getIsEnable() ? "enable" : "disable" ))),playerUUID);
                        break;
                    }
                }

                if(!IsExist){
                    UuidUtil.playerList.add(new UuidUtil.EnKData(playerUUID,true));
                    context.getSource().getPlayer().sendSystemMessage(Text.of(String.format("EnK is %s", "enable")),playerUUID);

                }

                return 1;
            }));

            //uuid list check
            dispatcher.register((literal("uenklist").requires(source -> source.hasPermissionLevel(4)).executes(context -> {
                for (UuidUtil.EnKData playerdata : UuidUtil.playerList){
                    System.out.println(playerdata.getUUID());
                    System.out.println(playerdata.getIsEnable());
                }
                return 1;
            })));
        });
    }
}
