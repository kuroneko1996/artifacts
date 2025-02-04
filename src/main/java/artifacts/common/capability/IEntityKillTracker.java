package artifacts.common.capability;

import net.minecraft.entity.EntityType;

import java.util.Collection;

public interface IEntityKillTracker {

    int getMaxSize();

    void clear();

    void addEntityType(EntityType<?> type);

    Collection<EntityType<?>> getEntityTypes();

    default double getKillRatio(EntityType<?> type) {
        return getEntityTypes().stream().filter(type::equals).count() / (double) getMaxSize();
    }
}
