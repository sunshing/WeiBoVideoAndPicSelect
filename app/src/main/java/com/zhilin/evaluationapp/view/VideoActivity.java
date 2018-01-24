package com.zhilin.evaluationapp.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhilin.evaluationapp.R;

public class VideoActivity extends AppCompatActivity {
    private final int GET_PERMISSION_REQUEST = 100; //权限申请自定义码
    public static  RelativeLayout rl_video;
    public static  ImageView iv_video;
    public static ImageView iv_play;
    public static String videopath;
    private  TextView tv_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoActivity.this.finish();
            }
        });
        rl_video = (RelativeLayout) findViewById(R.id.rl_video);
        iv_video = (ImageView) findViewById(R.id.iv_video);
        rl_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermissions();
            }
        });
        iv_video.performClick();//首次进入模拟点击事件
        iv_play = (ImageView) findViewById(R.id.iv_play);
    }

    /**
     * 获取权限
     */
    private void getPermissions() {
        if (videopath != null && !"".equals(videopath)) {
            //预览模式
            Intent intent = new Intent(this, VideoPreviewActivity.class);
            intent.putExtra("videopath", videopath);
            startActivity(intent);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager
                        .PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager
                                .PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager
                                .PERMISSION_GRANTED) {
                    startActivityForResult(new Intent(VideoActivity.this, CameraActivity.class), 100);
                } else {
                    //不具有获取权限，需要进行权限申请
                    ActivityCompat.requestPermissions(VideoActivity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.CAMERA}, GET_PERMISSION_REQUEST);
                }
            } else {
                startActivityForResult(new Intent(VideoActivity.this, CameraActivity.class), 100);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            Log.i("CJT", "picture");
            String path = data.getStringExtra("path");
            Glide.with(VideoActivity.this).load(path).into(VideoActivity.iv_video);
          //  iv_video.setImageBitmap(BitmapFactory.decodeFile(path));
        }

        if (resultCode == 102) {
            Log.i("CJT", "video");
            String path = data.getStringExtra("path");
            videopath = data.getStringExtra("videopath");
            Glide.with(VideoActivity.this).load(path).into(VideoActivity.iv_video);
          //  iv_video.setImageBitmap(BitmapFactory.decodeFile(path));
            iv_play.setVisibility(View.VISIBLE);

        }
        if (resultCode == 103) {
            Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GET_PERMISSION_REQUEST) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    startActivityForResult(new Intent(VideoActivity.this, CameraActivity.class), 100);
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
