package donkey.bora.com.network;

import java.util.List;

import donkey.bora.com.model.Hello;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IRequest {

    String BASE_URL = "http://10.0.2.2:8000";

    @GET("hello")
    Call<Hello> hello();

    @GET("pre-check")
    @Headers({ "access_token: 1111111" })
    Call<List<Object>> preCheck();

    @GET("intro")
    Call<List<Object>> intro();

    /*@GET("users/{user}/repos")
    Call<List<Object>> temp(@Path("user") String user);*/
}
