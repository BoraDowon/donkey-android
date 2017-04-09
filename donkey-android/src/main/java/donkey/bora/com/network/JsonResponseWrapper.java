package donkey.bora.com.network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class JsonResponseWrapper<T> implements Callback<JsonResponse<T>> {

    private static final String TAG = "http";
    private static final String OK_200 = "200";

    @Override
    public void onResponse(Call<JsonResponse<T>> call, Response<JsonResponse<T>> response) {
        Log.d(TAG, log(response));

        if (response.body() != null && OK_200.equals(response.body().getCode())) {
            callback(response.body().getData(), true);
        } else {
            callback(null, false);
        }
    }

    @Override
    public void onFailure(Call<JsonResponse<T>> call, Throwable t) {
        Log.e(TAG, "onFailure: " + t.getMessage() + ", " + t.getCause());
        callback(null, false);
    }

    private String log(Response<JsonResponse<T>> response) {
        StringBuilder log = new StringBuilder();
        if (response.raw() != null) {
            log.append(response.raw().toString());
        }
        if (response.body() != null) {
            log.append("\nAPI Result - ");
            if (response.body().getCode() != null) {
                log.append("[code]: ");
                log.append(response.body().getCode());
            }
            if (response.body().getMsg() != null) {
                log.append(", [msg]: ");
                log.append(response.body().getMsg());
            }
            if (response.body().getDetail() != null) {
                log.append(", [detail]: ");
                log.append(response.body().getDetail());
            }
        }
        return log.toString();
    }

    public abstract void callback(T data, boolean isSuccess);
}
