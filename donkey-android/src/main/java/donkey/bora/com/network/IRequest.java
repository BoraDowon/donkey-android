package donkey.bora.com.network;

import donkey.bora.com.model.DepartmentListResponseVO;
import donkey.bora.com.model.EmailAuthSendVO;
import donkey.bora.com.model.EmailCheckVO;
import donkey.bora.com.model.IsExistUserResponseVO;
import donkey.bora.com.model.PinCodeCheckVO;
import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.model.RegisterResponseVO;
import donkey.bora.com.model.RegisterSendInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {

    //String BASE_URL = "http://10.0.2.2:8000";
    String BASE_URL = "http://211.201.190.29:8080";

    @GET("preCheck")
    Call<JsonResponse<PreCheckVO>> preCheck(@Query("token") String token);

    @GET("emailCheck")
    Call<JsonResponse<EmailCheckVO>> emailValidateCheck(@Query("email") String email);

    @GET("genAuthKey")
    Call<JsonResponse<EmailAuthSendVO>> emailSend(@Query("email") String email);

    @GET("isExistUser")
    Call<JsonResponse<IsExistUserResponseVO>> isExistUser(@Query("email") String email,
                                                          @Query("auth_code") String pinCode);

    @GET("confirmAuthKey")
    Call<JsonResponse<PinCodeCheckVO>> pinCodeCheck(@Query("email") String email,
                                                    @Query("auth_code") String pinCode);

    @GET("departments")
    Call<JsonResponse<DepartmentListResponseVO>> getDepartments(@Query("email") String email,
                                                                @Query("auth_code") String pinCode);

    @POST("registration")
    Call<JsonResponse<RegisterResponseVO>> register(@Body RegisterSendInfo info);

}
