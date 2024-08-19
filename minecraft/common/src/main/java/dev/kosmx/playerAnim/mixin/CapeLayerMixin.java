package dev.kosmx.playerAnim.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import dev.kosmx.playerAnim.api.TransformType;
import dev.kosmx.playerAnim.core.util.MathHelper;
import dev.kosmx.playerAnim.core.util.Vec3f;
import dev.kosmx.playerAnim.impl.IAnimatedPlayer;
import dev.kosmx.playerAnim.impl.animation.AnimationApplier;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
public class CapeLayerMixin {
    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/player/AbstractClientPlayer;FFFFFF)V", at = @At("HEAD"))
    private void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        AnimationApplier emote = ((IAnimatedPlayer) abstractClientPlayer).playerAnimator_getAnimation();
        Vec3f pos = emote.get3DTransform("torso", TransformType.POSITION, Vec3f.ZERO);
        poseStack.translate(pos.getX(), pos.getY(), pos.getZ());
        Vec3f rot = emote.get3DTransform("torso", TransformType.ROTATION, Vec3f.ZERO);
        poseStack.mulPose(Vector3f.ZP.rotation(rot.getZ()));    //roll
        poseStack.mulPose(Vector3f.YP.rotation(rot.getY()));    //pitch
        poseStack.mulPose(Vector3f.XP.rotation(rot.getX()));    //yaw
    }
}
