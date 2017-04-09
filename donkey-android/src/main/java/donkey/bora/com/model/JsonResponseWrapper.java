package donkey.bora.com.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class JsonResponseWrapper<T> implements Callback<JsonResponse<T>> {

    private static final String TAG = "http";
    private static final String OK_200 = "200";

    @Override
    public void onResponse(Call<JsonResponse<T>> call, Response<JsonResponse<T>> response) {
        if (response.body() != null) {
            if (OK_200.equals(response.body().getCode())) {
                Log.e(TAG, "onResponse: [code]: " + response.body().getCode() + ", [msg]: " + response.body().getMsg() + ", [detail]: " + response.body().getDetail());
                callback(response.body().getData(), true);
                return;
            }
            Log.e(TAG, "onResponse: [code]: " + response.body().getCode() + ", [msg]: " + response.body().getMsg() + ", [detail]: " + response.body().getDetail());
        } else {
            Log.e(TAG, "onResponse: [code]: " + response.raw().code() + ", [msg]: " + response.raw().message() + ", [detail]: " + response.body().getDetail());
        }
        callback(null, false);
    }

    @Override
    public void onFailure(Call<JsonResponse<T>> call, Throwable t) {
        Log.e(TAG, "onFailure: " + t.getMessage() + ", " + t.getCause());
        callback(null, false);
    }

    public abstract void callback(T data, boolean isSuccess);
}
