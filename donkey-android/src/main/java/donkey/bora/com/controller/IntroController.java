package donkey.bora.com.controller;

import donkey.bora.com.network.JsonResponseWrapper;
import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class IntroController implements IController {

    public interface OnAuthorizationCallback {
        void callback(boolean isAuthorizedUser);
    }

    public void requestPreCheck(final OnAuthorizationCallback onAuthorizationCallback) {

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.preCheck("TEMP-TOKEN").enqueue(new JsonResponseWrapper<PreCheckVO>() {
            @Override
            public void callback(PreCheckVO data, boolean isSuccess) {
                onAuthorizationCallback.callback(isSuccess ? data.isResultOK() : false);
            }
        });
    }
}
