package com.zhilin.evaluationapp.view;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.zhilin.evaluationapp.R;

/**
 * Created by SongCaiBain on 2018/1/23.
 */

public class VideoPreviewActivity extends AppCompatActivity implements View.OnClickListener {
    String videopath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_preview);
        videopath = getIntent().getStringExtra("videopath");
        initView();
    }

    private void initView() {
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        ImageView iv_del = (ImageView) findViewById(R.id.iv_del);
        iv_del.setOnClickListener(this);

        Uri uri = Uri.parse(videopath);
        VideoView videoView = (VideoView) this.findViewById(R.id.video_view);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        //去除VideoView底部边框的办法
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);

        videoView.start();
        videoView.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.finish();
                break;
            case R.id.iv_del:
                deleteVideo();
                break;
        }
    }

    private void deleteVideo() {
        new AlertDialog.Builder(this)
                .setTitle(com.lidong.photopicker.R.string.confirm_to_delete)
                .setPositiveButton(com.lidong.photopicker.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //执行删除操作
                        VideoActivity.videopath = "";
                        VideoActivity.iv_play.setVisibility(View.GONE);
                        Glide.with(VideoPreviewActivity.this).load(R.mipmap.find_add_img).asBitmap().into(VideoActivity.iv_video);
                        Resources resources = getBaseContext().getResources();
                        Drawable imageDrawable = resources.getDrawable(R.mipmap.find_add_img); //图片在drawable目录下
                        VideoActivity.rl_video.setBackgroundDrawable(imageDrawable);
                        onBackPressed();
                    }
                })
                .setNegativeButton(com.lidong.photopicker.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
