/*
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *
 * Copyright (C) 2022 Vasiliy Petukhov <void.pointer@ya.ru>
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 */

package voidpointer.example.localemodule.command;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.example.localemodule.Message;
import voidpointer.example.localemodule.quote.Quote;
import voidpointer.example.localemodule.quote.QuoteFetcher;
import voidpointer.spigot.framework.localemodule.Locale;

@RequiredArgsConstructor
public final class RandomQuoteCommandExecutor implements CommandExecutor {
    @NonNull private final Locale locale;
    @NonNull private final QuoteFetcher quoteFetcher;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Quote randomQuote = quoteFetcher.fetchRandom();
        locale.localize(getAppropriateMessage(sender))
                .set("content", randomQuote.getContent())
                .set("author", randomQuote.getAuthor())
                .send(sender);
        return true;
    }

    public void register(final JavaPlugin plugin) {
        plugin.getCommand("random-quote").setExecutor(this);
    }

    private Message getAppropriateMessage(final CommandSender sender) {
        if (sender instanceof Player)
            return Message.QUOTE_TO_PLAYER;
        return Message.QUOTE_TO_CONSOLE;
    }
}
