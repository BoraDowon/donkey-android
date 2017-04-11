package donkey.bora.com.controller;

import donkey.bora.com.network.JsonResponseWrapper;
import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;
import donkey.bora.com.secure.TokenManager;

public class IntroController implements IController {

    public interface OnAuthorizationCallback {
        void callback(boolean isAuthorizedUser);
    }

    public void requestPreCheck(final OnAuthorizationCallback onAuthorizationCallback) {

        String token = TokenManager.load();

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.preCheck(token).enqueue(new JsonResponseWrapper<PreCheckVO>() {
            @Override
            public void callback(PreCheckVO data, boolean isSuccess) {
                onAuthorizationCallback.callback(isSuccess ? data.isResultOK() : false);
            }
        });
    }
}
