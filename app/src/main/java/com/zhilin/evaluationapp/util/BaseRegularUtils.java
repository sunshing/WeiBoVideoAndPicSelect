package com.zhilin.evaluationapp.util;

/**
 * 常用正则表达式
 */
public class BaseRegularUtils {
	//任意字符不为空
	public static final String NO_EMPTY = "NO_EMPTY"; //""[^\\s]{1,}
	//任意字符不为空
	public static final String EMPTY = "";
	//全数字，至少一位
	public static final String NUMBER = "^\\d+$";
	//全字母，至少一位
	public static final String ALPHA = "^[A-Za-z]+$";
	//全字母或数字，至少一位
	public static final String ALPHA_OR_NUMBER = "^[A-Za-z0-9]+$";
	//全字母或数字、汉字
	public static final String ALPHA_OR_ANY = "^[A-Za-z0-9\u4e00-\u9fa5]+$";
	//是否有特殊字符
	public static final String IS_SPECIAL = "[^\\:\\!\"\\#\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/\\:\\;\\<\\=\\>\\?\\@\\[\\\\\\]\\^\\_\\`\\{\\|\\}\\~]*";
	//不能全是数字，或者全是字母，6 - 16位
	//public static final String NUMBER_AND_ALPHA_6_16 = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,16}$";
	//金额，两位小数
	public static final String MONEY = "^\\d+(\\.\\d{1,2})?$";
	//手机号码格式
	public static final String MOBILE_PHONE = "^(13[0-9]|15[0-9]|18[0-9]|14[0-9])\\d{8}$";
	//中国移动手机号码格式
	public static final String MOBILE_PHONE_CHINA_MOBILE = "^(13[4-9]|147|15[012789]|18[78])\\d{8}$";

	private static final String STR_LENGTH = "STR_LENGTH";
	public static String getStrLen(int begin) {
		return "STR_LENGTH{"+begin+",#}";
	}
	
	public static String getStrLen(int begin,int end) {
		return "STR_LENGTH{"+begin+","+end+"}";
	}
	
	/**
	 * 检查给定的内容，是否满足后面的正则表达式，
	 * @param formats 三个三个的传递，1、内容，2、表达式、3、错误提示，也就是必须是3个倍数
	 * @return
	 */
	public static String checkRegularExpression(String... formats) {
		return checkRegularExpressionAll(null,formats);
	}
	
	/**
	 * 检查给定的内容，是否满足后面的正则表达式，
	 * 
	 * @param split 分隔符
	 * @param formats 三个三个的传递，1、内容，2、表达式、3、错误提示，也就是必须是3个倍数
	 * @return
	 */
	public static String checkRegularExpressionAll(String split,String... formats) {
		if (formats.length % 3 != 0) {
			return "调用错误";
		}

		int checkSum = formats.length / 3;

		String msg = "";

		for (int i = 0; i < checkSum; i++) {
			int offset = i * 3;
			String content = formats[offset];
			content = content.trim();

			String re = formats[offset + 1];
			String errorMsg = formats[offset + 2];
			String strMsg = ""; 
			
			if(re.equals(NO_EMPTY)){
				if(content.length()<=0) strMsg = errorMsg;
			}else if(re.startsWith(STR_LENGTH)){
				strMsg = getStrLenMsg(content,re,errorMsg);
			}else{
				if (!content.matches(re)) strMsg = errorMsg;
			}
			
			if (strMsg.length() != 0) {
				if (split==null){
					msg = strMsg;
					break;
				}else{
					if (msg.length() != 0) {
						msg += split;
					}
					msg += strMsg;
				}
			}
		}

		if (msg.length() != 0) {
			return msg;
		} else {
			return null;
		}
	}
	
	private static String getStrLenMsg(String content,String re,String errorMsg){
		re = re.replace(STR_LENGTH+"{", "").replace("}", "");
		String[] rearr = re.split(",");
		int begin = Integer.valueOf(rearr[0]);
		if(content.length()<begin) return errorMsg;
		
		String tmp = rearr[1];
		if(!tmp.equals("#")){
			int end = Integer.valueOf(rearr[1]);
			if(content.length()>end) return errorMsg;
		}
		return "";
	}
}
