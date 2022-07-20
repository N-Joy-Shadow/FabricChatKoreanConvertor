package njoyshadow.enk;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import njoyshadow.enk.utils.UUidUtil;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.UUID;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import static net.minecraft.server.command.CommandManager.literal;


public class Enk implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("enk").executes(ctx -> {
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
            }));
        });
    }

}
