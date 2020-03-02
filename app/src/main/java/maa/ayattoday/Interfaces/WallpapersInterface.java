package maa.ayattoday.Interfaces;

import maa.ayattoday.Models.WallpapersIslamic;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WallpapersInterface {

    @GET("get.php?auth=367983ad221a6fd926c7b31a062681b9&method=search&term=mosque")
    public Call<WallpapersIslamic> getWallpapers();
}
