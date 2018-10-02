package mkgosisejo.providers.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import mkgosisejo.interfaces.IDataProvider;
import mkgosisejo.models.Hero;
import mkgosisejo.providers.cache.Cache;

public class WebRequest implements IDataProvider {

    public WebRequest() {
        
    }

    public boolean insertHero(Hero hero) {
        return false;
    }

    public List<Hero> getHeros() {
        return (new File(executeRequest(Cache.Config.WEB_REQUEST_URL)).getHeros());
    }

    public boolean updateHero(Hero hero) {
        return false;
    }

	public boolean deleteHero(Hero hero) {
		return false;
    }
    
    private String executeRequest(String urlTarget){
        try {
            URL url = new URL(urlTarget);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String res = "";

            while ((line = bufferedReader.readLine()) != null){
                res += line + "\n";
            }
            bufferedReader.close();
            return (res);
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        return (null);
    }
}