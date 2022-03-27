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

package voidpointer.example.localemodule;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.example.localemodule.command.RandomQuoteCommandExecutor;
import voidpointer.example.localemodule.quote.OnlineQuoteFetcher;
import voidpointer.spigot.framework.localemodule.annotation.LocaleAnnotationResolver;
import voidpointer.spigot.framework.localemodule.annotation.PluginLocale;
import voidpointer.spigot.framework.localemodule.config.LocaleFileConfiguration;

public final class RandomQuotePlugin extends JavaPlugin {
    @PluginLocale(defaultMessages=Message.class, searchForInjection=true)
    private static LocaleFileConfiguration locale;

    @Override public void onLoad() {
        LocaleAnnotationResolver.resolve(this);
    }

    @Override public void onEnable() {
        new RandomQuoteCommandExecutor(new OnlineQuoteFetcher(getLogger())).register(this);
        locale.localize(Message.PLUGIN_ENABLED).set("plugin", getName())
                .send(Bukkit.getConsoleSender());
    }

    @Override public void onDisable() {
        locale.localize(Message.PLUGIN_DISABLED).set("plugin", getName())
                .send(Bukkit.getConsoleSender());
    }
}
