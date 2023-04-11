package kleber.fort.implementandoaclassificaodocampeonato.data;

import java.util.List;

import kleber.fort.implementandoaclassificaodocampeonato.model.ClassificaoTimes;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TimesInglesApi {

    @GET("lista-ingles-a-2022-2023.json")
    Call<List<ClassificaoTimes>> getTimesIngles();
}
