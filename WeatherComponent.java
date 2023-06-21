package eloro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import com.elo.flows.api.components.annotations.Component;
import com.elo.flows.api.components.annotations.Service;

@Component(version = "1.00.000", namespace = "eloro", name = "weatherComponent", displayName = "component.weatherComponent.display.name")

public class WeatherComponent {

    @Service(displayName = "com.elo.flows.weatherComponent.service")
    public WeatherComponentOutput weatherComponentService(WeatherComponentInput input) { // ce e asta?
        WeatherComponentOutput result = new WeatherComponentOutput();
        

        try {
            
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + input.getLocation() + "&units=metric&appid=895284fb2d2c50a520ea537456963d9c";
            System.out.println(urlString);
            URL url = new URL(urlString); // in loc de Bucharest trb sa fie ${input.getLocalization()}
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode(); // asta verifica daca conectarea la API a avut succes
            if (responseCode != 200) { // daca codul este diferit de 200 inseamna ca, conectarea a avut succes.
                throw new RuntimeException("HttpResponseCode: " + responseCode); // daca conectarea la API nu a avut succes primesc mesajul cu codul de eroare
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject myResponse = new JSONObject(response.toString());
                JSONArray weatherArray = myResponse.getJSONArray("weather"); // asta e array si se ia info-ul asa. (1)
                JSONObject insideWeather = weatherArray.getJSONObject(0); // asta e array si se ia info-ul asa. (2)
                JSONObject mainArray = myResponse.getJSONObject("main");

                result.setWeatherFeed(("The weather in " + myResponse.getString("name") + " is: " + mainArray.getInt("temp") + "*C. Weather description: " + insideWeather.getString("description") + ".")) ;

            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
