package com.base.baseproject.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.baseproject.AppApplication;
import com.base.baseproject.di.AppComponent;
import com.base.baseproject.presenter.BasePresenter;
import com.base.baseproject.ui.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    @Inject
    T mPresenter;

    protected AppApplication mApplication;
    protected Context        mContext;
    protected Activity       mActivity;
    private   Unbinder       mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this.mActivity = this;
        setContentView(getlayoutResID());
        mUnbinder = ButterKnife.bind(this);
        this.mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());
        init();
    }

    protected abstract int getlayoutResID();

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
    @Override
    public void showLoading() {
    }

    @Override
    public void showError(String msg) {
    }

    @Override
    public void dismissLoading() {
    }

}
