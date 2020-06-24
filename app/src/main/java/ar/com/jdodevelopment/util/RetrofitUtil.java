package ar.com.jdodevelopment.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class RetrofitUtil {




    public static String getErrorMessage(Response<?> response) {
        ResponseBody responseBody = response.errorBody();
        if(responseBody == null)
            return "No hay información disponible.";

        try {
            Gson gson = new Gson();
            JsonObject object = gson.fromJson(responseBody.string(), JsonObject.class);
            return object.get("message").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
            return "No hay información disponible.";
        }
    }


}
