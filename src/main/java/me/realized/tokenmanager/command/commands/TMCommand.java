package me.realized.tokenmanager.command.commands;

import me.realized.tokenmanager.Permissions;
import me.realized.tokenmanager.TokenManagerPlugin;
import me.realized.tokenmanager.command.BaseCommand;
import me.realized.tokenmanager.command.commands.subcommands.*;
import me.realized.tokenmanager.command.commands.subcommands.OfflineCommand.ModifyType;
import org.bukkit.command.CommandSender;

public class TMCommand extends BaseCommand {

    public TMCommand(final TokenManagerPlugin plugin) {
        super(plugin, "tm", Permissions.CMD_ADMIN, false);
        child(
            new OfflineCommand(plugin, ModifyType.ADD, "give"),
            new OfflineCommand(plugin, ModifyType.REMOVE, "delete", "take"),
            new OfflineCommand(plugin, ModifyType.SET),
            new GiveAllCommand(plugin),
            new OpenCommand(plugin),
            new TransferCommand(plugin),
            new ReloadCommand(plugin)
        );
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        sendMessage(sender, true, "COMMAND.tokenmanager.usage");
    }
}
