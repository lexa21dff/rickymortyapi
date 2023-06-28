package com.example.rickymortyapi.api;

import com.example.rickymortyapi.RickyMortyRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickapiService {
    @GET("character")
    Call<RickyMortyRespuesta> obtenerListaRickyMorty();
}
