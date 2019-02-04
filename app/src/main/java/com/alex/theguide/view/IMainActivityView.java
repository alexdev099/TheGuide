package com.alex.theguide.view;

import com.alex.theguide.model.FileModel;

import java.util.List;

public interface IMainActivityView extends IMvpView {

    void showFileModelList(List<FileModel> fileModels);

    void showMessage(String string);

    void openFolder(Long folderID);

    void showDialog();

    void showErrorMessage();
}
