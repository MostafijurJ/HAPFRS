package com.example.mr_kajol.finalproject;

public class Profile {

    String BengaliName, url, EnglishName;
    Double Fat, Carbohydrate, Protein;

    Profile(){

    }

    public Profile(String bengaliName, String url, String englishName, Double fat, Double carbohydrate, Double protein) {
        BengaliName = bengaliName;
        this.url = url;
        EnglishName = englishName;
        Fat = fat;
        Carbohydrate = carbohydrate;
        Protein = protein;
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