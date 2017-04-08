package donkey.bora.com.http;

import java.util.List;

import donkey.bora.com.Urls;
import donkey.bora.com.model.Hello;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface IRequest {

    @GET(Urls.HELLO)
    Call<Hello> hello();


    @GET(Urls.PRE_CHECK)
    @Headers({ "access_token: 1111111" })
    Call<List<Object>> preCheck();

    @GET(Urls.TEST)
    Call<List<Object>> intro();

    /*@GET("users/{user}/repos")
    Call<List<Object>> temp(@Path("user") String user);*/
}
