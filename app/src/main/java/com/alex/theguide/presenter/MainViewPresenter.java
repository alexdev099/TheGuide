package com.alex.theguide.presenter;

import android.util.Log;

import com.alex.theguide.model.FileModel;
import com.alex.theguide.model.IRepository;
import com.alex.theguide.view.IMainActivityView;

import java.util.List;

public class MainViewPresenter extends BasePresenter<IMainActivityView> implements IMainViewPresenter {

    private static final String MY_LOG = MainViewPresenter.class.getSimpleName();

    private final IRepository repository;

    public MainViewPresenter(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void viewIsReady(Long currentFolderID) {
        loadFileModelsList(currentFolderID);
    }

    private void loadFileModelsList(Long folderNameID) {
        repository.loadFileModels(folderNameID, new IRepository.LoadFileModelsCallback() {
            @Override
            public void onLoad(List<FileModel> fileModelList) {
                getView().showFileModelList(fileModelList);
            }

            @Override
            public void onError(Throwable error) {
                Log.e(MY_LOG, "DataBase error: " + error);
                getView().showErrorMessage();
            }
        });
    }

    @Override
    public void itemClicked(FileModel fileModel) {
        if (fileModel.getIsFolder()){
            getView().openFolder(fileModel.getId());
        } else {
            getView().showMessage("This is the FILE!!!");
        }
    }

    @Override
    public void itemLongClicked() {
        getView().showMessage("Long click PUSH!!!");
        getView().showDialog();
    }

    @Override
    public void linkAsFavoriteClicked() {
        getView().showMessage("Link as favorite was clicked!");
    }

    @Override
    public void linkGetPermalinkClicked() {
        getView().showMessage("Link get permalink was clicked!");
    }

    @Override
    public void linkDeleteClicked() {
        getView().showMessage("Link delete was clicked!");
    }

    @Override
    public void itemSwiped() {
        getView().showDialog();
    }
}
