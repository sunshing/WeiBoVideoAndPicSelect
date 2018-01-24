package com.zhilin.evaluationapp.view;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zhilin.evaluationapp.R;
import com.zhilin.evaluationapp.util.BaseRegularUtils;
import com.zhilin.evaluationapp.util.SharePreferenceMgr;
import com.zhilin.evaluationapp.util.XSYTool;

public class UserLoginActivity extends Activity implements OnClickListener {
    private Context mContext;

    private LinearLayout return_back;
    private EditText login_userphone;
    private LinearLayout login_userphone_del;
    private EditText login_userpwd;
    private LinearLayout login_userpwd_del;
    private Button login_userpwd_code;
    private Button buttonPrimary;

    private ProgressDialog progressDialog;
    boolean ifcheck=true;//默认选中
    SharePreferenceMgr sp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        sp = new SharePreferenceMgr(this);
        initView();
    }

    private void initView() {
        mContext = this;
        return_back = (LinearLayout) findViewById(R.id.return_back);

        login_userphone = (EditText) findViewById(R.id.login_userphone);
        login_userphone_del = (LinearLayout) findViewById(R.id.login_userphone_del);
        login_userpwd = (EditText) findViewById(R.id.login_userpwd);
        login_userpwd_del = (LinearLayout) findViewById(R.id.login_userpwd_del);
        login_userpwd_code = (Button) findViewById(R.id.login_userpwd_code);
        buttonPrimary = (Button) findViewById(R.id.buttonPrimary);
        CheckBox autologin = (CheckBox) findViewById(R.id.autologin);
        Boolean ifcheckState = (Boolean) sp.get("ifcheck", true);
        autologin.setChecked(ifcheckState);
        autologin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ifcheck=true;
                }else{
                    ifcheck=false;
                }
            }
        });

        buttonPrimary.setOnClickListener(this);
        return_back.setOnClickListener(this);
        login_userpwd_code.setOnClickListener(this);

        login_userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) login_userphone_del.setVisibility(View.VISIBLE);
                else login_userphone_del.setVisibility(View.GONE);
            }
        });
        login_userpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) login_userpwd_del.setVisibility(View.VISIBLE);
                else login_userpwd_del.setVisibility(View.GONE);
            }
        });
        login_userphone_del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login_userphone.setText("");
            }
        });
        login_userpwd_del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                login_userpwd.setText("");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPrimary://登录
                login();
                break;
            case R.id.login_userpwd_code://验证码
                checkCode();
                break;
            case R.id.return_back://关闭
                finish();
                break;

        }
    }

    private void login() {
        /*if (login_userphone.getText().toString().length() <= 0) {
            XSYTool.alertMsg(this, "提示信息", "手机号不能空");
            return;
        }
        if (login_userpwd.getText().toString().length() <= 0) {
            XSYTool.alertMsg(this, "提示信息", "验证码不能空");
            return;
        }
        if (!login_userpwd.getText().toString().equals("674522")) {
            XSYTool.alertMsg(this, "提示信息", "验证码错误");
            return;
        }*/

        //保存是否记住密码的值
        sp.put("ifcheck", ifcheck);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void checkCode() {
// 检查输入格式
        String msg = checkPhoneError();
        if (msg != null) {
            XSYTool.alertMsg(this, "提示信息", msg);
            return;
        }
        XSYTool.showToastmsg(mContext, "验证码为：674522");
    }

    private String checkPhoneError() {
        String username = login_userphone.getText().toString().trim();
        return BaseRegularUtils.checkRegularExpression(
                username, BaseRegularUtils.NO_EMPTY, "手机号码不能为空",
                username, BaseRegularUtils.MOBILE_PHONE, "请输入正确的手机号码",
                username, BaseRegularUtils.MOBILE_PHONE, "请检查手机号填写是否正确");
    }
}
