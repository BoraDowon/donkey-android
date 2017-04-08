package donkey.bora.com.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import donkey.bora.com.Urls;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    private static ApiRequest instance;

    private Retrofit retrofit;

    private ApiRequest() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static ApiRequest getInstance() {
        if (instance == null) {
            synchronized (ApiRequest.class) {
                if (instance == null) {
                    instance = new ApiRequest();
                }
            }
        }
        return instance;
    }

    public <T> T request(final Class<T> cls) {
        return retrofit.create(cls);
    }

}
