package com.alex.theguide.presenter;

import com.alex.theguide.model.FileModel;
import com.alex.theguide.view.IMainActivityView;

public interface IMainViewPresenter extends IMvpPresenter<IMainActivityView> {

    void itemClicked(FileModel fileModel);

    void itemLongClicked();

    void linkAsFavoriteClicked();

    void linkGetPermalinkClicked();

    void linkDeleteClicked();

    void itemSwiped();
}
