package com.example.mr_kajol.finalproject;

public class Model {

     String BengaliName,url, EnglishName;
     Double Fat, Carbohydrate,Protein;

    //constructor
    public Model(){


    }

    public String getBengaliName() {
        return BengaliName;
    }

    public void setBengaliName(String bengaliName) {
        BengaliName = bengaliName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public Double getFat() {
        return Fat;
    }

    public void setFat(Double fat) {
        Fat = fat;
    }

    public Double getCarbohydrate() {
        return Carbohydrate;
    }

    public void setCarbohydrate(Double carbohydrate) {
        Carbohydrate = carbohydrate;
    }

    public Double getProtein() {
        return Protein;
    }

    public void setProtein(Double protein) {
        Protein = protein;
    }
}
