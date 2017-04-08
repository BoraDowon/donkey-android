package donkey.bora.com.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class JsonResponseWrapper<T> implements Callback<JsonResponse<T>> {

    @Override
    public void onResponse(Call<JsonResponse<T>> call, Response<JsonResponse<T>> response) {
        boolean isSuccess = false;
        if (response.body() != null && "success".equals(response.body().getMsg())) {
            isSuccess = true;
        }
        callback(response.body().getData(), isSuccess);
    }

    @Override
    public void onFailure(Call<JsonResponse<T>> call, Throwable t) {
        callback(null, false);
    }

    public abstract void callback(T data, boolean isSuccess);
}
