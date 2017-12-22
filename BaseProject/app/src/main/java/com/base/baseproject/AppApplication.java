package com.base.baseproject;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.base.baseproject.di.AppComponent;
import com.base.baseproject.di.DaggerAppComponent;
import com.base.baseproject.di.module.AppModule;
import com.base.baseproject.di.module.HttpModule;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */


public class AppApplication extends Application {




    private View mView;


    private AppComponent mAppComponent;



    public static AppApplication get(Context context){
        return (AppApplication)context.getApplicationContext();
    }

    public AppComponent getAppComponent(){

        return mAppComponent;
    }


    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(this))
                .httpModule(new HttpModule()).build();
    }



}
