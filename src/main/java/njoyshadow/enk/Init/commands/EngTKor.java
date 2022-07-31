package njoyshadow.enk.Init.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import njoyshadow.enk.utils.UUidUtil;

import java.util.UUID;

public class EngTKor {
    public static int Enk(CommandContext<ServerCommandSource> ctx){
        UUID playerUUID = ctx.getSource().getPlayer().getUuid();
        boolean IsExist = false;

        for(UUidUtil.EnKData playerdata : UUidUtil.playerList){
            if(playerdata.getUUID() == playerUUID){
                IsExist = true;

                playerdata.setIsEnable(!playerdata.getIsEnable());
                ctx.getSource().getPlayer().sendMessage(Text.of(String.format("EnK is %s", (playerdata.getIsEnable() ? "enable" : "disable" ))));
                break;
            }

        }
        if(!IsExist){
            UUidUtil.playerList.add(new UUidUtil.EnKData(playerUUID,true));
            ctx.getSource().getPlayer().sendMessage(Text.of(String.format("EnK is %s", "enable" )));
        }
        return 1;
    }
}
