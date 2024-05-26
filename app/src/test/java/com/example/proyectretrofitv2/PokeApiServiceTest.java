package com.example.proyectretrofitv2;

import static strikt.api.ExpectKt.expectThat;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeApiServiceTest {
    @Test
    public void testGetPokemon() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService service = retrofit.create(PokeApiService.class);

        Call<Pokemon> call = service.getPokemon(1);

        Response<Pokemon> response = call.execute();

        expectThat(((Response<?>) response).isSuccessful()).equals(true);
        expectThat(response.body().getName()).equals("bulbasaur");
    }
}