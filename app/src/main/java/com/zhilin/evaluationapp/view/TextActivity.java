package com.zhilin.evaluationapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.PhotoPreviewActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;
import com.lidong.photopicker.intent.PhotoPreviewIntent;
import com.zhilin.evaluationapp.R;
import com.zhilin.evaluationapp.util.XSYTool;

import java.util.ArrayList;

/**
 * Created by SongCaiBain on 2018/1/11.
 */

public class TextActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView submit_click;
    private EditText et_context;
    private TextView tv_cancel;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
        initView();
        initData();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cancel.setOnClickListener(this);
        et_context = (EditText) findViewById(R.id.et_context);
        submit_click = (TextView) findViewById(R.id.find_comment_submit);
        submit_click.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("发表文字");
    }

    private void submitPic() {
        if (et_context.getText().toString().length() <= 0) {
            XSYTool.showToastmsg(this, "发表内容不能为空");
            initData();
            return;
        }
        XSYTool.showToastmsg(this, "提交成功");
        this.finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.finish();
                break;
            case R.id.find_comment_submit:
                submitPic();
                break;
        }
    }
}
