package donkey.bora.com.network;

import donkey.bora.com.model.EmailAuthSendVO;
import donkey.bora.com.model.EmailCheckVO;
import donkey.bora.com.model.PinCodeCheckVO;
import donkey.bora.com.model.PreCheckVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRequest {

    //String BASE_URL = "http://10.0.2.2:8000";
    String BASE_URL = "http://211.201.190.29:8080";

    @GET("pre-check")
    Call<JsonResponse<PreCheckVO>> preCheck();

    @GET("email-check")
    Call<JsonResponse<EmailCheckVO>> emailValidateCheck(@Query("email") String email);

    @GET("gen-auth-key")
    Call<JsonResponse<EmailAuthSendVO>> emailSend(@Query("email") String email);

    @GET("confirm-auth-key")
    Call<JsonResponse<PinCodeCheckVO>> pinCodeCheck(@Query("email") String email,
                                                    @Query("auth_code") String pinCode);
}
