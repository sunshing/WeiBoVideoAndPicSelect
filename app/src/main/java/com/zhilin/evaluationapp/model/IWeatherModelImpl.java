package com.zhilin.evaluationapp.model;

import android.graphics.Bitmap;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.zhilin.evaluationapp.util.XSYTool;
import com.zhilin.evaluationapp.view.IWetherView;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SongCaiBain on 2017/10/26.
 */

public class IWeatherModelImpl implements IWetherModel {
    @Override
    public void getStringInfo(final IWetherView mView, String url, HashMap<String, String> map) {
        if (mView != null) {
            mView.showWaitingDialog();
        }

        OkGo.post(url)
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .params(map)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                        // System.out.print("s" + s);

                        mView.onInfoUpdate("2017年12月23日，多云转晴");
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                        super.onError(call, response, e);
                    }
                });
    }

    @Override
    public void getFileDownloadInfo(final IWetherView mView, String url, HashMap<String, String> map) {
        final String destFileDir = XSYTool.getSDCardPath() + "/mvpdemo";
        final String destFileName = "test.jpg";

        if (mView != null) {
            mView.showWaitingDialog();
        }
        OkGo.get(url)
                .tag(this)
                .execute(new FileCallback(destFileDir, destFileName) {  //文件下载时，可以指定下载的文件目录和文件名
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        // file 即为文件数据，文件保存在指定目录
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                        Log.i("onSuccess--", "" + file.getAbsolutePath());

                        mView.onFileShow(destFileName);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
                        //currentSize totalSize以字节byte为单位
                        int down=(int) (100 * progress);
                        Log.i("downloadProgress", "" + down);
                        mView.showDownProgress(down);
                        if (down == 100) {
                            if (mView != null) {
                                mView.dissmissWaitingDialog();
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("onError", "" + e.toString());
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                    }
                });
    }

    @Override
    public void getFileUploadInfo(final IWetherView mView, String url, String keyName, List<File> files, HashMap<String, String> map) {
        if (mView != null) {
            mView.showWaitingDialog();
        }
        OkGo.post(url)//
                .tag(this)//
                //.isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                //.params("param1", "paramValue1")        // 这里可以上传参数
                //.params("file1", new File("filepath1"))   // 可以添加文件上传
                //.params("file2", new File("filepath2"))     // 支持多文件同时添加上传
               // .params(map)
                .addFileParams(keyName, files)    // 这里支持一个key传多个文件
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //上传成功
                        Log.i("onSuccess-","上传成功");
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
                        Log.i("upProgress-",""+(int) (100 * progress));
                        mView.showUpProgress("已上传" + currentSize/1024 + "KB, 共" + totalSize/1024 + "KB;");
                        if ((int) (100 * progress) == 100) {
                            if (mView != null) {
                                mView.dissmissWaitingDialog();
                            }
                        }
                     //   "已上传" + currentSize/1024/1024 + "MB, 共" + totalSize/1024/1024 + "MB;"
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("onError", "" + e.toString());
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }
                    }
                });
    }

    @Override
    public void getNetPicInfo(final IWetherView mView, String url) {
        if (mView != null) {
            mView.showWaitingDialog();
        }
        OkGo.get(url)//
                .tag(this)//
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap, Call call, Response response) {
                        // bitmap 即为返回的图片数据
                        if(bitmap!=null){
                            mView.setImageBitmap(bitmap);

                        }
                        if (mView != null) {
                            mView.dissmissWaitingDialog();
                        }

                    }
                });
    }
}
