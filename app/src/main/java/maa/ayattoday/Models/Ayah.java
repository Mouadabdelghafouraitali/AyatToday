package maa.ayattoday.Models;

public class Ayah
{
    private String code;

    private Data[] data;

    private String status;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

}
