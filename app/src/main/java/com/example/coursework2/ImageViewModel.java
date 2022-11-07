package com.example.coursework2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ImageViewModel extends ViewModel {
    protected final MutableLiveData<ImageModel> _image = new MutableLiveData<>();
    protected final MutableLiveData<String> _dataStatus = new MutableLiveData<>();

    public void setDataStatus(String value) {
        _dataStatus.setValue(value);
    }

    public LiveData<String> getDataStatus() {
        return _dataStatus;
    }

    public void setImage(ImageModel imageModel) {
        _image.setValue(imageModel);
    }

    public LiveData<ImageModel> getImage() {
        return _image;
    }

}
