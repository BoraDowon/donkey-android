package donkey.bora.com.controller;

import donkey.bora.com.model.EmailCheckVO;
import donkey.bora.com.model.JsonResponseWrapper;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class EmailAuthController implements IController {

    public interface OnCheckEmailCallback {
        void callback(EmailCheckVO emailCheckVO);
    }

    public void requestCheckEmail(String email, final OnCheckEmailCallback onCheckEmailCallback) {

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.emailCheck(email).enqueue(new JsonResponseWrapper<EmailCheckVO>() {
            @Override
            public void callback(EmailCheckVO data, boolean isSuccess) {
                onCheckEmailCallback.callback(data);
            }
        });
    }

}
