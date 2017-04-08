package donkey.bora.com.controller;

import donkey.bora.com.model.Hello;
import donkey.bora.com.model.JsonResponseWrapper;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class IntroController implements IController {

    private OnHelloCallback onHelloCallback;

    public interface OnHelloCallback {
        void success();
        void fail();
    }

    public void requestHello(final OnHelloCallback onHelloCallback) {
        this.onHelloCallback = onHelloCallback;

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.hello().enqueue(new JsonResponseWrapper<Hello>() {
            @Override
            public void callback(Hello data, boolean isSuccess) {
                if (isSuccess) {
                    onHelloCallback.success();
                } else {
                    onHelloCallback.fail();
                }
            }
        });
    }
}
