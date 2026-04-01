<<<<<<< HEAD
package net.intercomet.iccore.data.player;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final Map<UUID, Profile> profiles = new HashMap<>();
    private final Intercomet plugin;

    public ProfileManager(Intercomet plugin) {
        this.plugin = plugin;
    }

    public Profile getProfile(UUID uuid) {
        return profiles.computeIfAbsent(uuid, Profile::new);
    }

    public void removeProfile(UUID uuid) {
        profiles.remove(uuid);
    }
}
=======
package net.intercomet.iccore.data.player;

import net.intercomet.iccore.Intercomet;
import net.intercomet.iccore.data.db.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProfileManager {
    private final Map<UUID, Profile> profiles = new HashMap<>();
    private final Intercomet plugin;

    public ProfileManager(Intercomet plugin) {
        this.plugin = plugin;
    }

    public Profile getProfile(UUID uuid) {
        return profiles.computeIfAbsent(uuid, Profile::new);
    }

    public void removeProfile(UUID uuid) {
        profiles.remove(uuid);
    }
}
>>>>>>> 6c8a590 (mmm)
