package com.wangzhixuan.utils;

import com.wangzhixuan.model.PeopleBase;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by sterm on 2016/11/15.
 */
public class WordUtil {
    /**
     * 根据指定的参数值、模板，生成 word 文档
     * @param param 需要替换的变量
     * @param template 模板
     */
    public static CustomXWPFDocument generateWord(Map<String, Object> param, String template) {
        CustomXWPFDocument doc = null;
        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);
            doc = new CustomXWPFDocument(pack);
            if (param != null && param.size() > 0) {

                //处理段落
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);

                //处理表格
                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            List<XWPFParagraph> paragraphListTable =  cell.getParagraphs();
                            processParagraphs(paragraphListTable, param, doc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    /**
     * 处理段落
     * @param paragraphList
     */
    public static void processParagraphs(List<XWPFParagraph> paragraphList,Map<String, Object> param,CustomXWPFDocument doc){
        if(paragraphList != null && paragraphList.size() > 0){
            for(XWPFParagraph paragraph:paragraphList){
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if(text != null){
                        boolean isSetText = false;
                        for (Map.Entry<String, Object> entry : param.entrySet()) {
                            String key = entry.getKey();
                            if(text.indexOf(key) != -1){
                                isSetText = true;
                                Object value = entry.getValue();
                                if (value instanceof String) {//文本替换
                                    text = text.replace(key, value.toString());
                                } else if (value instanceof Map) {//图片替换
                                    text = text.replace(key, "");
                                    Map pic = (Map)value;
                                    int width = Integer.parseInt(pic.get("width").toString());
                                    int height = Integer.parseInt(pic.get("height").toString());
                                    int picType = getPictureType(pic.get("type").toString());
                                    byte[] byteArray = (byte[]) pic.get("content");
                                    ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                                    try {
                                        int ind = doc.addPicture(byteInputStream,picType);
                                        doc.createPicture(ind, width , height,paragraph);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        if(isSetText){
                            run.setText(text,0);
                        }
                    }
                }
            }
        }
    }
    /**
     * 根据图片类型，取得对应的图片类型代码
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType){
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if(picType != null){
            if(picType.equalsIgnoreCase("png")){
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            }else if(picType.equalsIgnoreCase("dib")){
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            }else if(picType.equalsIgnoreCase("emf")){
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            }else if(picType.equalsIgnoreCase("wmf")){
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }
    /**
     * 将输入流中的数据写入字节数组
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(FileInputStream in, boolean isClose){
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(isClose){
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
        return byteArray;
    }

    /**
     * 单元格样式-excel
     * @param workBook
     * @return
     */
    public static XSSFCellStyle setCellStyle(XSSFWorkbook workBook, boolean blob){
        XSSFCellStyle setBorder = workBook.createCellStyle();
        setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        setBorder.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
        XSSFFont font = workBook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 12);//设置字体大小
        if(blob){
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        }
        setBorder.setFont(font);//选择需要用到的字体格式
        return setBorder;
    }


    public static Map<String,Object> PutPhotoIntoWordParameter(String photoPath){

        String headPath = StringUtilExtra.getPictureUploadPath();
        File file=new File(headPath+photoPath);
        Map<String,Object> header = new HashMap<String, Object>();

        if(file.exists()){
            headPath=headPath+photoPath;

            header.put("width", 80);
            header.put("height", 120);
            header.put("type", "jpg");
            try {
                header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream(headPath), true));
            } catch (FileNotFoundException e) {
                return null;
            }
        }else{
            header = null;
        }

        return header;
    }

    public static void OutputWord(HttpServletResponse response, String filePath, String newFileName, Map<String, Object> params) {
        XWPFDocument doc;
        OutputStream os;
        try {
            doc = WordUtil.generateWord(params, filePath);
            response.reset();
            os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + new String(newFileName.getBytes("GBK"), "ISO-8859-1"));
            os.flush();
            doc.write(os);
            os.close();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{

        }
    }


    public static String getCellString(XSSFCell xs) {
        if (xs == null) {
            return "";
        } else {
            return xs.toString();
        }
    }
}