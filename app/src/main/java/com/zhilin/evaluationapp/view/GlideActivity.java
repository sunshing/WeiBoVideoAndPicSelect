
package com.zhilin.evaluationapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhilin.evaluationapp.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Glide加载图片
 * Created by SongCaiBain on 2017/11/27.
 */


public class GlideActivity extends Activity implements View.OnClickListener {
    //String Url = "http://a4.att.hudong.com/05/71/01300000057455120185716259013.jpg";
    String Url = "http://img.ycwb.com/news/attachement/gif/site2/20160921/507b9d762551194c19be5f.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        initView();
    }

    private void initView() {
        ImageView targetImageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView targetImageView22 = (ImageView) findViewById(R.id.imageView22);
        ImageView targetImageView23 = (ImageView) findViewById(R.id.imageView23);
        ImageView targetImageView24 = (ImageView) findViewById(R.id.imageView24);
        ImageView targetImageView244 = (ImageView) findViewById(R.id.imageView244);
        ImageView targetImageView25 = (ImageView) findViewById(R.id.imageView25);
        ImageView targetImageView26 = (ImageView) findViewById(R.id.imageView26);
        ImageView targetImageView27 = (ImageView) findViewById(R.id.imageView27);
        ImageView targetImageView28 = (ImageView) findViewById(R.id.imageView28);
        ImageView targetImageView29 = (ImageView) findViewById(R.id.imageView29);

        Glide.with(this).load(Url).bitmapTransform(new BlurTransformation(this)).into(targetImageView2);
        Glide.with(this).load(Url).bitmapTransform(new ColorFilterTransformation(this, getResources().getColor(R.color.colorAccent))).into(targetImageView22);
        Glide.with(this).load(Url).bitmapTransform(new CropCircleTransformation(this)).crossFade(1000).thumbnail(0.1f).into(targetImageView23);
        Glide.with(this).load(Url).bitmapTransform(new CropSquareTransformation(this)).into(targetImageView24);
        Glide.with(this).load(Url).bitmapTransform(new CropTransformation(this)).into(targetImageView244);
        Glide.with(this).load(Url).bitmapTransform(new GrayscaleTransformation(this)).into(targetImageView25);
        Glide.with(this).load(Url).bitmapTransform(new MaskTransformation(this, R.mipmap.ic_launcher)).crossFade(1000).thumbnail(0.1f).into(targetImageView26);
        Glide.with(this).load(Url).bitmapTransform(new RoundedCornersTransformation(this, 30, 0, RoundedCornersTransformation.CornerType.ALL)).into(targetImageView27);


        Glide.with(this).load(Url).into(targetImageView28);
        Glide.with(this).load(Url).error(R.mipmap.ic_launcher).into(targetImageView29);

    }

    @Override
    public void onClick(View v) {

    }
}

