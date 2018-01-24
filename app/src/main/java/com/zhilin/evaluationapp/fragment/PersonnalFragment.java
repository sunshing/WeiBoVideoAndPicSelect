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

public class PersonnalFragment extends Fragment {
    View view;
    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515479142492&di=4ba1aea66bd424a0a847246f9e8c4088&imgtype=0&src=http%3A%2F%2Fwww.945enet.com.tw%2Fepaper%2Fcontents%2Fen%2Fimages%2Fpic06.gif";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        initView();
        return view;
    }

    private void initView() {
        WebView webview = (WebView) view.findViewById(R.id.webview);
        webview.loadUrl(url);
    }
}
