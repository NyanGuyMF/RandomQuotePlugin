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

package voidpointer.example.localemodule.quote;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@RequiredArgsConstructor
public final class OnlineQuoteFetcher implements QuoteFetcher {
    public static final URL API_URL;
    private static final Gson gson = new Gson();

    static {
        URL apiUrl;
        try {
             apiUrl = new URL("https://api.quotable.io/random");
        } catch (MalformedURLException e) {
            apiUrl = null;
            assert false;
        }
        API_URL = apiUrl;
    }

    private final Logger log;

    /** Warning: the method is using blocking I/O! */
    @Override public CompletableFuture<Quote> fetchRandom() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return fetch0();
            } catch (IOException | JsonParseException exception) {
                log.warning("Couldn't fetch a random quote: " + exception.getMessage());
                return null;
            }
        });
    }

    private Quote fetch0() throws IOException, JsonParseException {
        return gson.fromJson(openNewQuoteReader(), JsonQuote.class);
    }

    private Reader openNewQuoteReader() throws IOException {
        return new InputStreamReader(API_URL.openStream());
    }
}
