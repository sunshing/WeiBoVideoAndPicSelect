package com.zhilin.evaluationapp.util;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class BaseDialog {
	//------自定义Dialog对话框---------------------------------------------------------
	/**
	 * 获取自定义风格的Dialog
	 * @param context 上下文对象
	 * @param view 自定义view
	 * @return
	 */
	public static Dialog showCustomeDialog(Context context, View view) {
		Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.setCanceledOnTouchOutside(true);
		return dialog;
	}
	
	/**
	 * 获取自定义风格的Dialog
	 * @param context 上下文对象
	 * @param layoutResID 自定义布局Id 
	 * @return
	 */
	public static Dialog showCustomeDialog(Context context, int layoutResID) {
		return showCustomeDialog(context,LayoutInflater.from(context).inflate(layoutResID, null));
	}
	
	//--ProgressDialog 等待对话框----------------------------------------------------------------------------
	
	/**
	 * 圆形等待提示框 无标题
	 * @param context 上下文对象
	 * @param msg 提示内容
	 * @return
	 */
	public static ProgressDialog showProgressDialog(Context context, String msg) {
		return showProgressDialog(context, "", msg, ProgressDialog.STYLE_SPINNER);
	}
	
	/**
	 * 圆形等待提示框
	 * @param context 上下文对象
	 * @param title 标题
	 * @param msg 提示内容
	 * @return
	 */
	public static ProgressDialog showProgressDialog(Context context, String title, String msg) {
		return showProgressDialog(context, title,msg, ProgressDialog.STYLE_SPINNER);
	}
	
	/**
	 * 等待提示框
	 * @param context 上下文对象
	 * @param title 标题
	 * @param msg 提示内容
	 * @param style 显示样式
	 *        ProgressDialog.STYLE_SPINNER为圆形不确定进度条
	 *        ProgressDialog.STYLE_HORIZONTAL为条形进图条
	 * @return
	 */
	public static ProgressDialog showProgressDialog(Context context, String title,String msg, int style) {
		ProgressDialog progress = new ProgressDialog(context,ProgressDialog.THEME_HOLO_LIGHT);
		progress.setMax(100);
		progress.setMessage(msg);
		progress.setCanceledOnTouchOutside(false);
		progress.setCancelable(true);//对话框可以被 返回键 取消
		progress.setIndeterminate(false);
		
		if(BaseStringUtils.isNotBlank(title)) progress.setTitle(title);
		
		switch (style) {
		case ProgressDialog.STYLE_SPINNER:
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			break;
		case ProgressDialog.STYLE_HORIZONTAL:
			progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			break;
		}
		progress.show();
		return progress;
	}
	
	//--AlertDialog对话框----------------------------------------------------------------------------
	public static final int BUTTON_YES = Dialog.BUTTON_NEGATIVE;
	public static final int BUTTON_NEUTRAL = Dialog.BUTTON_NEUTRAL;
	public static final int BUTTON_NO = Dialog.BUTTON_POSITIVE;
	
	/**
	 * 创建用户可选择对话框
	 * @param context 上下文
	 * @param msg 信息内容
	 */
	public static AlertDialog showAlertDialogEasy(Context context, String msg){
		return showAlertDialogEasy(context, "提示信息", msg, "确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}
	
	/**
	 * 创建用户可选择对话框
	 * @param context 上下文
	 * @param title 标题
	 * @param msg 信息内容
	 */
	public static AlertDialog showAlertDialogEasy(Context context, String title, String msg){
		return showAlertDialog(context, title, msg, 0, new String[]{"确定"}, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
	}
	
	/**
	 * 创建用户可选择对话框
	 * @param context 上下文
	 * @param title 标题
	 * @param msg 信息内容
	 * @param btnName 按钮
	 * @param listener 回调
	 */
	public static AlertDialog showAlertDialogEasy(Context context, String title, String msg,String btnName,DialogInterface.OnClickListener listener){
		return showAlertDialog(context, title, msg, 0, new String[]{btnName}, listener);
	}
	
	/**
	 * 创建用户可选择对话框
	 * @param context 上下文
	 * @param title 标题
	 * @param msg 信息内容
	 * @param icon 图标
	 * @param btnNames new String[]{"确定","取消"} 1:Negative 2:Neutral 3:Positive
 	 * @param listener 
 	 * 		  public void onClick(DialogInterface dialog,int which) {
	 * 		  switch (which) {
	 * 		  	case Dialog.BUTTON_POSITIVE:
	 * 		  		MainActivity.this.finish();
	 * 		  		break;
	 * 		  	case Dialog.BUTTON_NEGATIVE:
	 * 		 	    break;
	 * 		  }
	 * 		  dialog.dismiss();
	 * 		  }
	 */
	public static AlertDialog showAlertDialog(Context context, String title, String msg, int icon,
			String[] btnNames, final DialogInterface.OnClickListener listener){
		Builder dialog = new Builder(context,3);
		dialog.setTitle(title).setMessage(msg);
		dialog.setCancelable(false);//对话框不能被 返回键 取消
		if(icon != 0) dialog.setIcon(icon);
		
		if (btnNames!=null && listener!=null){
			if(btnNames.length==1){
				dialog.setPositiveButton(btnNames[0],listener);
			}else if(btnNames.length==2){
				dialog.setNegativeButton(btnNames[0],listener);
				dialog.setPositiveButton(btnNames[1],listener);
			}else if(btnNames.length==3){
				dialog.setNegativeButton(btnNames[0],listener);
				dialog.setNeutralButton(btnNames[1],listener);
				dialog.setPositiveButton(btnNames[2],listener);
			}
		}

		return dialog.show();
	}
	
	//--Toast提示信息----------------------------------------------------------------------------
	public static void toast(Context context, Object obj, int length){
		Toast.makeText(context, obj+"", length).show();
	}
	
	public static void toast(Context context, Object obj){
		toast(context, obj+"", Toast.LENGTH_SHORT);
	}
	
	/**
	 * Toast提示信息
	 * @param context 上下文
	 * @param resId 字符串 资源ID
	 * @param args 替换信息
	 */
    public static void toastFormat(Context context, int resId, Object... args) {
    	toast(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

	/**
	 * Toast提示信息
	 * @param context 上下文
	 * @param resId 字符串 资源ID
	 * @param duration 显示时间 设置
	 * @param args 替换信息
	 */
    public static void toastFormat(Context context, int resId, int duration, Object... args) {
    	toast(context, String.format(context.getResources().getString(resId), args), duration);
    }
    
	/**
	 * Toast提示信息
	 * @param context 上下文
	 * @param format 字符串  免费分钟总数： %1$s分钟  已使用 %2$s分钟  剩余%3$s分钟
	 * @param args 替换信息 
	 */
    public static void toastFormat(Context context, String format, Object... args) {
    	toast(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

	/**
	 * Toast提示信息
	 * @param context 上下文
	 * @param format 字符串  免费分钟总数： %1$s分钟  已使用 %2$s分钟  剩余%3$s分钟
	 * @param duration 显示时间 设置
	 * @param args 替换信息
	 */
    public static void toastFormat(Context context, String format, int duration, Object... args) {
    	toast(context, String.format(format, args), duration);
    }
}
