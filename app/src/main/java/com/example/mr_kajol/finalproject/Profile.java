package com.example.mr_kajol.finalproject;

public class Profile {
    String BengaliName,url, EnglishName;

    public Profile() {
    }

    public Profile(String bengaliName, String url, String englishName) {
        BengaliName = bengaliName;
        this.url = url;
        EnglishName = englishName;
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
}
