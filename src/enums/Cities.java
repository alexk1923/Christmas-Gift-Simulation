package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;

public enum Cities {

    @JsonProperty("Bucuresti")
    BUCURESTI("Bucuresti"),

    @JsonProperty("Constanta")
    CONSTANTA("Constanta"),

    @JsonProperty("Buzau")
    BUZAU("Buzau"),

    @JsonProperty("Timisoara")
    TIMISOARA("Timisoara"),

    @JsonProperty("Cluj-Napoca")
    CLUJ("Cluj-Napoca"),

    @JsonProperty("Iasi")
    IASI("Iasi"),

    @JsonProperty("Craiova")
    CRAIOVA("Craiova"),

    @JsonProperty("Brasov")
    BRASOV("Brasov"),

    @JsonProperty("Braila")
    BRAILA("Braila"),

    @JsonProperty("Oradea")
    ORADEA("Oradea");

    private String value;

    Cities(final String value) {
        this.value = value;
    }

    public double getNiceScore(final HashMap<Cities, ArrayList<Double>> citiesHashMap) {
        // daca orasul curent nu se afla in hashmap
        if (!citiesHashMap.containsKey(this)) {
            return 0;
        }
        double sum = 0;

        for (double d : citiesHashMap.get(this)) {
            sum += d;
        }
        return sum / citiesHashMap.get(this).size();
    }
}
