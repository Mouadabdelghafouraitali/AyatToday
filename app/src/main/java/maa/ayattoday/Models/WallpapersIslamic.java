package maa.ayattoday.Models;

public class WallpapersIslamic
{
    private String total_match;

    private String success;

    private Wallpapers[] wallpapers;

    public String getTotal_match ()
    {
        return total_match;
    }

    public void setTotal_match (String total_match)
    {
        this.total_match = total_match;
    }

    public String getSuccess ()
    {
        return success;
    }

    public void setSuccess (String success)
    {
        this.success = success;
    }

    public Wallpapers[] getWallpapers ()
    {
        return wallpapers;
    }

    public void setWallpapers (Wallpapers[] wallpapers)
    {
        this.wallpapers = wallpapers;
    }

}
