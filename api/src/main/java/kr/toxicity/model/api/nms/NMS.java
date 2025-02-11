package kr.toxicity.model.api.nms;

import kr.toxicity.model.api.data.blueprint.NamedBoundingBox;
import kr.toxicity.model.api.tracker.EntityTracker;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public interface NMS {
    @NotNull ModelDisplay create(@NotNull Location location);
    @NotNull PlayerChannelHandler inject(@NotNull Player player);
    @NotNull PacketBundler createBundler();
    @NotNull ItemStack tint(@NotNull ItemStack itemStack, boolean toggle);
    void mount(@NotNull EntityTracker tracker, @NotNull PacketBundler bundler);
    @NotNull HitBox createHitBox(@NotNull Entity entity, @NotNull TransformSupplier supplier, @NotNull NamedBoundingBox namedBoundingBox, @NotNull HitBoxListener source);
    @NotNull NMSVersion version();
    @NotNull EntityAdapter adapt(@NotNull LivingEntity entity);
}
