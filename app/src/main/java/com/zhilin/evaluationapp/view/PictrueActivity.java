package com.zhilin.evaluationapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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

public class PictrueActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private ArrayList<String> imagePaths = new ArrayList<>();

    private GridView gridView;
    private GridAdapter gridAdapter;
    private TextView submit_click;
    private EditText et_context;
    private TextView tv_cancel;
    private TextView tv_title;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictrue_activity);
        initView();
        initData();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_cancel.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridView);
        et_context = (EditText) findViewById(R.id.et_context);
        submit_click = (TextView) findViewById(R.id.find_comment_submit);
        submit_click.setOnClickListener(this);
        ImageView iv_add = (ImageView) findViewById(R.id.iv_add);
        iv_add.setOnClickListener(this);
        iv_add.performClick();//首次进入模拟点击事件
    }

    private void initData() {
        tv_title.setText("发图片");
        gridView.setVisibility(View.VISIBLE);
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gridView.setNumColumns(cols);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String imgs = (String) parent.getItemAtPosition(position);
                if ("paizhao".equals(imgs)) {
                    defaultSelectPic();
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(PictrueActivity.this);
                    intent.setCurrentItem(position);
                    if (imagePaths != null && imagePaths.size() > 0) {
                        if (imagePaths.contains("paizhao")) {
                            imagePaths.remove("paizhao");
                        }
                    }
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }
            }
        });
        imagePaths.add("paizhao");//初始化，添加图片按钮
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);

    }

    private void submitPic() {
        if (imagePaths.contains("paizhao")) {
            imagePaths.remove("paizhao");
        }
        if (imagePaths.size() <= 0) {
            XSYTool.showToastmsg(this, "请选择相片");
            initData();
            return;
        }

        XSYTool.showToastmsg(this, "提交成功");
        imagePaths.clear();
        imagePaths.add("paizhao");//初始化，添加图片按钮
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
        this.finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    Log.d(TAG, "数量：" + list.size());
                    loadAdpater(list);
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    ArrayList<String> ListExtra = data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT);
                    loadAdpater2(ListExtra);
                    break;
            }
        }
    }

    private void loadAdpater2(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            imagePaths.clear();
        }
        if (paths.contains("paizhao")) {
            paths.remove("paizhao");
        }

        if (paths.size() < 6) {
            paths.add("paizhao");
        }
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths);
        gridView.setAdapter(gridAdapter);
    }

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths != null && imagePaths.size() > 0) {
            if (imagePaths.contains("paizhao")) {
                imagePaths.remove("paizhao");
            }
        }
        if (paths.contains("paizhao")) {
            paths.remove("paizhao");
        }

        if (paths.size() < 6) {
            paths.add("paizhao");
        }
        imagePaths.addAll(paths);
        gridAdapter = new GridAdapter(imagePaths);
        if (imagePaths.size() > 6) {
            if (imagePaths.contains("paizhao")) {
                imagePaths.remove("paizhao");
            }
        }

        gridView.setAdapter(gridAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                this.finish();
                break;
            case R.id.iv_add:
                defaultSelectPic();
                break;
            case R.id.find_comment_submit:
                submitPic();
                break;
        }
    }

    private void defaultSelectPic() {
        PhotoPickerIntent intent = new PhotoPickerIntent(PictrueActivity.this);
        intent.setSelectModel(SelectModel.MULTI);
        intent.setShowCarema(true); // 是否显示拍照
        intent.setMaxTotal(6 - imagePaths.size() + 1); // 最多选择照片数量，默认为6
        intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent, REQUEST_CAMERA_CODE);

    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            inflater = LayoutInflater.from(PictrueActivity.this);
        }

        public int getCount() {
            return listUrls.size();
        }

        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.pic_grid_item, parent, false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final String path = listUrls.get(position);
            if (path.equals("paizhao")) {
                holder.image.setImageResource(R.mipmap.find_add_img);
            } else {
                Glide.with(PictrueActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }

        class ViewHolder {
            ImageView image;
        }
    }
}
