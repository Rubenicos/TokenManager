package me.realized.tokenmanager.util.profile;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

final class UUIDFetcher {

    private static final String PROFILE_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final Gson GSON = new Gson();
    private static final Cache<String, UUID> NAME_TO_UUID = CacheBuilder.newBuilder()
        .concurrencyLevel(4)
        .maximumSize(1000)
        .expireAfterWrite(30, TimeUnit.MINUTES)
        .build();

    private UUIDFetcher() {}

    static String getUUID(final String name) throws Exception {
        final UUID cached = NAME_TO_UUID.getIfPresent(name);

        if (cached != null) {
            return cached.toString();
        }

        String content;
        try (InputStream in = new URL(PROFILE_URL + name).openStream(); BufferedInputStream buff = new BufferedInputStream(in)) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len;
            while ((len = buff.read(buf)) > 0) {
                stream.write(buf, 0, len);
            }
            content = new String(stream.toByteArray(), StandardCharsets.UTF_8);
        }

        if (!content.isEmpty()) {
            JsonObject object = GSON.fromJson(content, JsonObject.class);
            if (object.has("name") && object.has("id")) {
                final UUID uuid;
                NAME_TO_UUID.put(object.get("name").toString(), uuid = get(object.get("id").getAsString()));
                return uuid.toString();
            }
        }
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString();
    }

    private static UUID get(String id) {
        return UUID.fromString(
            id.substring(0, 8) + "-" + id.substring(8, 12) + "-" + id.substring(12, 16) + "-" + id.substring(16, 20) + "-" + id.substring(20, 32));
    }
}
