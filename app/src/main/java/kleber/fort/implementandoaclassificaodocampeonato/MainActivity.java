package kleber.fort.implementandoaclassificaodocampeonato;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import kleber.fort.implementandoaclassificaodocampeonato.adapter.TimesInglesAdapter;
import kleber.fort.implementandoaclassificaodocampeonato.data.TimesInglesApi;
import kleber.fort.implementandoaclassificaodocampeonato.databinding.ActivityMainBinding;
import kleber.fort.implementandoaclassificaodocampeonato.model.ClassificaoTimes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TimesInglesApi timesInglesApi;
    private TimesInglesAdapter timesInglesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupListaTimes();



    }



    private void setupHttpClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/kleberfort/dados-jogos-partidas-oficial-2022-api/master/ingles-a-2022-23/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        timesInglesApi = retrofit.create(TimesInglesApi.class);


    }

    private void setupListaTimes() {

        binding.rvClassificaoIngles.setHasFixedSize(true);
        binding.rvClassificaoIngles.setLayoutManager(new LinearLayoutManager(this));
        binding.rvClassificaoIngles.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        timesInglesApi.getTimesIngles().enqueue(new Callback<List<ClassificaoTimes>>() {
            @Override
            public void onResponse(Call<List<ClassificaoTimes>> call, Response<List<ClassificaoTimes>> response) {
                if(response.isSuccessful()){
                    List<ClassificaoTimes> list = response.body();
                    timesInglesAdapter = new TimesInglesAdapter(list);
                    binding.rvClassificaoIngles.setAdapter(timesInglesAdapter);


                }else{
                    errorBuscarDados();
                }
            }

            @Override
            public void onFailure(Call<List<ClassificaoTimes>> call, Throwable t) {
                errorBuscarDados();
            }
        });


    }//fim do método setup


    private void errorBuscarDados() {
        Snackbar.make(binding.getRoot(), "erro ao buscar dados da API, Verifique a conexão de Internet, ", Snackbar.LENGTH_LONG).show();
    }





}