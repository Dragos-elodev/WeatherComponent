package eloro;

import com.elo.flows.api.schema.annotations.Property;

public class WeatherComponentOutput {
    
    @Property(displayName = "weatherComponent.output.data.text")
    private String weatherFeed;

    public String getWeatherFeed() {
        return weatherFeed;
    }

    public void setWeatherFeed(String weatherFeed) {
        this.weatherFeed = weatherFeed;
    }
}
