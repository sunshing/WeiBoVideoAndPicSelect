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

public class DynamicsFragment extends Fragment {
    View view;
    String url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515479122121&di=bca5137ef873d84868d7f9c66fbe3ed1&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F20168_3_16%2Fa2ei6i0566732857855.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_dynamics, container,false);
        initView();
        return view;
    }

    private void initView() {
       WebView webview=(WebView)view.findViewById(R.id.webview);
        webview.loadUrl(url);
    }
}
