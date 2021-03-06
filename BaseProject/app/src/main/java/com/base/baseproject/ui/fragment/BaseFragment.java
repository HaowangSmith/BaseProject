package com.base.baseproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.baseproject.AppApplication;
import com.base.baseproject.di.AppComponent;
import com.base.mylibrary.presenter.BasePresenter;
import com.base.mylibrary.presenter.BaseView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by WangHao
 *
 * @ 创建时间 2017/10/13  11:16
 */


public  abstract  class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {



    private Unbinder mUnbinder;

    private AppApplication mApplication;

    private View mRootView;


    @Inject
    T mPresenter ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



         mRootView = inflater.inflate(setLayout(), container, false);
         mUnbinder=  ButterKnife.bind(this, mRootView);



        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        this.mApplication = (AppApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());

        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mUnbinder != Unbinder.EMPTY){
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

    public abstract int setLayout();

    public abstract  void setupAcitivtyComponent(AppComponent appComponent);


    public abstract void  init();


}
