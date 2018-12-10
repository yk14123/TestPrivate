package com.chinafocus.mytestanimation.network;


public class ApiManager {

    private final ApiService mApiService;

    private ApiManager() {
        mApiService = RetrofitFactory.getDownloadService().create(ApiService.class);
    }

    public static ApiManager getInstance() {
        return Holder.INSTANCE;
    }

    static class Holder {
        static ApiManager INSTANCE = new ApiManager();
    }

    //获取ApiService接口调用对象
    public ApiService getService() {
        return mApiService;
    }


}
