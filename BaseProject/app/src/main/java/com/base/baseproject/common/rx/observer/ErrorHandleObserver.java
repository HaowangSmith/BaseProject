package com.base.baseproject.common.rx.observer;

import android.content.Context;

import com.base.baseproject.common.exception.BaseException;
import com.base.baseproject.common.rx.RxErrorHandler;

import com.base.baseproject.common.util.LogUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */

public abstract class ErrorHandleObserver<T> extends DefaultObserver<T> {
    private   Context        context;
    protected RxErrorHandler rxErrorHandler;

    public ErrorHandleObserver(Context context) {
        this.context = context;
        this.rxErrorHandler = new RxErrorHandler(context);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onError(@NonNull Throwable e) {
        //e.printStackTrace();
        BaseException baseException = rxErrorHandler.onErrorHandle(e);
        if (baseException == null) {
            LogUtil.logger(e.getMessage());
        } else {
            rxErrorHandler.showErrorMessage(baseException);
        }
    }

    @Override
    public void onComplete() {
    }
}
