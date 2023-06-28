package com.example.rickymortyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rickymortyapi.api.RickapiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListaRickyMortyAdapter listaRickyMortyAdapter;
    Retrofit retrofit;
    ImageView imageView;
    private static final String TAG = "POKEDEX";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamado del recyclevie
        recyclerView=findViewById(R.id.card_recycler_view);
        listaRickyMortyAdapter=new ListaRickyMortyAdapter(this);
        recyclerView.setAdapter(listaRickyMortyAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ////////////////
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        imageView = findViewById(R.id.imagenGlide);
        setImageView();
        obtenerDatos();
    }

    private void obtenerDatos2() {
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickyMortyRespuesta> rickyMortyRespuestaCall = service.obtenerListaRickyMorty();

        rickyMortyRespuestaCall.enqueue(new Callback<RickyMortyRespuesta>() {
            @Override
            public void onResponse(Call<RickyMortyRespuesta> call, Response<RickyMortyRespuesta> response) {

                if (response.isSuccessful()) { // si la respuesta ha sido generada con exito
                    RickyMortyRespuesta rickyMortyRespuesta = response.body(); // aqui tenemos la informacion obtenenida
                    List<RickyMorty> listaRickyMorty = rickyMortyRespuesta.getResults(); // traemos los resultados y los guardamos en el arraylist generado para tal fin
                    for (int i = 0; i < listaRickyMorty.size(); i++) {
                        RickyMorty p = listaRickyMorty.get(i);
                        Log.e(TAG, " pokemon: " + p.getName());
                    }
                    listaRickyMortyAdapter.add((ArrayList<RickyMorty>) listaRickyMorty);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RickyMortyRespuesta> call, Throwable t) {

                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
    private void setImageView() {
        String url = "https://assets.stickpng.com/images/58f3773fa4fa116215a92413.png";
        Glide.with(this)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }
    public void obtenerDatos() {
        RickapiService service = retrofit.create(RickapiService.class);
        Call<RickyMortyRespuesta> rickyMortyRespuestaCall = service.obtenerListaRickyMorty();
        rickyMortyRespuestaCall.enqueue(new Callback<RickyMortyRespuesta>() {
            @Override
            public void onResponse(Call<RickyMortyRespuesta> call, Response<RickyMortyRespuesta> response) {
                if (response.isSuccessful()) {
                    RickyMortyRespuesta pokemonRespuesta = response.body();
                    List<RickyMorty> listaRickyMorty = pokemonRespuesta.getResults();
                    for (int i = 0; i < listaRickyMorty.size(); i++) {
                        RickyMorty p = listaRickyMorty.get(i);
                        Log.e(TAG, "pokemon: " + p.getName());
                    }

                    listaRickyMortyAdapter.add((ArrayList<RickyMorty>) listaRickyMorty);
                }
                else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<RickyMortyRespuesta> call, Throwable t) {

                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
