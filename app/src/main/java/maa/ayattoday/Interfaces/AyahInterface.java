package maa.ayattoday.Interfaces;

import maa.ayattoday.Models.Ayah;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AyahInterface {

    @GET("quran-uthmani,en.asad")
    public Call<Ayah> getAyahInformation();
}
