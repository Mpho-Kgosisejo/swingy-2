package mkgosisejo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileHandler {
    public static String ReadFile(String filepath){
        File file = null;
        BufferedReader bufferedReader = null;
        String data = null;
        String line;

        try {
            file = new File(filepath);
            bufferedReader = new BufferedReader(new FileReader(file));
            data = "";

            while ((line = bufferedReader.readLine()) != null){
                data += line + "\n";
            }
        } catch (Exception e) {}
        return (data);
    }

    public static boolean WriteFile(String filename, String data, boolean append){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter(filename, append);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);

            printWriter.print(data);
            printWriter.close();
        } catch (Exception e) {}
        return (false);
    }

    public static JSONObject ReadJSON_File(String filepath){
        JSONObject jsonObject = null;

        try {
            Object object = new JSONParser().parse(new FileReader(filepath));
            jsonObject = (JSONObject)object;

            return (jsonObject);
        } catch (Exception e) {}
        return (jsonObject);
    }

    public static boolean WriteJSON_File(String filename, JSONObject jsonObject){
        try {
            PrintWriter printWriter = new PrintWriter(filename);
            printWriter.write(jsonObject.toJSONString());

            printWriter.flush();
            printWriter.close();

            return (true);
        } catch (Exception e) {}
        return (false);
    }

    /**
     * This function will not work with LinkedMaps!
     * 
     * Author: Mpho Kgosisejo
     * 
     * @param filename file to write data in.
     * @param data will be written into "filename". usage => key:value (eg.) => firstName:Mpho \n lastName: Kgosisejo
     * @return Returns "true" of successful else "false".
     */
    public static boolean WriteJSON_File(String filename, String data){
        JSONObject jsonObject = new JSONObject();;
        String[] dataArray = data.trim().split("\n");
        String[] lineArray = null;
        String key = null;
        String value = null;

        for (String str: dataArray) {
            lineArray = str.split(":");

            // Check if lineArray has "key" and "value" by checking: "length == 2"
            // If not, ignore...
            if (lineArray.length == 2){
                if(((key = lineArray[0].trim()).length() > 0) && ((value = lineArray[1].trim()).length() > 0)){
                    jsonObject.put(key, value);
                }
            }
        }
        return (WriteJSON_File(filename, jsonObject));
    }

    public static String GetJSONObjectValue(JSONObject jsonObject, String key){
        try {
            return (jsonObject.get(key).toString());
        } catch (Exception e) {}
        return (null);
    }
}