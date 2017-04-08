package donkey.bora.com.controller;

import donkey.bora.com.model.EmailAuthSendVO;
import donkey.bora.com.model.EmailCheckVO;
import donkey.bora.com.model.JsonResponseWrapper;
import donkey.bora.com.model.PinCodeCheckVO;
import donkey.bora.com.network.ApiRequest;
import donkey.bora.com.network.IRequest;

public class EmailAuthController implements IController {

    public interface OnCheckEmailCallback {
        void callback(EmailCheckVO emailCheckVO);
    }

    public void requestCheckEmail(String email, final OnCheckEmailCallback onCheckEmailCallback) {

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.emailValidateCheck(email).enqueue(new JsonResponseWrapper<EmailCheckVO>() {
            @Override
            public void callback(EmailCheckVO data, boolean isSuccess) {
                onCheckEmailCallback.callback(data);
            }
        });
    }

    public interface OnSendEmailCallback {
        void callback(EmailAuthSendVO emailAuthSendVO);
    }

    public void requestSendEmail(String email, final OnSendEmailCallback onSendEmailCallback) {
        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.emailSend(email).enqueue(new JsonResponseWrapper<EmailAuthSendVO>() {
            @Override
            public void callback(EmailAuthSendVO data, boolean isSuccess) {
                onSendEmailCallback.callback(data);
            }
        });
    }

    public interface OnPinCodeConfrimCallback {
        void callback(PinCodeCheckVO pinCodeCheckVO);
    }

    public void requestPinCodeConfirm(String email, String pinCode, final OnPinCodeConfrimCallback onPinCodeConfrimCallback) {
        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.pinCodeCheck(email, pinCode).enqueue(new JsonResponseWrapper<PinCodeCheckVO>() {
            @Override
            public void callback(PinCodeCheckVO data, boolean isSuccess) {
                onPinCodeConfrimCallback.callback(data);
            }
        });
    }



}
