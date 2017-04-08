package donkey.bora.com.network;

import java.util.List;

import donkey.bora.com.model.JsonResponse;
import donkey.bora.com.model.PreCheckVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRequest {

    //String BASE_URL = "http://10.0.2.2:8000";
    String BASE_URL = "http://211.201.190.29:8080";

    @GET("hello")
    Call<JsonResponse<Object>> hello();

    @GET("pre-check")
    Call<JsonResponse<PreCheckVO>> preCheck(@Query("token") String token);

    @GET("intro")
    Call<List<Object>> intro();

    /*@GET("users/{user}/repos")
    Call<List<Object>> temp(@Path("user") String user);*/
}
