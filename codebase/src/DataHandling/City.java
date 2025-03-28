package DataHandling;

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

    public City(String CSVEntry){
        // Parse city entries into the data structure
    }

    // For now we only have latitude, can write getters for other attributes later.
    public float getLat() {
        return lat;
    }
}

