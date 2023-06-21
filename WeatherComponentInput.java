package eloro;

import com.elo.flows.api.schema.annotations.Property;

public class WeatherComponentInput {

    @Property(displayName = "weatherComponent.input.data.location")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
