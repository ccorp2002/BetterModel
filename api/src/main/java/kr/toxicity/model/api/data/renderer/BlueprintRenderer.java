package kr.toxicity.model.api.data.renderer;

import kr.toxicity.model.api.data.blueprint.BlueprintAnimation;
import kr.toxicity.model.api.data.blueprint.ModelBlueprint;
import kr.toxicity.model.api.tracker.EntityTracker;
import kr.toxicity.model.api.tracker.VoidTracker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public final class BlueprintRenderer {
    @Getter
    private final ModelBlueprint parent;
    private final Map<String, RendererGroup> rendererGroupMap;
    private final Map<String, BlueprintAnimation> animationMap;

    public @NotNull EntityTracker create(@NotNull Entity entity) {
        var tracker = EntityTracker.tracker(entity.getUniqueId());
        return tracker != null ? tracker : new EntityTracker(
                entity,
                instance(entity.getLocation())
        );
    }

    public @NotNull VoidTracker create(@NotNull UUID uuid,  @NotNull Location location) {
        return new VoidTracker(uuid, instance(location), location);
    }

    private @NotNull RenderInstance instance(@NotNull Location location) {
        return new RenderInstance(this, rendererGroupMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().create(location))), animationMap);
    }
}
