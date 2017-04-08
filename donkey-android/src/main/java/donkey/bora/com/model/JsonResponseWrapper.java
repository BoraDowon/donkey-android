package donkey.bora.com.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class JsonResponseWrapper<T> implements Callback<JsonResponse<T>> {

    private static final String TAG = "http";

    @Override
    public void onResponse(Call<JsonResponse<T>> call, Response<JsonResponse<T>> response) {
        if (response.body() != null) {
            if ("200".equals(response.body().getCode())) {
                Log.e(TAG, "[code]: " + response.body().getCode() + ", [msg]: " + response.body().getMsg());
                callback(response.body().getData(), true);
                return;
            }
            Log.e(TAG, "[code]: " + response.body().getCode() + ", [msg]: " + response.body().getMsg());
        } else {
            Log.e(TAG, "[code]: " + response.raw().code() + ", [msg]: " + response.raw().message());
        }
        callback(null, false);
    }

    @Override
    public void onFailure(Call<JsonResponse<T>> call, Throwable t) {
        callback(null, false);
    }

    public abstract void callback(T data, boolean isSuccess);
}
