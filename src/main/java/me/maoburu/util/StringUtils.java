package me.maoburu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;

public class StringUtils {
	/**
	 * 将日期格式转换成yyyyMMdd字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	public static String dateTimeToString(Date date) {
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	public static String objToString(Object obj) {
		String str = "";
		if (obj == null) {
			return str;
		}
		str = obj.toString();
		return str;
	}
	
	public static boolean isblank(String str) {
		if ("".equals(str) || str == null) {
			return true;
		}
		return false;
	}
	
	public static String JsonResult(Map map) {
		return new Gson().toJson(map);
	}
}
