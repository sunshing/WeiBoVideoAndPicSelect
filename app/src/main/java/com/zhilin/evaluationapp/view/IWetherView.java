package com.zhilin.evaluationapp.view;

import android.graphics.Bitmap;

/**
 * Created by SongCaiBain on 2017/10/26.
 */

public interface IWetherView {
    /**
     * 获取天气信息
     * @param info
     */
    public void onInfoUpdate(String info);

    /**
     * 文件下载 上传
     * @param fileName
     */
    public void onFileShow(String fileName);
    public void showDownProgress(int progress);


    public void showUpProgress(String progress);
    public void setImageBitmap(Bitmap bitmap);



    /**
     * 加载进度条
     */
    public void showWaitingDialog();
    /**
     * 消失进度条
     */
    public void dissmissWaitingDialog();
}
