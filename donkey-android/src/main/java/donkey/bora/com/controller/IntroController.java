package donkey.bora.com.controller;

import donkey.bora.com.model.Hello;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroController implements IController {

    private OnHelloCallback onHelloCallback;

    public interface OnHelloCallback {
        void success();
        void fail();
    }

    public void requestHello(OnHelloCallback onHelloCallback) {
        this.onHelloCallback = onHelloCallback;

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.hello().enqueue(helloCallback);
    }

    private Callback<Hello> helloCallback = new Callback<Hello>() {
        @Override
        public void onResponse(Call<Hello> call, Response<Hello> response) {
            if (response.body() != null && "success".equals(response.body().getMsg())) {
                onHelloCallback.success();
            } else {
                onHelloCallback.fail();
            }
        }

        @Override
        public void onFailure(Call<Hello> call, Throwable t) {
            onHelloCallback.fail();
        }
    };

}
