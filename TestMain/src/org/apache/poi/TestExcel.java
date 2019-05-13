package org.apache.poi;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestExcel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		exportExcelByBatch(session,response,url,batchSize,param,"����������ϸ",Arrays.asList(ExportConstant.HEAD_BANKPOINTDETAIL),Arrays.asList(ExportConstant.COLUMN_BANKPOINTDETAIL),"����������ϸ");
	}

	/**
	 * ����Excel
	 * @param session
	 * @param response
	 * @param url:����pbq��URL
	 * @param batchSize:ÿһ���������ݵ�����
	 * @param param����ǰ̨����������������
	 * @param exportExcelName������Excel�ı�����
	 * @param headers����ͷ��������
	 * @param columns����ͷӢ��map
	 * @param sheetName��sheet����
	 * @throws PbqAccessException������pbq�쳣
	 */
	public static void exportExcelByBatch(Map<String, String> session,HttpServletResponse response,String url,int batchSize,JSONObject param,String exportExcelName,List<String> headers,List<String> columns,String sheetName) {
		try {
			JSONArray jsonArray = null;
			exportExcelName = new String((exportExcelName + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".xlsx").getBytes(), "ISO8859-1");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=" + exportExcelName);
			OutputStream os = response.getOutputStream();
			
			XSSFWorkbook workbook = new XSSFWorkbook();// ����������
			XSSFSheet sheet = workbook.createSheet();
			for(int i=0;i<headers.size();i++){
				sheet.setColumnWidth(i,5100);
			}
			workbook.setSheetName(0, sheetName);
			XSSFFont font = workbook.createFont();
			font.setColor(Font.COLOR_RED);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			XSSFCellStyle cellStyle = workbook.createCellStyle();// ������ʽ
			cellStyle.setFont(font);
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			XSSFRow row = sheet.createRow((short) 0); // ��һ�� ��ͷ
			for (int i = 0; i < headers.size(); i++) {
				XSSFCell cell = row.createCell(i); // ������1�е�Ԫ��
				cell.setCellValue(headers.get(i));
				cell.setCellStyle(cellStyle);
			}
//			XSSFSheet sheet = ExcelUtilTemplateControlImpl.createSheet(sheetName, workbook,headers);
//			XSSFFont font = ExcelUtilTemplateControlImpl.createFont(workbook);
//			XSSFCellStyle cellStyle = ExcelUtilTemplateControlImpl.createCellStyle(workbook, font);
//			ExcelUtilTemplateControlImpl.disposeHeaderLine(headers, sheet, cellStyle);//ԭ���� private ���͵�disposeHeaderLine��������Ҫ��Ϊpublic
			int i = 0;//Ĭ������������10���
			while(i<100000){
				param.put("pageIndex", i);
				param.put("pageSize", batchSize);
//				jsonArray = doExportJobRequest(session,url,param.toString());//��pbq����һ������
				if(jsonArray.size()==0){
					break;
				}
//				PBQExcelUtil.disposeDataLine(jsonArray, columns,  workbook,  sheet);//ԭ���� private ���͵�disposeDataLine��������Ҫ��Ϊpublic
				XSSFCellStyle cellDataStyle = workbook.createCellStyle();
				cellDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
				if (jsonArray.size()>0) {
					// �����Զ�д�뷽��
					for (int j = 0; j < jsonArray.size(); j++) {	
						XSSFRow rowdata = sheet.createRow(sheet.getLastRowNum() + 1);
						JSONObject obj=jsonArray.getJSONObject(j);
						for (int k = 0; k < columns.size(); k++) {
							XSSFCell celldata = rowdata.createCell((short) k);
							if(obj.containsKey(columns.get(k))){
								String val=obj.getString(columns.get(k));
								if(!"null".equalsIgnoreCase(val)){
									celldata.setCellValue(val);
									celldata.setCellStyle(cellDataStyle);		
								}
							}
						}
					}
				}
				System.out.println("==========================i="+i);
				i++;
			}
			workbook.write(os);
			os.flush();
			try{
				if(os!=null){os.close();}
			}catch(Exception e1){
			}
		} catch (Exception e) {
		}
	}
}
