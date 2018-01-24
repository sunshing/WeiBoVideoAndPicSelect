package com.zhilin.evaluationapp.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by SongCaiBain on 2017/12/23.
 */

public class XSYTool {
    private static SweetAlertDialog dia = null;
    public static String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    /**
     * 本地保存成功后，展示在客户端
     */
    public static void setImageView(ImageView iv, String fileName) {
        // 从sd卡获取图片
        String filepath = XSYTool.getSDCardPath() + "/mvpdemo/" + fileName;
        File file = new File(filepath);
        if (file.exists()) {
            Bitmap bm = BitmapFactory.decodeFile(filepath);
            // 将图片显示到ImageView中
            iv.setImageBitmap(bm);
        }
    }




    /**
     * 自定义工具类
     * 显示提示框
     *
     * @param context
     * @param msg
     */
    public static void showToastmsg(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void alertMsg(Context context, String msgTitle, String msgCont) {
        stopDialog();
        dia = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText(msgTitle).setContentText(msgCont).showCancelButton(false);
        Window dialogWindow = dia.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }

    //延迟对号dialog
    public static void delayalertMsg(final Context context, final String msgTitle, final String msgCont) {
        stopDialog();
        dia = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText(msgTitle).setContentText(msgCont).showCancelButton(false);
        Window dialogWindow = dia.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopDialog();
            }
        }, 2000);
    }

    //延迟警告的dialog
    public static void warningalerMsg(final Context context, final String msgTitle, final String msgCont) {
        stopDialog();
        dia = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText(msgTitle).setContentText(msgCont).showCancelButton(false);
        Window dialogWindow = dia.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopDialog();
            }
        }, 2000);

    }

    public static void stopDialog() {
        if (dia != null && dia.isShowing()) {
            dia.dismiss();
        }
    }
}
