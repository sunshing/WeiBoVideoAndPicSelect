package com.zhilin.evaluationapp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhilin.evaluationapp.R;
import com.zhilin.evaluationapp.common.MoreWindow;
import com.zhilin.evaluationapp.fragment.DynamicsFragment;
import com.zhilin.evaluationapp.fragment.EvaluateFragment;
import com.zhilin.evaluationapp.fragment.IndexFragment;
import com.zhilin.evaluationapp.fragment.PersonnalFragment;
import com.zhilin.evaluationapp.util.BaseDialog;
import com.zhilin.evaluationapp.util.XSYTool;


public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private LinearLayout ll1, ll2, ll3, ll4;
    private TextView tv1, tv2, tv3, tv4;
    private ImageView iv1, iv2, iv3, iv4;

    private Fragment fragment = null;
    MoreWindow mMoreWindow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_activity_main_frame);
        initView();
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tv2);
        iv2 = (ImageView) findViewById(R.id.iv2);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll2.setOnClickListener(this);

        tv3 = (TextView) findViewById(R.id.tv3);
        iv3 = (ImageView) findViewById(R.id.iv3);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll3.setOnClickListener(this);

        tv4 = (TextView) findViewById(R.id.tv4);
        iv4 = (ImageView) findViewById(R.id.iv4);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        ll4.setOnClickListener(this);
        LinearLayout llf = (LinearLayout) findViewById(R.id.llf);
        llf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow(v);
            }
        });
        fragment = new IndexFragment();
        goToFragment();
    }

    /**
     * 发布
     */
    private void showMoreWindow(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }


    /**
     * 发布文字
     */
    public void lunchText() {
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    public void lunchPic() {
        Intent intent = new Intent(this, PictrueActivity.class);
        startActivity(intent);
    }
    public void lunchVideo() {
       Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        } else {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case BaseDialog.BUTTON_NO:
                            break;
                        case BaseDialog.BUTTON_YES:
                            MainActivity.this.finish();
                            break;
                    }
                    dialog.dismiss();
                }
            };
            BaseDialog.showAlertDialog(this, "提示信息", "确定退出系统？",
                    android.R.drawable.ic_dialog_info,
                    new String[]{"确定", "取消"}, listener);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll1:
                iv1.setBackgroundResource(R.drawable.com_bottombar_r1_press);
                iv2.setBackgroundResource(R.drawable.com_bottombar_r2_unpress);
                iv3.setBackgroundResource(R.drawable.com_bottombar_r3_unpress);
                iv4.setBackgroundResource(R.drawable.com_bottombar_r4_unpress);
                tv1.setTextColor(this.getResources().getColor(R.color.colorGreen));
                tv2.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv3.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv4.setTextColor(this.getResources().getColor(R.color.colorDark));
                fragment = new IndexFragment();
                break;
            case R.id.ll2:
                iv2.setBackgroundResource(R.drawable.com_bottombar_r2_press);
                iv1.setBackgroundResource(R.drawable.com_bottombar_r1_unpress);
                iv3.setBackgroundResource(R.drawable.com_bottombar_r3_unpress);
                iv4.setBackgroundResource(R.drawable.com_bottombar_r4_unpress);
                tv2.setTextColor(this.getResources().getColor(R.color.colorGreen));
                tv1.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv3.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv4.setTextColor(this.getResources().getColor(R.color.colorDark));
                fragment = new EvaluateFragment();
                break;
            case R.id.ll3:
                iv3.setBackgroundResource(R.drawable.com_bottombar_r3_press);
                iv1.setBackgroundResource(R.drawable.com_bottombar_r1_unpress);
                iv2.setBackgroundResource(R.drawable.com_bottombar_r2_unpress);
                iv4.setBackgroundResource(R.drawable.com_bottombar_r4_unpress);
                tv3.setTextColor(this.getResources().getColor(R.color.colorGreen));
                tv1.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv2.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv4.setTextColor(this.getResources().getColor(R.color.colorDark));
                fragment = new DynamicsFragment();
                break;
            case R.id.ll4:
                iv4.setBackgroundResource(R.drawable.com_bottombar_r4_press);
                iv1.setBackgroundResource(R.drawable.com_bottombar_r1_unpress);
                iv2.setBackgroundResource(R.drawable.com_bottombar_r2_unpress);
                iv3.setBackgroundResource(R.drawable.com_bottombar_r3_unpress);
                tv4.setTextColor(this.getResources().getColor(R.color.colorGreen));
                tv1.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv2.setTextColor(this.getResources().getColor(R.color.colorDark));
                tv3.setTextColor(this.getResources().getColor(R.color.colorDark));
                fragment = new PersonnalFragment();
                break;
            default:
                break;

        }
        goToFragment();
    }

    private void goToFragment() {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameMain, fragment, "frameMain");
            transaction.commit();
        }
    }
}
