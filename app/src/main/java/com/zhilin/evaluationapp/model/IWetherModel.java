package com.zhilin.evaluationapp.model;


import com.zhilin.evaluationapp.view.IWetherView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SongCaiBain on 2017/10/26.
 * Model层是数据层，用来存储数据并且提供数据。
 */

public interface IWetherModel {
    //提供数据
    public void getStringInfo(IWetherView mView, String url, HashMap<String, String> map);

    public void getFileDownloadInfo(IWetherView mView, String url, HashMap<String, String> map);

    public void getFileUploadInfo(IWetherView mView, String url, String keyName, List<File> files, HashMap<String, String> map);
    public void getNetPicInfo(IWetherView mView, String url);
}
