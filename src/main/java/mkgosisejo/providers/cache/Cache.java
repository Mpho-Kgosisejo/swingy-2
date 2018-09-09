package mkgosisejo.providers.cache;

import mkgosisejo.enums.DataProviderType;
import mkgosisejo.enums.DisplayMode;

public class Cache {
    public static class Args {
        public static DisplayMode DISPLAY_MODE = DisplayMode.CONSOLE;
    }

    public static class Config {
        public static boolean IS_DEVELOPMENT = true;
        public static DataProviderType DATA_PROVIDER_TYPE = DataProviderType.FILE;
        public static String FILE_SOURCE_NAME = "data.txt";
        public static String DATABASE_SOURCE_NAME = "data.sql";
        public static String DEFAULT_HERO_IMAGE = "src/main/java/mkgosisejo/images/default-hero-image.png";
        public static String DEFAULT_ENEMY_IMAGE = "src/main/java/mkgosisejo/images/default-enemy-image.png";
    }

    public static class Data {

    }
}