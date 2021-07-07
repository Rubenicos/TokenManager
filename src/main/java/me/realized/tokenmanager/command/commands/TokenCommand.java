package me.realized.tokenmanager.command.commands;

import me.realized.tokenmanager.Permissions;
import me.realized.tokenmanager.TokenManagerPlugin;
import me.realized.tokenmanager.command.BaseCommand;
import me.realized.tokenmanager.command.commands.subcommands.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.OptionalLong;

public class TokenCommand extends BaseCommand {

    public TokenCommand(final TokenManagerPlugin plugin) {
        super(plugin, "token", Permissions.CMD_TOKEN, false);
        child(
            new BalanceCommand(plugin),
            new SendCommand(plugin),
            new TopCommand(plugin),
            new ShopCommand(plugin),
            new ShopsCommand(plugin),
            new SellCommand(plugin),
            new WorthCommand(plugin),
            new VersionCommand(plugin)
        );
    }

    @Override
    protected void execute(final CommandSender sender, final String label, final String[] args) {
        final OptionalLong balance;

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            balance = dataManager.get(player);

            if (!balance.isPresent()) {
                sendMessage(player, false, "&cYour data is improperly loaded, please re-log.");
                return;
            }
        } else {
            balance = OptionalLong.empty();
        }

        sendMessage(sender, true, "COMMAND.token.usage", "tokens", balance.orElse(0));
    }
}
