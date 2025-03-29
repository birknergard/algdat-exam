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
    double population;
    double id;

    public City(String csvEntry){
        // Parse city entries into the data structure
        String[] entry = csvEntry.split("\",\"");

        this.city = verify(entry[1]) ? entry[1] : "Empty";
        this.lat = verify(entry[2]) ? Float.parseFloat(entry[2]) : 0;
        this.lng = verify(entry[2]) ? Double.parseDouble(entry[3]) : 0;
        this.country = verify(entry[2]) ? entry[4] : "";
        this.iso2 = verify(entry[2]) ? entry[5] : "";
        this.iso3 = verify(entry[2]) ? entry[6] : "";
        this.adminName = verify(entry[2]) ? entry[7] : "";
        this.capital = verify(entry[2]) ? entry[8] : "";
        this.population = verify(entry[9]) ? Double.parseDouble(entry[9]) : 0;
        this.id = verify(entry[10]) ? Double.parseDouble(entry[10]) : 0;

    }

    // If string length is not longer than 2 (string == "") returns false, otherwise true
    private boolean verify(String input){
        return input.length() >= 2;
    }

    // For now we only have latitude, can write getters for other attributes later.
    public float getLat() {
        return this.lat;
    }
}

