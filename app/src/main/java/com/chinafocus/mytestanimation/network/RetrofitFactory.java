package com.chinafocus.mytestanimation.network;

import com.chinafocus.mytestanimation.global.MyTestApplication;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class RetrofitFactory {

    static Retrofit getDownloadService() {
        SSLSocketFactory sslSocketFactory = HttpsUtils.setCertificatesFromFile(MyTestApplication.sContext, "expressreader.cn.crt");

        OkHttpClient client;
        if (sslSocketFactory != null) {
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(HttpsUtils.hostnameVerifier)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .sslSocketFactory(HttpsUtils.getSLLContext().getSocketFactory())
                    .hostnameVerifier(HttpsUtils.hostnameVerifier)
                    .build();
        }

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL_SHELVES)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    static Retrofit getNewApkService() {

        return new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_APK_SHELVES)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .sslSocketFactory(HttpsUtils.getSLLContext().getSocketFactory())
                        .hostnameVerifier(HttpsUtils.hostnameVerifier)
                        .build())
                .build();
    }
}
