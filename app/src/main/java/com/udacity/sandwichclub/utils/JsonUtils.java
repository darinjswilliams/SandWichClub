package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String KEY_NAME = "name";
    private final static String KEY_MAIN_NAME = "mainName";
    private final static String KEY_ALSO_KNOW_AS = "alsoKnownAs";
    private final static String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_IMAGE = "image";
    private final static String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

    try {
        JSONObject sandwichJson = new JSONObject(json);

        //NAME
        JSONObject name = sandwichJson.getJSONObject(KEY_NAME);

        //MAIN NAME
        String mainName = name.getString(KEY_MAIN_NAME);

        //ALSO KNOW AS
        JSONArray alsoKnowAsObject = name.getJSONArray(KEY_ALSO_KNOW_AS);
        List<String> alsoKnowAs = getStrings(alsoKnowAsObject);

        //PLACE OF ORGIN
        String placeOfOrigin = sandwichJson.getString(KEY_PLACE_OF_ORIGIN);

        //DESCRIPTION
        String description = sandwichJson.getString(KEY_DESCRIPTION);

        //IMAGE URL
        String imageUrl = sandwichJson.getString(KEY_IMAGE);



        //INGREDIENTS
        JSONArray ingredientsJsonObject = sandwichJson.getJSONArray(KEY_INGREDIENTS);
        List<String> ingredients = getStrings(ingredientsJsonObject);


        Sandwich sandwich = new Sandwich(mainName, alsoKnowAs, placeOfOrigin, description, imageUrl, ingredients);

        return  sandwich;

    } catch(JSONException jse){
        jse.printStackTrace();

        return null;
    }

    }

    private static List<String> getStrings(JSONArray jsonArray) throws JSONException {
        List<String> dataOfString = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++){
            dataOfString.add(jsonArray.getString(i));
        }
        return  dataOfString;
    }
}
