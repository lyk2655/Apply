package com.ipin.service.rest.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexUtils.
 * 正则表达式帮助工具
 * 
 * @author zhongyongsheng
 *
 */
public class RegexUtils {

	public static final String NUM_REGEX = "^[0-9]*$";
	
	/**
	 * 是否匹配
	 * @param content 内容
	 * @param regex 正则表达式
	 * @return
	 */
	public static boolean isMatch(String content, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}
	
	/**
	 * 匹配第一个值
	 * @param content 内容
	 * @param groupRegex 正则表达式
	 * @return
	 */
	public static String getFirstMatch(String content, String groupRegex) {
		Pattern pattern = Pattern.compile(groupRegex);
		Matcher matcher = pattern.matcher(content);
		if(matcher.find()) {
			return matcher.group(1);
		}
		
		return null;
	}
	
	
}
