package org.aims.kpcu;

import org.aims.kpcu.entities.LoanApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONObject;

import org.apache.poi.hssf.usermodel.*;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.time.ZoneId;
public class Utils {


    public static double gamb(Object i)
    {
        double out=0.0;
        try{
            out=Double.parseDouble(i+"");

        }catch (Exception ignored)
        {

        }
        return  out;
    }

    public static String getString(JSONObject jsonObject, String key) {
        try {
            return (String) jsonObject.get(key);
        } catch (Exception ignored) {
            return ""; // or handle the exception in an appropriate way
        }
    }

    public static Integer getInteger(JSONObject jsonObject, String key) {
        try {
             return Integer.parseInt((String) jsonObject.get(key));
        } catch (Exception ignored) {
            return 0; // or handle the exception in an appropriate way
        }

    }

    public static Long getLong(Object str)
    {
        Long ll= 0L;
        try{
            ll=Long.parseLong(str+"");
        }catch (Exception ed)
        {

        }
        return ll;
    }

    public static String getAdmin()
    {
        return "bazubigman";
    }

    public static String patrickCash(Double k)
    {
        double gh=Math.round(k*100.0);
        int eh=(int) gh;
        String ge;
        if(eh!=0)
        {
            ge=eh+"";
        }
        else
        {
            ge="000";
        }
        String contain="";
        int lenij=ge.length()-2;
        for(int fa=lenij;fa>0;fa--)
        {
            int sisi=lenij-fa;
            if(sisi%3==0&&sisi>0)
            {
                contain=ge.charAt(fa-1)+","+contain;
            }
            else
            {
                contain=ge.charAt(fa-1)+contain;
            }
        }
        contain=contain+"."+ge.charAt(lenij);
        contain=contain+ge.charAt(lenij+1);
        return contain;
    }


    public static String generateUniqueString() {
        long currentTimeMillis = System.currentTimeMillis();
        UUID uuid = new UUID(currentTimeMillis, 0);
        UUID randomUuid = UUID.randomUUID();

        return uuid.toString() + randomUuid.toString();
    }


    public static Date getLocalDate()
    {
        String timezone = "Africa/Nairobi";
        // Get the current date and time in the specified timezone
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of(timezone));

        // Convert ZonedDateTime to Date

        return Date.from(zonedDateTime.toInstant());
    }

    public static String pet(String in)
    {
        if(in==null)
            return "0.00";

        String newl = "";

        // Split the string on the decimal point.
        String[] parts = in.split("\\.");

        // Reverse the part before the decimal point and add commas every three digits.
        for (int a = parts[0].length() - 1; a >= 0; a--)
        {
            newl = parts[0].charAt(a) + newl;
            if ((parts[0].length() - a) % 3 == 0 && a > 0)
                newl = "," + newl;
        }

        // Ensure the part after the decimal point has exactly two decimal places.
        String afterDecimal = "";
        try
        {
            afterDecimal = parts[1].length() >= 2 ? parts[1].substring(0, 2) : parts[1] + "0";
        }
        catch (Exception e)
        {
            System.out.println(e);
            afterDecimal = "00";
        }

        // Add the part after the decimal point to the result.
        newl = newl + "." + afterDecimal;

        return newl;
    }

    public static void Excelss(String[][] data,String[] head, String location) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(location.replaceAll(":","-"));

        // Title (Location) on Row 1
        HSSFRow titleRow = sheet.createRow(0);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, head.length - 1));
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue(location);

        // Headers on Row 2
        HSSFRow headerRow = sheet.createRow(1);
        for (int i = 0; i < head.length; i++) {
            HSSFCell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(head[i]);
        }

        CellStyle r = workbook.createCellStyle();
        r.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
        r.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        r.setBorderTop(BorderStyle.THIN);
        r.setBorderBottom(BorderStyle.THIN);
        r.setBorderLeft(BorderStyle.THIN);
        r.setBorderRight(BorderStyle.THIN);

        CellStyle r3 = workbook.createCellStyle();
        r3.setBorderTop(BorderStyle.THIN);
        r3.setBorderBottom(BorderStyle.THIN);
        r3.setBorderLeft(BorderStyle.THIN);
        r3.setBorderRight(BorderStyle.THIN);

        org.apache.poi.ss.usermodel.Font f = workbook.createFont();
        f.setBold(true);
        r.setFont(f);

        for (int i = 0; i < head.length; i++) {
            headerRow.getCell(i).setCellStyle(r);
        }

        HSSFRow[] row = new HSSFRow[data.length];
        for (int i = 0; i < data.length; i++) {
            row[i] = sheet.createRow(i + 2); // Start from Row 3 onwards
            for (int j = 0; j < head.length; j++) {
                if (j >= 4 && j < head.length - 2) {
                    try {
                        row[i].createCell(j).setCellFormula(data[i][j].trim());
                    } catch (Exception e) {
                        row[i].createCell(j).setCellValue(data[i][j].trim());
                    }
                } else {
                    row[i].createCell(j).setCellValue(data[i][j].trim());
                }
                row[i].getCell(j).setCellStyle(r3);
            }
        }

        for (int i = 0; i < head.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            File exportDirectory = new File("../Desktop/Exports/");
            exportDirectory.mkdirs();

            location = location.replaceAll(":", "-");
            File exportFile = new File("../Desktop/Exports/");// + Utils.getFactory() + location + ".xls");

            try{
                exportFile.getParentFile().mkdirs();

            }catch(Exception e)
            {
                e.printStackTrace();
            }

            FileOutputStream fileOut = new FileOutputStream(exportFile);
            workbook.write(fileOut);
            //JOptionPane.showMessageDialog(null, "Successfully created file \"Exports/" + Utils.getFactory() + location + ".xls\" on the desktop");

            fileOut.close();
            workbook.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            e.printStackTrace();
        }
    }

//    public static List<String> getColumnHeaders(Class<?> clazz) {
//        List<String> columnHeaders = new ArrayList<>();
//        collectColumnHeaders(clazz, columnHeaders);
//        return columnHeaders;
//    }


    public static byte[] convertEntityListToExcel(List<LoanApplication> entityList) throws IOException
    {
        return  JsonToExcelConverter.jsonFileToExcelFile(entityList);
    }

//    public static byte[] convertEntityListToExcel(List<?> entityList) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//
//        // Create cell style with borders
//        CellStyle borderCellStyle = workbook.createCellStyle();
//        borderCellStyle.setBorderTop(BorderStyle.THIN);
//        borderCellStyle.setBorderBottom(BorderStyle.THIN);
//        borderCellStyle.setBorderLeft(BorderStyle.THIN);
//        borderCellStyle.setBorderRight(BorderStyle.THIN);
//
//        // Write column headers
//        Row headerRow = sheet.createRow(0);
//        List<String> columnHeaders = getColumnHeaders(entityList.get(0).getClass());
//        for (int i = 0; i < columnHeaders.size(); i++) {
//            Cell cell = headerRow.createCell(i);
//            cell.setCellValue(columnHeaders.get(i));
//            cell.setCellStyle(borderCellStyle);
//        }
//
//        // Write data rows
//        int rowNum = 1;
//        ObjectMapper objectMapper = new ObjectMapper();
//        for (Object entity : entityList) {
//            Row dataRow = sheet.createRow(rowNum++);
//            writeEntityToRow(entity, dataRow, columnHeaders, objectMapper, borderCellStyle);
//        }
//
//        // Auto-size columns for better readability
//        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        // Convert the Excel data to a byte array
//        byte[] excelBytes;
//        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
//            workbook.write(outputStream);
//            excelBytes = outputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        // Close the workbook to release resources
//        workbook.close();
//
//        return excelBytes;
//    }
//
//    private static void writeEntityToRow(Object entity, Row dataRow, List<String> columnHeaders,
//                                         ObjectMapper objectMapper, CellStyle borderCellStyle) throws IOException {
//        JsonNode node = objectMapper.valueToTree(entity);
//
//        for (int i = 0; i < columnHeaders.size(); i++) {
//            Cell cell = dataRow.createCell(i);
//            String header = columnHeaders.get(i);
//
//            JsonNode fieldNode = node.get(header);
//            if (fieldNode != null) {
//                if (fieldNode.isValueNode()) {
//                    cell.setCellValue(fieldNode.asText());
//                } else {
//                    cell.setCellValue(fieldNode.toString());
//                }
//            }
//            cell.setCellStyle(borderCellStyle);
//        }
//    }


    public static SimpleDateFormat pFormat()
    {
        return new SimpleDateFormat("dd - MMM - yyyy HH:mm:ss");

    }

    public static String getPDate()
    {
        return pFormat().format(getLocalDate());
    }


}
