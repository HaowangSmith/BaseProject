package com.base.baseproject.di.module;

import android.app.Application;

import com.base.baseproject.common.rx.RxErrorHandler;
import com.base.baseproject.common.util.LogUtil;
import com.base.baseproject.common.util.PropertyUtil;
import com.base.baseproject.data.ApiService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */

@Module
public class HttpModule {





    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Application application) {
        boolean debug = PropertyUtil.isLogAvailable(application);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (debug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtil.logger(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);//
        }
        return builder
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient);


        return builder.build();

    }



    @Provides
    @Singleton
    public ApiService provideApiService(Retrofit retrofit){

        return  retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    public RxErrorHandler provideErrorHandlder(Application application){

        return  new RxErrorHandler(application);
    }



}
