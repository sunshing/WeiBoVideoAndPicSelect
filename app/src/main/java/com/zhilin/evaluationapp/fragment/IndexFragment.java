package com.zhilin.evaluationapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.zhilin.evaluationapp.R;

/**
 * Created by SongCaiBain on 2018/1/9.
 */

public class IndexFragment  extends Fragment {
    View view;
    String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515479304898&di=00755004114725a1a11737627240b575&imgtype=0&src=http%3A%2F%2Fpic32.photophoto.cn%2F20140801%2F0032018056169156_b.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_index, container,false);
        initView();
        return view;
    }

    private void initView() {
       WebView webview=(WebView)view.findViewById(R.id.webview);
        webview.loadUrl(url);
    }
}
