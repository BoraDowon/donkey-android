package donkey.bora.com.controller;

import donkey.bora.com.model.JsonResponseWrapper;
import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class IntroController implements IController {

    private OnHelloCallback onHelloCallback;

    public interface OnHelloCallback {
        void success();
        void fail();
    }

    /*public void requestPreCheck(final OnHelloCallback onHelloCallback) {
        this.onHelloCallback = onHelloCallback;

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.preCheck("TEST-TOKEN").enqueue(new JsonResponseWrapper<PreCheckVO>() {
            @Override
            public void callback(PreCheckVO data, boolean isSuccess) {
                if (isSuccess) {
                    onHelloCallback.success();
                } else {
                    onHelloCallback.fail();
                }
            }
        });
    }*/

    public void requestHello(final OnHelloCallback onHelloCallback) {
        this.onHelloCallback = onHelloCallback;

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.hello().enqueue(new JsonResponseWrapper<Object>() {
            @Override
            public void callback(Object data, boolean isSuccess) {
                if (isSuccess) {
                    onHelloCallback.success();
                } else {
                    onHelloCallback.fail();
                }
            }
        });
    }
}
