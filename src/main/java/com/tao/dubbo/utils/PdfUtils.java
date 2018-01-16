package com.tao.dubbo.utils;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.*;
import java.io.*;

/**
 * Created by DELL on 2017/9/22.
 */
public class PdfUtils {

    public static void pdfToDoc(String name1)
            throws IOException
    {
        PDDocument doc = PDDocument.load(new File(name1));
        int pagenumber = doc.getNumberOfPages();

        name1 = name1.substring(0, name1.lastIndexOf("."));

        String dirName = name1;

        String fileName = dirName + ".doc";
        createFile(fileName);
        FileOutputStream fos = new FileOutputStream(fileName);
        Writer writer = new OutputStreamWriter(fos, "UTF-8");
        PDFTextStripper stripper = new PDFTextStripper();

        stripper.setSortByPosition(true);

        stripper.setStartPage(1);
        stripper.setEndPage(pagenumber);
        stripper.writeText(doc, writer);
        writer.close();
        doc.close();
        System.out.println("pdf转换word成功！");
    }

    private static void createDir(String destDirName)
    {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录失败，目标目录已存在！");
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {
            System.out.println("创建目录成功！" + destDirName);
        } else {
            System.out.println("创建目录失败！");
        }
    }

    public static void createFile(String filePath)
    {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("目标文件已存在" + filePath);
        }
        if (filePath.endsWith(File.separator)) {
            System.out.println("目标文件不能为目录！");
        }
        if (!file.getParentFile().exists())
        {
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在的目录失败！");
            }
        }
        try
        {
            if (file.createNewFile()) {
                System.out.println("创建文件成功:" + filePath);
            } else {
                System.out.println("创建文件失败！");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("创建文件失败！" + e.getMessage());
        }
    }

    public static void main(String[] args)
            throws Exception
    {
        String a = "C:\\Users\\DELL\\Desktop\\docker.pdf";


        pdfToDoc(a);
    }
}
