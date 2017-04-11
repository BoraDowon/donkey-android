package donkey.bora.com.controller;

import donkey.bora.com.model.PreCheckVO;
import donkey.bora.com.model.RegisterResponseVO;
import donkey.bora.com.model.RegisterSendInfo;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;
import donkey.bora.com.network.JsonResponseWrapper;

public class RegisterController {

    public interface OnRegisterCallback {
        void callback(RegisterResponseVO registerResponseVO);
    }

    public void requestRegister(final OnRegisterCallback onRegisterCallback, String email, String authCode) {
        RegisterSendInfo info = new RegisterSendInfo();
        info.setEmail(email);
        info.setAuthCode(authCode);

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.register(info).enqueue(new JsonResponseWrapper<RegisterResponseVO>() {
            @Override
            public void callback(RegisterResponseVO data, boolean isSuccess) {
                onRegisterCallback.callback(data);
            }
        });
    }
}


