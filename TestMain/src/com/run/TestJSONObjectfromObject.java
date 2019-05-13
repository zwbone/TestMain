package com.run;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJSONObjectfromObject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String jsonstr="{'companyFuMinDetailData':{'customerId':'7899881917852095446','processStep':'1'},'businessLicenceData':{'fid':'','fileid':'group1/M00/00/0A/CgpAcFs93nOADT3KAAgEYLuMEnk219.jpg','name':'thumb_900_600_1472514709793.jpg','suffix':'jpg','size':525408},'financialStatementsData':{'fid':'','fileid':'group1/M00/00/0A/CgpAcFs93nWALJZAAAgEYLuMEnk604.jpg','name':'thumb_900_600_1472514709793.jpg','suffix':'jpg','size':525408},'legalPersonPData':{'fid':'','fileid':'group1/M00/00/0A/CgpAcFs93neAO-hnAAY2TEXtYQ0095.jpg','name':'thumb_900_600_1472514708417.jpg','suffix':'jpg','size':407116},'legalPersonNData':{'fid':'','fileid':'group1/M00/00/0A/CgpAcFs93nmAPWn5AAY2TEXtYQ0483.jpg','name':'thumb_900_600_1472514708417.jpg','suffix':'jpg','size':407116},'signContractData':{'fid':'','fileid':'group1/M00/00/0A/CgpAcFs93nyAQ2tWAAXpJSkZ_2U397.jpg','name':'thumb_900_600_1472514694640.jpg','suffix':'jpg','size':387365},'manageArrayData':[],'stockHolderArrayData':[]}";
		JSONObject jsonObj = JSONObject.fromObject(jsonstr);
		System.out.println(jsonObj.optString("companyFuMinDetailData").toString());
		System.out.println(jsonObj.optString("stockHolderArrayData"));
		JSONArray jsonArray = new JSONArray();
		System.out.println(">>>>>"+jsonArray.toString()+"<<<<<<<<<<<");
		
	}

}
