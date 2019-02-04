package com.alex.theguide.model;

import java.util.List;

public interface IRepository {

    void loadFileModels(Long folderID, LoadFileModelsCallback callback);


    interface LoadFileModelsCallback {
        void onLoad(List<FileModel> fileModelList);
        void onError(Throwable error);
    }
}
