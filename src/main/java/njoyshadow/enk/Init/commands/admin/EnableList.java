package njoyshadow.enk.Init.commands.admin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import njoyshadow.enk.utils.UUidUtil;

public class EnableList {
    public static int enklist(CommandContext<ServerCommandSource> ctx){
        for(UUidUtil.EnKData playerdata : UUidUtil.playerList) {
            String data = String.format("%s : %s",playerdata.getUUID(),playerdata.getIsEnable());
            ctx.getSource().getPlayer().sendMessage(Text.of(data));
        }
        return 1;
    }
}
