package com.example.mr_kajol.finalproject;

public class Profile {

    String BengaliName, url, EnglishName;
    double Fat, Carbohydrate, Protein;

    Profile(){
    }

    public Profile(String bengaliName, String url, String englishName, double fat, double carbohydrate, double protein) {
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

    public double getFat() {
        return Fat;
    }

    public void setFat(double fat) {
        Fat = fat;
    }

    public double getCarbohydrate() {
        return Carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        Carbohydrate = carbohydrate;
    }

    public double getProtein() {
        return Protein;
    }

    public void setProtein(double protein) {
        Protein = protein;
    }
}