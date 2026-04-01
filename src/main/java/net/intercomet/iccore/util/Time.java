<<<<<<< HEAD
package net.intercomet.iccore.util;

public class Time {
    public static String formatTime(long lastSeen) {
        long diff = System.currentTimeMillis() - lastSeen;

        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) return days + "d";
        if (hours > 0) return hours + "h";
        if (minutes > 0) return minutes + "m";
        return seconds + "s";
    }
}
=======
package net.intercomet.iccore.util;

public class Time {
    public static String formatTime(long lastSeen) {
        long diff = System.currentTimeMillis() - lastSeen;

        long seconds = diff / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) return days + "d";
        if (hours > 0) return hours + "h";
        if (minutes > 0) return minutes + "m";
        return seconds + "s";
    }
}
>>>>>>> 6c8a590 (mmm)
