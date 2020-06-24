package ar.com.jdodevelopment.data.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MercadoPagoRetrofit {


    private static final int TIMEOUT_SECONDS = 25;

    private static final String BASE_URL = "https://api.mercadopago.com/v1/";

    public static final String PUBLIC_KEY = "TEST-0542a24b-149d-4f6a-9bfa-8f950ae4cdfa";


    public static <S> S create(Class<S> serviceClass) {
        return buildRetrofit().create(serviceClass);
    }

    private static Retrofit buildRetrofit( ) {
        OkHttpClient okHttpClient = buildOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    private static OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }


}
