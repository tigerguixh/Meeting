package cn.mmt.app.http;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import cn.mmt.app.common.Common_Info;

public class ResolveJson {
	/**
	 * 解析手机发送验证码数据
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static List<String> getPhoneMessage(String jsonData) throws Exception{
		List<String> list = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject(jsonData);
		list.add(jsonObject.getString(Common_Info.commandID));
		list.add(jsonObject.getString(Common_Info.code));
		JSONObject JSONObject2 = jsonObject.getJSONObject(Common_Info.data);
		list.add(JSONObject2.getString(Common_Info.result));
		list.add(JSONObject2.getString(Common_Info.message));
		return list;
	}
	/**
	 * 解析手机登陆信息
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public static List<String> getRegisterMessage(String jsonData) throws Exception{
		List<String> list = new ArrayList<String>();
		JSONObject jsonObject = new JSONObject(jsonData);
		list.add(jsonObject.getString(Common_Info.commandID));
		list.add(jsonObject.getString(Common_Info.code));
		JSONObject JSONObject2 = jsonObject.getJSONObject(Common_Info.data);
		list.add(JSONObject2.getString(Common_Info.result));
		return list;
	}
}
