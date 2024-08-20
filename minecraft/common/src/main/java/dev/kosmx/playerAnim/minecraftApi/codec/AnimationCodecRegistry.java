package dev.kosmx.playerAnim.minecraftApi.codec;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnimationCodecRegistry {
    public static final AnimationCodecRegistry INSTANCE = new AnimationCodecRegistry();
    private AnimationCodecRegistry() {}

    private final List<AnimationCodec<?>> codecs = new ArrayList<>();

    public void registerCodec(String extension, AnimationCodec<?> codec) {
        codecs.add(codec);
    }

    /**
     * Return decoder(s) for the given file extension.
     * An extension might have more decoders, like .json might be emotecraft or bedrock
     * @param fileExtension extension without the dot (.) like <code>json</code>
     * @return stream, the stream might be empty.
     */
    @NotNull
    public List<AnimationCodec<?>> getCodec(@NotNull String fileExtension) {
        return codecs.stream().filter(it -> Objects.equals(it.getExtension(), fileExtension)).toList();
    }


    public static @Nullable String getExtension(@NotNull String filename) {
        if (filename.isEmpty()) return null;

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            filename = filename.substring(i + 1);
        }
        return filename;
    }

}
