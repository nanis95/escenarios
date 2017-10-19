package com.example.diana.escenarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.diana.escenarios.SitiosService.SitiosService;
import com.example.diana.escenarios.models.Escenarios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    public static final String TAG = "DATOSCOLOMBIA";

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    List<Escenarios> listDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder().baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        listDatos = new ArrayList<Escenarios>();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        recycler.setLayoutManager(new LinearLayoutManager(this));

        obtenerDatos();
    }

    public void obtenerDatos() {
        SitiosService service = retrofit.create(SitiosService.class);
        Call<List<Escenarios>> sitioRespuestaCall = service.obtenerListadeSitios();
        sitioRespuestaCall.enqueue(new Callback<List<Escenarios>>() {
            @Override
            public void onResponse(Call<List<Escenarios>> call, Response<List<Escenarios>> response) {
                if (response.isSuccessful()){
                    List lista = response.body();
                    for (int i=0; i<lista.size(); i++){
                        Escenarios m = (Escenarios) lista.get(i);

                        // Inicializar Escenarios
                        listDatos.add(m);

                        // Crear un nuevo adaptador
                        adapter = new EscAdapter(listDatos);
                        recycler.setAdapter(adapter);
                    }
                }
                else{
                    Log.e(TAG," onResponse: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<Escenarios>> call, Throwable t) {
                Log.e(TAG," onFailure: " + t.getMessage());
            }
        });
    }
}
