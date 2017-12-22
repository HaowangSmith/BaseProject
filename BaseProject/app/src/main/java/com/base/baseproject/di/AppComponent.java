package com.base.baseproject.di;

import android.app.Application;

import com.base.baseproject.common.rx.RxErrorHandler;
import com.base.baseproject.data.ApiService;
import com.base.baseproject.di.module.AppModule;
import com.base.baseproject.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5play.di.component
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {


    public ApiService getApiService();

    public Application getApplication();

    public RxErrorHandler getRxErrorHandler();




}
