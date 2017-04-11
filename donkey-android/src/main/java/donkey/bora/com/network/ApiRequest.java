package donkey.bora.com.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import donkey.bora.com.secure.TokenManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    private static ApiRequest instance;

    private Retrofit retrofit;
    private Retrofit retrofitWithToken;

    private ApiRequest() {

        retrofit = new Retrofit.Builder()
                .baseUrl(IRequest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();

        retrofitWithToken = new Retrofit.Builder()
                .baseUrl(IRequest.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .client(getHttpTokenBuilder().build())
                .build();
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private OkHttpClient.Builder getHttpTokenBuilder() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("HTTP_X_AUTH_TOKEN", "BoraToken " + TokenManager.load(TokenManager.getDefaultPath()))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return httpClient;
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

    public <T> T requestWithToken(final Class<T> cls) {
        return retrofitWithToken.create(cls);
    }

}
