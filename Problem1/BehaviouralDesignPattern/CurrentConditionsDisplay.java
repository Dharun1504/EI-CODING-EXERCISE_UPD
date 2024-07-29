// Use Case: Weather Monitoring System

// In this system, multiple display devices (observers) need to update their readings whenever the weather data (subject) changes.


// Subject interface

import java.util.ArrayList;
import java.util.List;

interface WeatherStation {
    void addObserver(Display display);
    void removeObserver(Display display);
    void notifyObservers();
}

// Observer interface
interface Display {
    void update(float temperature, float humidity, float pressure);
}

// Concrete subject
class WeatherData implements WeatherStation {
    private List<Display> displays;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        displays = new ArrayList<>();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    @Override
    public void addObserver(Display display) {
        displays.add(display);
    }

    @Override
    public void removeObserver(Display display) {
        displays.remove(display);
    }

    @Override
    public void notifyObservers() {
        for (Display display : displays) {
            display.update(temperature, humidity, pressure);
        }
    }
}

// Concrete observer
class CurrentConditionsDisplay implements Display {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        System.out.println("Current conditions: " + temperature + "C degrees and " + humidity + "% humidity");
    }
}
