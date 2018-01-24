package com.zhilin.evaluationapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zhilin.evaluationapp.R;

/**
 * Created by SongCaiBain on 2018/1/9.
 */

public class EvaluateFragment extends Fragment {
    View view;
    String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515479107485&di=ce46bc6b2d9dd2adaff8406e496117ed&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160830%2F5b69f4098880442b829b43dbeb59664f.gif";
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_evaluate, container,false);
        initView();
        return view;
    }

    private void initView() {
       WebView webview=(WebView)view.findViewById(R.id.webview);
        webview.loadUrl(url);
    }
}
