package org.apache.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class ExcelUtil{
	
	public static String getCell(Cell cell) {
        if (cell == null){
            return "";
        }
        switch (cell.getCellType()) {
	        case XSSFCell.CELL_TYPE_NUMERIC:    
	        	 //�ж��Ƿ�Ϊ��������
	        	if(DateUtil.isCellDateFormatted(cell)){
		        	//����ת��Ϊ���ڸ�ʽ
		        	Date d = cell.getDateCellValue();
		        	return format(d,"yyyy-MM-dd");
	        	}else{
	        		java.text.DecimalFormat formatter = new java.text.DecimalFormat("########.########");
	                return formatter.format(cell.getNumericCellValue());
	        	}
	        case XSSFCell.CELL_TYPE_STRING:
	            return cell.getStringCellValue();
	        case XSSFCell.CELL_TYPE_FORMULA:
	            return cell.getCellFormula()+"";
	        case XSSFCell.CELL_TYPE_BLANK:
	            return "";
	        case XSSFCell.CELL_TYPE_BOOLEAN:
	            return cell.getBooleanCellValue() + "";
	        case XSSFCell.CELL_TYPE_ERROR:
	            return cell.getErrorCellValue() + "";
	        }
        return "";
    }
	
	private static String format(Date s, String pattern) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern,Locale.US);
			if (s == null)
				return "";
			return formatter.format(s);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static  String insertErrorInfo(int i, String str, String fileName) {
		String errResult = "<font color='red'>" + fileName + ":��" + (i + 1)+ "��" + str + "��</font></br>";
		return errResult;
	}

	public static  boolean checkStringLength(String checkString, int minLength) {
		if (StringUtils.isNotBlank(checkString)) {
			if (checkString.trim().length() > minLength) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static  Date toDate(String date, String pattern) throws Exception {
		if (date == null)
			return null;
		if (date.trim().equals("")){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.US);
		Date date1 = null;
		try {
			date1 = format.parse(date);
		} catch (ParseException e) {

			throw e;
		}
		return date1;
	}
	
	
	/**
	 * ����excel
	 * 
	 * @param datas
	 *            list
	 * @param clazz
	 *            objectClass
	 * @param headers
	 *            exportList
	 * @param sheetName
	 *            sheetName
	 * @return XSSFWorkbook
	 * @throws ParameterErrorException
	 *             ParameterErrorException
	 * 
	 */
	public static XSSFWorkbook createExportWorkbook(List<String> headers,String sheetName)throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();// ����������
		XSSFSheet sheet = createSheet(sheetName, workbook,headers);
		XSSFFont font = createFont(workbook);
		XSSFCellStyle cellStyle = createCellStyle(workbook, font);
		disposeHeaderLine(headers, sheet, cellStyle);
		return workbook;
	}
	
	public static XSSFWorkbook createExcel(JSONArray datas,List<String> headers,List<String> columns,String sheetName)throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();// ����������
		XSSFSheet sheet = createSheet(sheetName, workbook,headers);
		XSSFFont font = createFont(workbook);
		XSSFCellStyle cellStyle = createCellStyle(workbook, font);
		disposeHeaderLine(headers, sheet, cellStyle);
		disposeDataLine(datas, columns,  workbook,  sheet);
		return workbook;
	}
	//create excel with a 2 rows header
	public static XSSFWorkbook createExcel(JSONArray datas,String[][] headers,List<String> columns,String sheetName)throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();// ����������
		XSSFSheet sheet = createSheet(sheetName, workbook, Arrays.asList(headers[0]));
		XSSFFont font = createFont(workbook);
		XSSFCellStyle cellStyle = createCellStyle(workbook, font);
		//create a header with 2 rows
		disposeHeaderLine(headers, sheet, cellStyle);
		disposeDataLine_h(datas, columns,  workbook,  sheet);
		return workbook;
	}
	
	/**
	 * ����sheet
	 * 
	 * @param sheetName
	 *            sheet����
	 * @param workbook
	 *            excel
	 * @return XSSFSheet
	 */
	public static XSSFSheet createSheet(String sheetName, XSSFWorkbook workbook,List<String> headers) {
		XSSFSheet sheet = workbook.createSheet();
		for(int i=0;i<headers.size();i++){
			sheet.setColumnWidth(i,5100);
		}
		workbook.setSheetName(0, sheetName);
		return sheet;
	}
	
	/**
	 * ��������
	 * 
	 * @param workbook
	 *            excel
	 * @return XSSFFont
	 */
	public static XSSFFont createFont(XSSFWorkbook workbook) {
		XSSFFont font = workbook.createFont();
		font.setColor(Font.COLOR_RED);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		return font;
	}
	
	/**
	 * ������ʽ
	 * 
	 * @param workbook workbook
	 * @param font ����
	 * @return XSSFCellStyle
	 */
	public static XSSFCellStyle createCellStyle(XSSFWorkbook workbook,
			XSSFFont font) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();// ������ʽ
		cellStyle.setFont(font);
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		return cellStyle;
	}
	
	 /** �����ͷ
	 * 
	 * @param headers
	 *            ��ͷ
	 * @param sheet
	 *            ��
	 * @param cellStyle
	 *            ��ʽ
	 */
	private static void disposeHeaderLine(List<String> headers,
			XSSFSheet sheet, XSSFCellStyle cellStyle) {
		XSSFRow row = sheet.createRow((short) 0); // ��һ�� ��ͷ
		for (int i = 0; i < headers.size(); i++) {
			XSSFCell cell = row.createCell(i); // ������1�е�Ԫ��
			cell.setCellValue(headers.get(i));
			cell.setCellStyle(cellStyle);
		}
	}
	/**
	 * create a header with 2 rows
	 * @param headers
	 * @param sheet
	 * @param cellStyle
	 */
	private static void disposeHeaderLine(String[][] headers,
			XSSFSheet sheet, XSSFCellStyle cellStyle) {
		//the 1st row
		XSSFRow row1 = sheet.createRow((short) 0);
		for (int i = 0; i < headers[0].length; i++) {
			XSSFCell cell = row1.createCell(i);
			cell.setCellValue(headers[0][i]);
			cell.setCellStyle(cellStyle);
		}
		List<Integer> mergeColIdx = new ArrayList<Integer>();
		int colIdx = 0;
		boolean mergeFlag = true;
		for(String col : headers[0]){
			if("".equals(col)){
				if(mergeFlag){
					mergeColIdx.add(colIdx);
					mergeFlag = false;
				}
			}else{
				if(!mergeFlag){
					mergeColIdx.add(colIdx);
					mergeFlag = true;
				}
			}
			colIdx++;
		}
		if(!mergeFlag){
			mergeColIdx.add(headers[0].length-1);
		}
		for(int i=0;i<mergeColIdx.size();i+=2){
			sheet.addMergedRegion(new CellRangeAddress(0,(short)0,mergeColIdx.get(i)-1,mergeColIdx.get(i+1)-1));
		}
		
		//the 2nd row
		XSSFRow row2 = sheet.createRow((short) 1);
		for (int i = 0; i < headers[1].length; i++) {
			XSSFCell cell2 = row2.createCell(i);
			cell2.setCellValue(headers[1][i]);
			cell2.setCellStyle(cellStyle);
			if("".equals(headers[1][i])){
				sheet.addMergedRegion(new CellRangeAddress(0,(short)1,i,i));
			}
		}
	}
	/**
	 * ׷��ģʽappend ��������
	 * @param datas
	 * @param columns
	 * @param workbook
	 * @param sheet
	 */
	private static void disposeDataLine(JSONArray datas, List<String> columns, XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFCellStyle cellDataStyle = workbook.createCellStyle();
		cellDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		if (datas.size()>0) {
			// �����Զ�д�뷽��
			for (int j = 0; j < datas.size(); j++) {	
				XSSFRow rowdata = sheet.createRow(sheet.getLastRowNum() + 1);//��ȡ��ǰ��������һ�е�����
				JSONObject obj=datas.getJSONObject(j);
				for (int k = 0; k < columns.size(); k++) {
					XSSFCell celldata = rowdata.createCell((short) k);
					if(obj.containsKey(columns.get(k))){
						String val=obj.getString(columns.get(k));
						if(isNull(val)){
							celldata.setCellValue(val);
							celldata.setCellStyle(cellDataStyle);		
						}
					}
				}
			}
		}
	}
	
	
	/***************************
	 * columMap ��Ҫ��������,��ͷ����
	 * 
	 * **************************/
	
	private static void disposeDataLine_h(JSONArray datas, List<String> columns,   XSSFWorkbook workbook, XSSFSheet sheet) {
		XSSFCellStyle cellDataStyle = workbook.createCellStyle();
		cellDataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		if (datas.size()>0) {
			// �����Զ�д�뷽��
			for (int j = 0; j < datas.size(); j++) {	
				XSSFRow rowdata = sheet.createRow(j + 2);
				JSONObject obj=datas.getJSONObject(j);
				for (int k = 0; k < columns.size(); k++) {
					XSSFCell celldata = rowdata.createCell((short) k);
					if(obj.containsKey(columns.get(k))){
						String val=obj.getString(columns.get(k));
						if(isNull(val)){
							celldata.setCellValue(val);
							celldata.setCellStyle(cellDataStyle);		
						}
					}
				}
			}
		}
	}
	
	
	private static boolean isNull(String str){
		
		if("null".equalsIgnoreCase(str)||StringUtils.isBlank(str)){
			
			return false;
		}else{
			
			return true;
		}
		
	}
	 
	/**
	 * ����excel
	 * 
	 * @param exportFileName
	 *            �����ļ�����
	 * @param response
	 *            response
	 * @param workbook
	 *            ��Ҫ������excel
	 * @throws ObjectErrorException
	 *             ObjectErrorException
	 */
	public static void downloadFile(String exportFileName,
			HttpServletResponse response, XSSFWorkbook workbook) {
		OutputStream os = null;
		try {
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String((exportFileName
									+ new SimpleDateFormat(
											"yyyy-MM-dd_HH-mm-ss")
											.format(new Date()) + ".xlsx")
									.getBytes(), "ISO8859-1"));
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(os!=null){os.close();}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * ����excel(��ȷ����)
	 * 
	 * @param exportFileName
	 *            �����ļ�����
	 * @param response
	 *            response
	 * @param workbook
	 *            ��Ҫ������excel
	 * @throws ObjectErrorException
	 *             ObjectErrorException
	 */
	public static void downloadFileOnlyMin(String exportFileName,
			HttpServletResponse response, XSSFWorkbook workbook) {
		OutputStream os = null;
		try {
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String((exportFileName
									+ new SimpleDateFormat(
											"yyyyMMddHHmm")
											.format(new Date()) + ".xlsx")
									.getBytes(), "ISO8859-1"));
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(os!=null){os.close();}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	
	public static void downloadFile2(String exportFileName,
			HttpServletResponse response, XSSFWorkbook workbook) {
		OutputStream os = null;
		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String((exportFileName
									+ new SimpleDateFormat(
											"yyyy-MM-dd_HH-mm-ss")
											.format(new Date()) + ".xlsx")
									.getBytes(), "ISO8859-1"));
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(os!=null){os.close();}
			}catch(Exception e1){
				e1.printStackTrace();
			}
		}
	}
	public static void downloadFile(HttpServletResponse response, File file) {
		try {
			if(!file.exists()||file.isDirectory())
				throw new FileNotFoundException();
			InputStream inStream = new FileInputStream(file);
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ new String(file.getName().getBytes(), "ISO8859-1"));
			byte[] b = new byte[1024];
	        int len;
            while ((len = inStream.read(b)) > 0) {
				outputStream.write(b, 0, len);
			}
            outputStream.close();
            inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������� ����Excel
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
	public void exportExcelByBatch(Map<String, String> session,HttpServletResponse response,String url,int batchSize,JSONObject param,String exportExcelName,List<String> headers,List<String> columns,String sheetName) {
		try {
			JSONArray jsonArray = null;
			exportExcelName = new String((exportExcelName + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()) + ".xlsx").getBytes(), "ISO8859-1");
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=" + exportExcelName);
			OutputStream os = response.getOutputStream();
			
			XSSFWorkbook workbook = new XSSFWorkbook();// ����������
			XSSFSheet sheet = createSheet(sheetName, workbook,headers);
			XSSFFont font = createFont(workbook);
			XSSFCellStyle cellStyle = createCellStyle(workbook, font);
			disposeHeaderLine(headers, sheet, cellStyle);//
			int i = 0;//Ĭ������������10���
			while(i<100000){
				param.put("pageIndex", i);
				param.put("pageSize", batchSize);
				if(jsonArray.size()==0){
					break;
				}
				disposeDataLine(jsonArray, columns,  workbook,  sheet);//ԭ���� private ���͵�disposeDataLine��������Ҫ��Ϊpublic
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

