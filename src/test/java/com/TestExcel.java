package com;

import com.entity.User;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import com.itextpdf.text.Document;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassNmae TestPoi
 * @Discription
 * @Author 王浩
 * @Date 2020/4/9  11:32
 * @Version 1.0
 */
public class TestExcel {
    @Test
    public void jxlIn() {
        ArrayList<User> users = new ArrayList<>();
        try {
            // 获取本地 Excel 文件输入流，并创建工作薄对象
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("F:\\Offece\\excel\\user.xls"));
            // 获取工作表
            HSSFSheet sheet = workbook.getSheet("用户信息表");
            // 声明行对象
            HSSFRow row = null;
            //注意：获取数据 需排除标题行 从数据行开始读取
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                // 获取当前工作表中的数据行信息 数据行索引从 1 开始
                row = sheet.getRow(i); // 打印结果
                System.out.println( row.getCell(0).getNumericCellValue() + " " + row.getCell(1).getStringCellValue() + " " + row.getCell(2).getDateCellValue()+ " " + row.getCell(3).getDateCellValue());
                Date date = new Date(Double.doubleToLongBits(row.getCell(3).getNumericCellValue()));
                User user = new User((int)row.getCell(0).getNumericCellValue()+"",row.getCell(1).getNumericCellValue()+"",Integer.parseInt(row.getCell(2).getNumericCellValue()+""),date);
                users.add(user);
            }
        } catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); } }
            e.printStackTrace();
        }

        System.out.println( "对象集合 ==》"+users);
    }

    @Test
    public void jxlOut() {
        //创建 Excel 工作薄对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title = {"编号", "姓名", "年龄", "出生年月"};
        //创建单元格对象
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            //i 标示列索引
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //处理日期格式
        HSSFCellStyle cellStyle = workbook.createCellStyle(); //样式对象
        HSSFDataFormat dataFormat = workbook.createDataFormat(); //日期格式
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy 年 MM 月 dd 日 ")); //设置日期格式
        //处理数据行
        for (int i = 1; i < 10; i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue(i);
            row.createCell(1).setCellValue("张三" + i);
            row.createCell(2).setCellValue(i * 2);
            //设置出生年月格式
            cell = row.createCell(3);
            cell.setCellValue(new Date());
            cell.setCellStyle(cellStyle);
        }
        try {
            workbook.write(new File("F:\\Offece\\excel\\user1.xls"));
            workbook.close();
        } catch (
                IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
        }

    }
    @Test
    public void PDF(){
        BaseFont bf = null; //创建字体
        try {
            bf = BaseFont.createFont("C:\\Windows\\Fonts\\STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("字体设置失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font font = new Font(bf, 12); //使用字体
        Document document = new Document();//创建文档
        PdfWriter instance = null;
        try {
            instance = PdfWriter.getInstance(document, new FileOutputStream("F:\\Offece\\file\\first.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("文档出错");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("文件未找到");
        }finally {
            if(instance != null) instance.close();
        }
        document.open();
        try {
            document.add(new Paragraph("hello 你好  ！@ #",font));
        } catch (DocumentException e) {
            e.printStackTrace();
            System.err.println("pdf 导出失败");
        }
        document.close();

        System.err.println("pdf 导出成功");
    }

    @Test
    public void Pdf2() throws Exception{
        String templatePath = "F:\\Offece\\file\\test.pdf";  //模板
        String newPath = "F:\\Offece\\file\\test1.pdf";  //新模板
        FileOutputStream out = new FileOutputStream(newPath); //输出流
        PdfReader reader = new PdfReader(templatePath); //读取pdf模板
        ByteArrayOutputStream bos =  new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, bos);
        AcroFields form = stamper.getAcroFields();
        User user = new User("001","张三",18,new Date());
        form.setField("id",user.getId());
        form.setField("name",user.getName());
        form.setField("age",Integer.toString(user.getAge()));
        form.setField("birth",new SimpleDateFormat("YYYY年MM月DD日").format(new Date()));
        stamper.setFormFlattening(true); //ture 不能编辑  false 可以编辑
        stamper.close();
        Document doc = new Document();
        PdfCopy pdfCopy = new PdfCopy(doc, out);
        doc.open();
        PdfImportedPage importedPage = pdfCopy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
        pdfCopy.addPage(importedPage);
        doc.close();

    }
}
