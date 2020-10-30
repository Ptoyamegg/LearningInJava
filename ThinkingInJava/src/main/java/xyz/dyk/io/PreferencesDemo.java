package xyz.dyk.io;

import java.util.prefs.Preferences;

public class PreferencesDemo {
    public static void main(String[] args) throws Exception {
        Preferences preferences = Preferences.userNodeForPackage(PreferencesDemo.class);
        preferences.put("Location", "Oz");
        preferences.put("Footwear", "Ruby Slippers");
        preferences.putInt("Companions", 4);
        preferences.putBoolean("Are there witches?", true);
        int usageCount = preferences.getInt("UsageCount", 0);
        usageCount++;
        preferences.putInt("UsageCount", usageCount);
        for (String key : preferences.keys())
            System.out.println(key + ": " + preferences.get(key, null));
        //  You must always provide a default value:
        System.out.println("How many companions does Dorothy have? " +
                preferences.getInt("Companions", 0));
    }
}
