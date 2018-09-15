package mkgosisejo.enums;

import mkgosisejo.providers.cache.Cache;

public enum DisplayMode {
    CONSOLE,
    GUI;

    public static DisplayMode getEnumValue(String value){
        try {
            return (DisplayMode.valueOf(value.toUpperCase()));
        } catch (Exception e) {}
        return null;
    }

    public static void SwitchDisplay(){
        if (Cache.Args.DISPLAY_MODE == DisplayMode.GUI){
            Cache.Args.DISPLAY_MODE = DisplayMode.CONSOLE;
        }
        else if (Cache.Args.DISPLAY_MODE == DisplayMode.CONSOLE){
            Cache.Args.DISPLAY_MODE = DisplayMode.GUI;
        }
    }
}