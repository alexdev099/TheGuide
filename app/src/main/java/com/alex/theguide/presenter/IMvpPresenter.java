package com.alex.theguide.presenter;

import com.alex.theguide.view.IMvpView;

public interface IMvpPresenter<V extends IMvpView> {

    void attachView(V mvpView);

    void detachView();

    void viewIsReady(Long folderID);

}
