package com.ipin.service.rest.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.ipin.service.rest.constants.Constants;

public class ApplyUtils {
	public static String getStatus(String type, int status) {
		String s = "";
		switch (status) {
		case Constants.APPLY_STATUS_NONE:
			s = "没有" + type;
			break;
		case Constants.APPLY_STATUS_NOT_BEGIN:
			s = type + "未开始";
			break;
		case Constants.APPLY_STATUS_NOT_COMPLETED:
			s = "未完成" + type;
			break;
		case Constants.APPLY_STATUS_COMPLETED:
			s = "已完成" + type;
			break;
		case Constants.APPLY_STATUS_FAILED:
			s = type + "已被刷";
			break;
		case Constants.APPLY_STATUS_SUCCESS:
			s = type + "已通过";
			break;
		case Constants.APPLY_STATUS_CANCEL:
			s = type + "已取消";
			break;
		case Constants.APPLY_STATUS_MISSING:
			s = "已错过" + type;
			break;
		case Constants.APPY_STATUS_NO_CHANCE:
			s = "没机会" + type;
			break;
		default:
			s = "其他";
		}
		return s;
	}

	/**
	 * 判断输入的字符串是否满足时间格式 ： yyyy-MM-dd HH:mm:ss
	 * 
	 * @param patternString
	 *            需要验证的字符串
	 * @return 合法返回 true ; 不合法返回false
	 */
	public static boolean isTimeLegal(String patternString) {
		if (StringUtils.isBlank(patternString)) {
			return false;
		}
		Pattern a = Pattern.compile(
				"^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher b = a.matcher(patternString);
		if (b.matches()) {
			return true;
		} else {
			return false;
		}
	}

	// 是否有效的网申时间
	public static boolean isApplicaitonValidDate(String beginDate, String endDate, int dateFilter)
			throws ParseException {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
			return true;
		}
		if (!ApplyUtils.isTimeLegal(beginDate) || !ApplyUtils.isTimeLegal(endDate)) {
			return false;
		}
		if(dateFilter == Constants.APPLY_DATE_FILTER_365) {
			return true;
		}
		long begin = fmt.parse(beginDate).getTime();
		long end = fmt.parse(endDate).getTime();
		long now = fmt.parse(getNow()).getTime();
		long today = fmt.parse(getToday()).getTime();
		long tomorrow = fmt.parse(getTomorrow()).getTime();
		long week = fmt.parse(getWeek()).getTime();
		long month = fmt.parse(getMonth()).getTime();

		if (dateFilter == Constants.APPLY_DATE_FILTER_0) {
			// 返回已经开始且结束时间在今天内的网申
			if (begin <= now && end >= now && end <= today) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_1) {
			// 返回已经开始且结束时间在明天内的网申
			if (begin <= now && end >= now && end <= tomorrow) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_7) {
			// 返回已经开始且结束时间在7天内的网申
			if (begin <= now && end >= now && end <= week) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_30) {
			// 返回已经开始且结束时间在明天内的网申
			if (begin <= now && end >= now && end <= month) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}

	public static boolean isTeachinValidDate(String d,int dateFilter) throws ParseException {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (StringUtils.isBlank(d)) {
			return true;
		}
		if (!ApplyUtils.isTimeLegal(d)) {
			return false;
		}
		if(dateFilter == Constants.APPLY_DATE_FILTER_365) {
			return true;
		}
		long date = fmt.parse(d).getTime();
		long now = fmt.parse(getNow()).getTime();
		long today = fmt.parse(getToday()).getTime();
		long tomorrow = fmt.parse(getTomorrow()).getTime();
		long week = fmt.parse(getWeek()).getTime();
		long month = fmt.parse(getMonth()).getTime();
		if (dateFilter == Constants.APPLY_DATE_FILTER_0) {
			// 返回已经开始且结束时间在今天内的
			if ( date >= now && date <= today) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_1) {
			// 返回已经开始且结束时间在明天内的
			if ( date >= now && date <= tomorrow) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_7) {
			// 返回已经开始且结束时间在7天内的
			if ( date >= now && date <= week) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_30) {
			// 返回已经开始且结束时间在明天内的
			if (date >= now && date <= month) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}

	public static boolean isExaminationValidDate(String d,int dateFilter) throws ParseException {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (StringUtils.isBlank(d)) {
			return true;
		}
		if (!ApplyUtils.isTimeLegal(d)) {
			return false;
		}
		if(dateFilter == Constants.APPLY_DATE_FILTER_365) {
			return true;
		}
		long date = fmt.parse(d).getTime();
		long now = fmt.parse(getNow()).getTime();
		long today = fmt.parse(getToday()).getTime();
		long tomorrow = fmt.parse(getTomorrow()).getTime();
		long week = fmt.parse(getWeek()).getTime();
		long month = fmt.parse(getMonth()).getTime();
		if (dateFilter == Constants.APPLY_DATE_FILTER_0) {
			// 返回已经开始且结束时间在今天内的
			if ( date >= now && date <= today) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_1) {
			// 返回已经开始且结束时间在明天内的
			if ( date >= now && date <= tomorrow) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_7) {
			// 返回已经开始且结束时间在7天内的
			if ( date >= now && date <= week) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_30) {
			// 返回已经开始且结束时间在明天内的
			if (date >= now && date <= month) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}

	public static boolean isInterviewValidDate(String d, int dateFilter) throws ParseException {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (StringUtils.isBlank(d)) {
			return true;
		}
		if (!ApplyUtils.isTimeLegal(d)) {
			return false;
		}
		if(dateFilter == Constants.APPLY_DATE_FILTER_365) {
			return true;
		}
		long date = fmt.parse(d).getTime();
		long now = fmt.parse(getNow()).getTime();
		long today = fmt.parse(getToday()).getTime();
		long tomorrow = fmt.parse(getTomorrow()).getTime();
		long week = fmt.parse(getWeek()).getTime();
		long month = fmt.parse(getMonth()).getTime();
		if (dateFilter == Constants.APPLY_DATE_FILTER_0) {
			// 返回已经开始且结束时间在今天内的
			if ( date >= now && date <= today) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_1) {
			// 返回已经开始且结束时间在明天内的
			if ( date >= now && date <= tomorrow) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_7) {
			// 返回已经开始且结束时间在7天内的
			if ( date >= now && date <= week) {
				return true;
			} else {
				return false;
			}
		} else if (dateFilter == Constants.APPLY_DATE_FILTER_30) {
			// 返回已经开始且结束时间在明天内的
			if (date >= now && date <= month) {
				return true;
			} else {
				return false;
			}
		} 
		return false;
	}

	public static List<String> listDeleteId(List<String> idList, String _id) {
		if (idList != null && idList.size() > 0) {
			// 删除idList中的_id
			Iterator<String> sListIterator = idList.iterator();
			while (sListIterator.hasNext()) {
				String e = sListIterator.next();
				if (e.equals(_id)) {
					sListIterator.remove();
					break;
				}
			}
		}
		return idList;
	}

	public static List<String> listAddId(List<String> idList, String _id) {
		if (idList == null || idList.size() == 0) {
			List<String> list = new ArrayList<String>();
			list.add(_id);
			return list;
		}
		if (idList != null && idList.size() > 0) {
			// 添加_id到idList中前先判断_id是否已经存在idList中
			Iterator<String> sListIterator = idList.iterator();
			while (sListIterator.hasNext()) {
				String e = sListIterator.next();
				if (e.equals(_id)) {
					return idList;
				}
			}
			idList.add(_id);
		}
		return idList;
	}

	// 
	public static String getNow() {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();
		return fmt.format(now.getTime());
	}

	// 今天晚上23:59:59时间
	public static String getToday() {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 23);
		today.set(Calendar.MINUTE, 59);
		today.set(Calendar.SECOND, 59);
		return fmt.format(today.getTime());
	}

	// 明天天晚上23:59:59时间
	public static String getTomorrow() {
		Calendar current = Calendar.getInstance();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 1);
		tomorrow.set(Calendar.HOUR_OF_DAY, 23);
		tomorrow.set(Calendar.MINUTE, 59);
		tomorrow.set(Calendar.SECOND, 59);
		return fmt.format(tomorrow.getTime());
	}

	// 7天后晚上23:59:59时间
	public static String getWeek() {
		Calendar current = Calendar.getInstance();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar week = Calendar.getInstance();
		week.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH) + 7);
		week.set(Calendar.HOUR_OF_DAY, 23);
		week.set(Calendar.MINUTE, 59);
		week.set(Calendar.SECOND, 59);
		return fmt.format(week.getTime());
	}

	// 30天后晚上23:59:59时间
	public static String getMonth() {
		Calendar current = Calendar.getInstance();
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar month = Calendar.getInstance();
		month.set(Calendar.MONTH, current.get(Calendar.MONTH) + 1);
		month.set(Calendar.HOUR_OF_DAY, 23);
		month.set(Calendar.MINUTE, 59);
		month.set(Calendar.SECOND, 59);
		return fmt.format(month.getTime());
	}
	
	public static Date getSendTime(String sendTime) throws ParseException {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sendT = new Date();
		if(StringUtils.isBlank(sendTime) || !ApplyUtils.isTimeLegal(sendTime)) {
			Calendar c = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			now.set(Calendar.SECOND, c.get(Calendar.SECOND) + 10);
			sendT.setTime(now.getTime().getTime());
		} else {
			sendT = fmt.parse(sendTime);
		}
		Calendar cu = Calendar.getInstance();
		Calendar current = Calendar.getInstance();
		current.set(Calendar.SECOND, cu.get(Calendar.SECOND) + 10);
		return sendT.getTime() < current.getTime().getTime() ? current.getTime() : sendT ;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(getNow());
//		System.out.println(getToday());
//		System.out.println(getTomorrow());
//		System.out.println(getWeek());
//		System.out.println(getMonth());
//		System.out.println(getSendTime(""));

	}

	public static int getCompareProgress(String type1, int status1, String type2, int status2) {
		int t1 = getTypeInt(type1);
		int t2 = getTypeInt(type2);
		if(t1 != t2) {
			return t1 - t2;
		} else {
			return status1 - status2;
		}
	}

	public static int getCompareType(String type1,String type2) {
		int t1 = getTypeInt(type1);
		int t2 = getTypeInt(type2);
			return t1 - t2;
	}
	
	public static int getCompareStatus( int status1, int status2) {
			return status1 - status2;
	}
	
	public static int getCompareString( String str1 , String str2 ) {
		return str1.compareTo(str2);
}


	private static int getTypeInt(String type) {
		int t = 0;
		switch(type) {
		case Constants.APPLY_TYPE_APPLICATION : t = 1; break;
		case Constants.APPLY_TYPE_TEACHIN : t = 2; break;
		case Constants.APPLY_TYPE_EXAMINATION : t = 3; break;
		case Constants.APPLY_TYPR_INTERVIEW : t = 4; break;
		default: t = 0; break;
		}
		return t;
	}

}
