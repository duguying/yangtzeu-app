package com.rex.yuol.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class JwcRegex {
	/**
	 * ��ȡ��¼ҳ�ϵ�VIEWSTATE�𣬹���¼ʹ��
	 * 
	 * @param content
	 *            ԭ��ҳ
	 * @return map<s,s>�ṹ�Ĳ�����
	 * @throws Exception ����ƥ��ʧ��
	 */
	public static Map<String, String> get_keys(String content) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String key1 = "", key2 = "";
		String regEx1 = "__VIEWSTATE\" value=\"([/d/D][^\"]+)\"";
		String regEx2 = "__EVENTVALIDATION\" value=\"([/d/D][^\"]+)\"";

		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(content);
		boolean rs1 = mat1.find();
		// ���û��ƥ�䵽
		if (!rs1) {
			throw new Exception("����ƥ��ʧ��!û�ҵ�__VIEWSTATE,__EVENTVALIDATION...");
		}
		key1 = mat1.group(1);
		map.put("viewstate", key1);

		Pattern pat2 = Pattern.compile(regEx2);
		Matcher mat2 = pat2.matcher(content);
		boolean rs2 = mat2.find();
		key2 = mat2.group(1);
		map.put("eventvalidation", key2);

		Log.i("regex", key1 + "==>" + key2);
		return map;
	}
	
	/**
	 * �ж�δ��¼
	 * @param content ����
	 * @return trueδ��¼
	 * @throws Exception ������
	 */
	public static Boolean is_not_login(String content) throws Exception{
		if(content.equals("")){
			throw new Exception("����Ϊ�գ�");
		}
		String regEx1 = "^<script>alert";
		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(content);
		Boolean result = mat1.find();
		return result;
	}
}