package net.intercomet.iccore.data;

import java.util.UUID;

public class PunishContext {
    private final UUID targetUUID;
    private final String action;
    private String duration;
    private String reason;

    public PunishContext(UUID targetUUID, String action, String reason, String duration) {
        this.targetUUID = targetUUID;
        this.action = action;
        this.reason = reason;
        this.duration = duration;
    }

    public UUID getTargetUUID() {
        return targetUUID;
    }

    public String getAction() {
        return action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
