package dev.kosmx.playerAnim.api;

import dev.kosmx.playerAnim.api.layered.IAnimation;

/**
 * Animation that can be stored in animation registry.
 * It has a function to create a player
 */
@FunctionalInterface
public interface IPlayable {
    IAnimation playAnimation();
}
