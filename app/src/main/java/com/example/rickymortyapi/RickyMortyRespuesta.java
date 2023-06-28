package com.example.rickymortyapi;

import java.util.ArrayList;
import java.util.List;

public class RickyMortyRespuesta {
    private List<RickyMorty> results = new ArrayList<RickyMorty>();

    public List<RickyMorty> getResults() {
        return results;
    }

    public void setResults(List<RickyMorty> results) {
        this.results = results;
    }
}
