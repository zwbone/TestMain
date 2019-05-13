package com.run;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJSONObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject completeInfo = new  JSONObject();
		JSONObject registerInfo = new  JSONObject();
		registerInfo.put("userId", "-6422062012622142087");
		registerInfo.put("realAccountNo", "pc1021682");
		registerInfo.put("callFrom", "mobile");
		registerInfo.put("applyTelphone", "13700011113");
		completeInfo.put("registerInfo", registerInfo);
		
		JSONObject uploadFilesJson = new  JSONObject();
		completeInfo.put("uploadFiles", uploadFilesJson);
		
//		JSONObject.fromObject("{'registerInfo':{'userId':'-6422062012622142087','realAccountNo':'pc1021682','callFrom':'mobile','applyPost':'6004','fixphone':'026-87543236','applyUserName':'晃晃','sex':'0','provinceCode':'110000','provinceName':'北京市','cityCode':'110101','cityName':'东城区','addr':'h','applyTelphone':'13700011113'},'uploadFiles':{}}'");
		JSONObject uploadFiles =completeInfo.optJSONObject("uploadFiles");
//		String fileJSONArray = uploadFiles.optString("files");
//		JSONArray fileJSONArray = uploadFiles.getJSONArray("files");
		JSONArray fileJSONArray = uploadFiles.optJSONArray("files");
		if(fileJSONArray != null) {
			System.out.println("fileJSONArray>>>>"+fileJSONArray.toString());
		}
//		System.out.println("fileJSONArray<<<"+fileJSONArray.toString());
	}

}
