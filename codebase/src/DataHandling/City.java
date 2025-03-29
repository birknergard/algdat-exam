package DataHandling;

import java.util.Arrays;

public class City {
    String city;
    float lat;
    double lng;
    String country;
    String iso2;
    String iso3;
    String adminName;
    String capital;
    long population;
    long id;

    public City(String csvEntry){
        // Parse city entries into the data structure
        String[] entry = csvEntry.split("\",\"");

        this.city = entry[1];
        this.lat = Float.parseFloat(entry[2]);
        this.lng = Double.parseDouble(entry[3]);
        this.country = entry[4];
        this.iso2 = entry[5];
        this.iso3 = entry[6];
        this.adminName = entry[7];
        this.capital = entry[8];
        this.population = Long.parseLong(entry[9]);

        // Removes final " from entry.
    }

    // For now we only have latitude, can write getters for other attributes later.
    public float getLat() {
        return this.lat;
    }
}

