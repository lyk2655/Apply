package com.linyk3.apply.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

public class ApplyUtils {
	//检验参数是否为空	
	public static void checkParamsIsNull(Object object) {
		if (object == null) {
//			throw new IllegalArgumentException("[Params failed] - this  argument must be not be null");
		}
	}

	public static void checkString(String str) {
		if(StringUtils.isBlank(str)) {
//			throw new IllegalArgumentException("[Params failed] - this  argument " + str + " must be not be blank");
		}
	}

	public static  <T> boolean isListNotNull(List<T> list) {
		if(list == null || list.size() == 0) {
			return false;
		}
		return true;
	}
	
	public static  <T> boolean isListNull(List<T> list) {
		if(list == null || list.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static List<ObjectId> getObjectIdList(List<String> ids) {
		if(isListNull(ids)) {
			return null;
		}
		List<ObjectId> list = new ArrayList<ObjectId>();
		for(String id : ids) {
			list.add(new ObjectId(id));
		}
		return list;
	}

}
