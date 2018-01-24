package com.zhilin.evaluationapp.presenter;


import com.zhilin.evaluationapp.model.IWeatherModelImpl;
import com.zhilin.evaluationapp.model.IWetherModel;
import com.zhilin.evaluationapp.view.IWetherView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SongCaiBain on 2017/10/26.
 * Presenter是个大忙人，因为要同时对View和Model对接，所以内部必须持有它们的接口引用。
 */

public class IWeatherPresenter {
    IWetherModel mModel;
    IWetherView mView;

    public IWeatherPresenter(IWetherView view) {
        this.mView = view;
        mModel = new IWeatherModelImpl();
    }

    /**
     * 获取天气信息
     *
     * @return
     */
    public void requestWeatherInfo(String url, HashMap<String, String> map) {
        mModel.getStringInfo(mView, url, map);
    }

    /**
     * 文件下载
     * @param url
     * @param map
     */
    public void requestFileDownload(String url, HashMap<String, String> map) {
        mModel.getFileDownloadInfo(mView, url, map);
    }


    /**
     * 文件上传
     * @param url
     * @param map
     */
    public void requestFileUpload(String url, String keyName, List<File> files, HashMap<String, String> map) {
        mModel.getFileUploadInfo(mView, url,keyName,files, map);
    }
    public void requestNetPic(String url) {
        mModel.getNetPicInfo(mView, url);
    }

}
