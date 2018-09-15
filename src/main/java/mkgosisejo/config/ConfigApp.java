package mkgosisejo.config;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import mkgosisejo.providers.DataProvider;
import mkgosisejo.providers.cache.Cache;
import mkgosisejo.enums.DataProviderType;
import mkgosisejo.enums.DisplayMode;
import mkgosisejo.utils.FileHandler;

public class ConfigApp {
    public static String ResourcesPath = "resources/";
    private String config_file_name = "Config.json";

    public ConfigApp(String[] args){
        // Silence "org.hibernate.Validator" logs
        Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        this.processCacheArgs(args);
        this.processCacheConfig();
        this.processCacheData();
    }

    private void processCacheArgs(String[] args){
        // Check args if there's "Console" or "GUI"

        for (String arg: args) {
            if (arg.equalsIgnoreCase("console")){
                Cache.Args.DISPLAY_MODE = DisplayMode.CONSOLE;
            }
            else if (arg.equalsIgnoreCase("gui")){
                Cache.Args.DISPLAY_MODE = DisplayMode.GUI;
            }
        }
    }

    private void processCacheConfig(){
        // Check for the "Config.json"

        this.buildConfigFile();

        JSONObject jsonObject = FileHandler.ReadJSON_File(GetPath(this.config_file_name));

        if (jsonObject != null){
            Cache.Config.IS_DEVELOPMENT = Boolean.parseBoolean(FileHandler.GetJSONObjectValue(jsonObject, "IS_DEVELOPMENT"));
            Cache.Config.DATA_PROVIDER_TYPE = (DataProviderType.getEnumValue(FileHandler.GetJSONObjectValue(jsonObject, "DATA_PROVIDER_TYPE")) != null) ? DataProviderType.getEnumValue(FileHandler.GetJSONObjectValue(jsonObject, "DATA_PROVIDER_TYPE").toString()) : Cache.Config.DATA_PROVIDER_TYPE;
            Cache.Config.FILE_SOURCE_NAME = (FileHandler.GetJSONObjectValue(jsonObject, "FILE_SOURCE_NAME") != null) ? FileHandler.GetJSONObjectValue(jsonObject, "FILE_SOURCE_NAME") : Cache.Config.FILE_SOURCE_NAME;
            Cache.Config.DATABASE_SOURCE_NAME = (FileHandler.GetJSONObjectValue(jsonObject, "DATABASE_SOURCE_NAME") != null) ? FileHandler.GetJSONObjectValue(jsonObject, "DATABASE_SOURCE_NAME") : Cache.Config.DATABASE_SOURCE_NAME;
            Cache.Config.DEFAULT_HERO_IMAGE = (FileHandler.GetJSONObjectValue(jsonObject, "DEFAULT_HERO_IMAGE") != null) ? FileHandler.GetJSONObjectValue(jsonObject, "DEFAULT_HERO_IMAGE") : Cache.Config.DEFAULT_HERO_IMAGE;
            Cache.Config.DEFAULT_ENEMY_IMAGE = (FileHandler.GetJSONObjectValue(jsonObject, "DEFAULT_ENEMY_IMAGE") != null) ? FileHandler.GetJSONObjectValue(jsonObject, "DEFAULT_ENEMY_IMAGE") : Cache.Config.DEFAULT_ENEMY_IMAGE;
        }
    }

    private void processCacheData(){
        Cache.Data.heroList = new DataProvider().getHeros();
    }

    private void buildConfigFile(){
        File file = new File(ResourcesPath);
        if (file.exists() == false){
            file.mkdirs();
        }

        if ((new File(GetPath(this.config_file_name)).exists()) == false){
            String config = "";
            config += "IS_DEVELOPMENT: " + Cache.Config.IS_DEVELOPMENT + "\n";
            config += "DATA_PROVIDER_TYPE: " + Cache.Config.DATA_PROVIDER_TYPE + "\n";
            config += "FILE_SOURCE_NAME: " + Cache.Config.FILE_SOURCE_NAME + "\n";
            config += "DATABASE_SOURCE_NAME: " + Cache.Config.DATABASE_SOURCE_NAME + "\n";
            config += "DEFAULT_HERO_IMAGE: " + Cache.Config.DEFAULT_HERO_IMAGE + "\n";
            config += "DEFAULT_ENEMY_IMAGE: " + Cache.Config.DEFAULT_ENEMY_IMAGE;
            
            FileHandler.WriteJSON_File(GetPath(this.config_file_name), config);
        }
    }

    public static String GetPath(String file){
        return (ResourcesPath + file);
    }
}