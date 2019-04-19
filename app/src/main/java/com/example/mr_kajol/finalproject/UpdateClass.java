package com.example.mr_kajol.finalproject;

import java.util.HashMap;
import java.util.Map;

public class UpdateClass {
   public String Height, Weight,PAL;

    UpdateClass() {

    }


    public UpdateClass(String Height, String Weight,String PAL) {
        this.Height = Height;
        this.Weight = Weight;
        this.PAL = PAL;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Height", Height);
        result.put("Weight", Weight);
        result.put("PAL", PAL);

        return result;
    }
}
