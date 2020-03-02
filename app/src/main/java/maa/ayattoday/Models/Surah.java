package maa.ayattoday.Models;

public class Surah
{
    private String number;

    private String englishName;

    private String numberOfAyahs;

    private String revelationType;

    private String name;

    private String englishNameTranslation;

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public String getEnglishName ()
    {
        return englishName;
    }

    public void setEnglishName (String englishName)
    {
        this.englishName = englishName;
    }

    public String getNumberOfAyahs ()
    {
        return numberOfAyahs;
    }

    public void setNumberOfAyahs (String numberOfAyahs)
    {
        this.numberOfAyahs = numberOfAyahs;
    }

    public String getRevelationType ()
    {
        return revelationType;
    }

    public void setRevelationType (String revelationType)
    {
        this.revelationType = revelationType;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEnglishNameTranslation ()
    {
        return englishNameTranslation;
    }

    public void setEnglishNameTranslation (String englishNameTranslation)
    {
        this.englishNameTranslation = englishNameTranslation;
    }

}
