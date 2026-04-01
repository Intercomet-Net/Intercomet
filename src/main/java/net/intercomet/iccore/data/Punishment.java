<<<<<<< HEAD
package net.intercomet.iccore.data;

import javax.annotation.Nullable;
import java.util.UUID;

public class Punishment {
    private final UUID punisherUUID;
    private final UUID targetUUID;
    private final String action;
    @Nullable private String reason;
    @Nullable private String duration;
    private final long timestamp;

    public Punishment(UUID punisherUUID, UUID targetUUID, String action, String reason, String duration) {
        this.punisherUUID = punisherUUID;
        this.targetUUID = targetUUID;
        this.action = action;
        this.reason = reason;
        this.duration = duration;
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getPunisherUUID() {
        return this.punisherUUID;
    }

    public UUID getTargetUUID() {
        return this.targetUUID;
    }

    public String getAction() {
        return this.action;
    }

    public String getReason() {
        return this.reason;
    }

    public String getDuration() {
        return this.duration;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
=======
package net.intercomet.iccore.data;

import javax.annotation.Nullable;
import java.util.UUID;

public class Punishment {
    private final UUID punisherUUID;
    private final UUID targetUUID;
    private final String action;
    @Nullable private String reason;
    @Nullable private String duration;
    private final long timestamp;

    public Punishment(UUID punisherUUID, UUID targetUUID, String action, String reason, String duration) {
        this.punisherUUID = punisherUUID;
        this.targetUUID = targetUUID;
        this.action = action;
        this.reason = reason;
        this.duration = duration;
        this.timestamp = System.currentTimeMillis();
    }

    public UUID getPunisherUUID() {
        return this.punisherUUID;
    }

    public UUID getTargetUUID() {
        return this.targetUUID;
    }

    public String getAction() {
        return this.action;
    }

    public String getReason() {
        return this.reason;
    }

    public String getDuration() {
        return this.duration;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
>>>>>>> 6c8a590 (mmm)
