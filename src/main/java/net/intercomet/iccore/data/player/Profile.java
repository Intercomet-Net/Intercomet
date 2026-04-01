package net.intercomet.iccore.data.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Profile {
    private final UUID uuid;
    private final Settings settings;
    private final Map<String, Stat<?>> stats;

    public Profile(UUID uuid) {
        this.uuid = uuid;
        this.settings = new Settings();
        this.stats = new HashMap<>();
    }

    public UUID getUniqueId() {
        return this.uuid;
    }
    public Settings getSettings() {
        return this.settings;
    }

    public Map<String, Stat<?>> getStats() {
        return this.stats;
    }

    public void setStats(String key, Stat<?> stat) {
        this.stats.put(key, stat);
    }
}
