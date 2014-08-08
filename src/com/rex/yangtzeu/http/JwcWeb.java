package com.rex.yangtzeu.http;

import java.util.HashMap;
import java.util.Map;

import com.rex.yangtzeu.Yangtzeu;
import com.rex.yangtzeu.config.Urls;
import com.rex.yangtzeu.regex.JwcRegex;
import com.rex.yangtzeu.sqlite.ComDB;
import com.rex.yangtzeu.utils.EncrypAES;

public class JwcWeb {
	public static boolean jwc_login() {
		try {
			String test_response = YuHttp.get(Urls.jwc_login_page, "gb2312");
			JwcRegex.get_viewstate_keys(test_response);
			
			Map<String,String> data = new HashMap<String,String>();
			data.put("__VIEWSTATE",Yangtzeu.jwc_login_viewstate);
			data.put("__EVENTVALIDATION",Yangtzeu.jwc_login_eventvalidation);
			data.put("txtUid",ComDB.kv_get("student_number"));
			data.put("txtPwd",EncrypAES.decrypt(ComDB.kv_get("student_password")));
			data.put("selKind","1");
			data.put("btLogin", null);
			
			String login_result = YuHttp.post(Urls.jwc_login_page, data, "gb2312", false);
			
			return JwcRegex.login_success(login_result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
