package njoyshadow.enk.Init.commands.admin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static njoyshadow.enk.utils.UUidUtil.IsCrime;

public class CrimeMod {
    public static int On(CommandContext<ServerCommandSource> ctx) {
        IsCrime = true;
        ctx.getSource().getPlayer().sendMessage(Text.of("Crime mode on!"));
        return 1;
    }
    public static int Off(CommandContext<ServerCommandSource> ctx) {
        IsCrime = false;
        ctx.getSource().getPlayer().sendMessage(Text.of("Crime mode off!"));
        return 1;
    }
    public static int Info(CommandContext<ServerCommandSource> ctx) {
        ctx.getSource().getPlayer().sendMessage(Text.of("CrimeMode is mode that does not show nicknames to others!"));
        return 1;
    }

}
