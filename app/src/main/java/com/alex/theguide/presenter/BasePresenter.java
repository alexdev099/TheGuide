package com.alex.theguide.presenter;

import com.alex.theguide.view.IMvpView;

public abstract class BasePresenter<T extends IMvpView> implements IMvpPresenter<T> {

    private T view;

    @Override
    public void attachView(T mvpView) {
        view = mvpView;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public T getView() {
        return view;
    }

}
