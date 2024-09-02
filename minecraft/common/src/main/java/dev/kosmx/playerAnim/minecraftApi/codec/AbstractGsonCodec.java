package dev.kosmx.playerAnim.minecraftApi.codec;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dev.kosmx.playerAnim.api.IPlayable;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class AbstractGsonCodec<T extends IPlayable> implements AnimationCodec<T> {

    protected abstract Gson getGson();

    protected abstract Type getListedTypeToken();

    @Override
    public @NotNull Collection<T> decode(@NotNull InputStream buffer) throws IOException {
        var gson = getGson();
        try {
            // type safety is off!
            return gson.fromJson(new InputStreamReader(buffer), getListedTypeToken());
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        }
    }

    @Override
    public @NotNull String getExtension() {
        return "json";
    }
}
