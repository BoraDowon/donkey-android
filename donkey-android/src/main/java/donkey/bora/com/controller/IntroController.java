package donkey.bora.com.controller;

import donkey.bora.com.model.JsonResponseWrapper;
import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class IntroController implements IController {

    private OnAuthorizationCallback onAuthorizationCallback;

    public interface OnAuthorizationCallback {
        void callback(boolean isAuthorizedUser);
    }

    public void requestPreCheck(final String token, final OnAuthorizationCallback onAuthorizationCallback) {
        this.onAuthorizationCallback = onAuthorizationCallback;

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.preCheck(token).enqueue(new JsonResponseWrapper<PreCheckVO>() {
            @Override
            public void callback(PreCheckVO data, boolean isSuccess) {
                onAuthorizationCallback.callback(data.isResultOK());
            }
        });
    }
}
