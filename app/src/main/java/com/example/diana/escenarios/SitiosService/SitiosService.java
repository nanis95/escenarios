package com.example.diana.escenarios.SitiosService;

import com.example.diana.escenarios.models.Escenarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by diana on 18/10/17.
 */

public interface SitiosService {
    @GET("53ga-fhf4.json")
    Call<List<Escenarios>> obtenerListadeSitios();
}
