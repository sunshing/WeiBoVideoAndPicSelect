package com.zhilin.evaluationapp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.zhilin.evaluationapp.R;
import com.zhilin.evaluationapp.presenter.IWeatherPresenter;
import com.zhilin.evaluationapp.util.XSYTool;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 简单的mvp+okgo框架，请求实时天气信息
 */
public class MvpMainActivity extends Activity implements IWetherView, View.OnClickListener {

    /**
     * View—–>Presenter
     * 从视图界面出发，用户要请求数据，而Presenter是具体实现者，所以Presenter要提供方法代View的实现者调用，
     * 并且View的实现中必须要有Presenter的引用。
     * 所以MainActivity.java中要有WetherPresenter的引用。
     */

    IWeatherPresenter mPresenter;
    private ProgressDialog mDialog;
    TextView tv_weather;
    TextView tv_down;
    TextView tv_up;
    ImageView iv_pic;
    ImageView iv_net_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_main);
        mPresenter = new IWeatherPresenter(this);
        initView();
    }

    private void initView() {
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_down = (TextView) findViewById(R.id.tv_down);
        tv_up = (TextView) findViewById(R.id.tv_up);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        iv_net_pic = (ImageView) findViewById(R.id.iv_net_pic);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    //得到数据后，处理数据
    @Override
    public void onInfoUpdate(final String info) {
        tv_weather.setText(info);
    }

    //图片显示
    @Override
    public void onFileShow(String fileName) {
        //加载本地Bitmap图片
        // XSYTool.setImageView(iv_pic,fileName);
        //Glide显示图片
        String Url = XSYTool.getSDCardPath() + "/mvpdemo/" + fileName;
        Glide.with(this).load(Url).into(iv_pic);
    }

    @Override
    public void showDownProgress(int progress) {
        tv_down.setText("已完成" + progress + "%");
    }

    @Override
    public void showUpProgress(String progress) {
        tv_up.setText(progress);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        iv_net_pic.setImageBitmap(bitmap);
    }

    @Override
    public void showWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }

                mDialog = ProgressDialog.show(MvpMainActivity.this, "", "正在获取中...");
            }
        });
    }

    @Override
    public void dissmissWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                requestWeatherInfo();
                break;
            case R.id.button2:
                requestFileDownload();
                break;
            case R.id.button3:
                requestFileUpload();
                break;
            case R.id.button4:
                requestNetPic();
                break;
        }
    }




    private void requestWeatherInfo() {
        String url = "http://image.baidu.com/search/index?";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("tn", "baiduimage");//第几页
        map.put("ipn", "d");//第几页
        map.put("istype", "2");//第几页
        map.put("ie", "utf-8");//第几页
        map.put("oe", "utf-8");//第几页
        map.put("word", "云漫作品");//第几页
        mPresenter.requestWeatherInfo(url, map);//数据请求
    }

    private void requestFileDownload() {
        String url = "http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("tn", "baiduimage");//第几页
        map.put("ipn", "d");//第几页
        map.put("istype", "2");//第几页
        map.put("ie", "utf-8");//第几页
        map.put("oe", "utf-8");//第几页
        map.put("word", "云漫作品");//第几页
        mPresenter.requestFileDownload(url, map);//数据请求
    }

    private void requestFileUpload() {
        String url="http://image.baidu.com/search/index?";//上传地址
        HashMap<String, String> map = new HashMap<String, String>();
        String keyName = "头像";
        List<File> files = new ArrayList<File>();
        String url1 = XSYTool.getSDCardPath() + "/mvpdemo/test.jpg";
        String url2 = XSYTool.getSDCardPath() + "/mvpdemo/test.jpg";
        File file1=new File(url1);
        File file2=new File(url2);
        files.add(file1);
        files.add(file2);

        mPresenter.requestFileUpload(url, keyName, files, map);//数据请求
    }

    private void requestNetPic() {
        String url="http://h.hiphotos.baidu.com/image/pic/item/50da81cb39dbb6fd7d9873ec0024ab18962b373c.jpg";
        mPresenter.requestNetPic(url);
    }
}
