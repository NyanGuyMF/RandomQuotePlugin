/*
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *
 * Copyright (C) 2020 Vasiliy Petukhov <void.pointer@ya.ru>
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

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// Lombok constructor generator. You can write a constructor yourself, but I prefer lombok.
@RequiredArgsConstructor
public enum Message implements voidpointer.spigot.framework.localemodule.Message {
    PLUGIN_ENABLED("&ePlugin &b{plugin}&e successfully enabled!"),
    PLUGIN_DISABLED("&ePlugin &b{plugin}&e has been disabled."),
    QUOTE_TO_PLAYER("\\(&e&o{content} &6© {author}) [hover{&eCopy the quote}] [click.copy{{content}}]" +
            " &7(Click to copy!)"),
    QUOTE_TO_CONSOLE("&e&o{content} &6© {author}"),
    ;

    // This is also a Lombok annotation. Obviously you can write the getter yourself.
    @Getter private final String defaultMessage;

    @Override public String getPath() {
        return toString().toLowerCase().replace('_', '-');
    }
}
