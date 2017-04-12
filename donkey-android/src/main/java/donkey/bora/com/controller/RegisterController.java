package donkey.bora.com.controller;

import donkey.bora.com.model.DepartmentListResponseVO;
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

    public void requestRegister(final OnRegisterCallback onRegisterCallback, String email, String authCode, long departmentId) {
        RegisterSendInfo info = new RegisterSendInfo();
        info.setEmail(email);
        info.setAuthCode(authCode);
        info.setDepartmentId(departmentId);

        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.register(info).enqueue(new JsonResponseWrapper<RegisterResponseVO>() {
            @Override
            public void callback(RegisterResponseVO data, boolean isSuccess) {
                onRegisterCallback.callback(data);
            }
        });
    }

    public interface OnDepartmentListCallback {
        void callback(DepartmentListResponseVO departmentListResponseVO);
    }

    public void requestDepartmentList(final OnDepartmentListCallback onDepartmentListCallback, String email, String authCode) {
        IRequest request = ApiRequest.getInstance().request(IRequest.class);
        request.getDepartments(email, authCode).enqueue(new JsonResponseWrapper<DepartmentListResponseVO>() {
            @Override
            public void callback(DepartmentListResponseVO data, boolean isSuccess) {
                onDepartmentListCallback.callback(data);
            }
        });
    }
}


