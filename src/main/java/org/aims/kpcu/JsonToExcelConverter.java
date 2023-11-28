package org.aims.kpcu;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aims.kpcu.entities.LoanApplication;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author patrick
 *
 */
public class JsonToExcelConverter {




    public static byte[] jsonFileToExcelFile(List<LoanApplication> list) throws IOException {
        try {


            ObjectMapper om = new ObjectMapper();

            om.setDateFormat(Utils.pFormat());
            om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            //Reading the json file
            Map<String,String> fields=new LinkedHashMap<>();
            fields.put("num","Application Id");
            fields.put("member.factory.fname", "Member Factory");
            fields.put("member.num", "Member Number");
            fields.put("member.memberid", "Member ID");
            fields.put("member.memberName", "Member Name");
            fields.put("member.phone", "Member Phone");
            fields.put("member.altphone", "Member Alternate Phone");

//            fields.put("member.trees", "Member Trees");
//            fields.put("member.currentCherry", "Member Current Cherry");
//            fields.put("member.currentMbuni", "Member Current Mbuni");
//            fields.put("member.currentSeason", "Member Current Season");
//            fields.put("member.otheradvances", "Member Other Advances");
            fields.put("amount", "Loan Amount");
            fields.put("bank", "Bank Name");
            fields.put("b_account", "Bank Account Number");
            fields.put("member.loanLimit", "Member Loan Limit");
            fields.put("applicationType", "Application Type");
            fields.put("maker.username", "Maker Username");
            fields.put("madeOn", "Made On Date");
            fields.put("status.response", "Status Response");

            fields.put("status.chairman.username", "Chairman Username");

            fields.put("status.respondedon", "Status Responded On");
            fields.put("status.disbursementStatus", "Status Disbursement Status");


            Set<String> head=fields.keySet();

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Loan Applications");

            // Title (Location) on Row 1
            HSSFRow titleRow = sheet.createRow(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, head.size() - 1));
            HSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Disbursed Loan Applications");

            // Headers on Row 2
            HSSFRow headerRow = sheet.createRow(1);

            int jt=0;
            for(String ips:head)
            {
                HSSFCell headerCell = headerRow.createCell(jt);
                headerCell.setCellValue(fields.get(ips));
                jt++;
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

            for (int i = 0; i < head.size(); i++) {
                headerRow.getCell(i).setCellStyle(r);
            }


            HSSFRow[] row = new HSSFRow[list.size()];
            for (int i = 0; i < list.size(); i++) {
                row[i] = sheet.createRow(i + 2); // Start from Row 3 onwards
                LoanApplication la=list.get(i);
                JSONObject jo=new JSONObject(om.writeValueAsString(la));
                if(i==0)
                    titleCell.setCellValue("Disbursed Loan Applications DisbusmentId "+la.getStatus().getDisbursementStatus().getId()+" On "+Utils.pFormat().format(la.getStatus().getDisbursementStatus().getDoneOn()));

                int j=0;
                for(String hd:head)
                {
                    String val=hd;
                    JSONObject jst=jo;
                    if(val.contains("."))
                    {
                        for(String ind:val.split("\\."))
                        {
                            try {
                                jst = (JSONObject) jo.get(ind);
                            }catch (Exception e){

//                                System.out.println(jo);
//                                System.out.println("getting"+ind);
//                                System.out.println(e);
                                //e.printStackTrace();
                            }
                            val=ind;
                        }

                    }


                    try {
                        //System.out.println("excel" + jst + " getting " + val);
                        String value="";


                        try {
                            value = jst.get(val).toString();
                        }catch (Exception regy){}

                        if(hd.equals("member.factory.fname"))
                            value=la.getMember().getFactory().getFname();

                        if(hd.equals("status.chairman.username"))
                            value=la.getStatus().getChairman().getUsername();

                        try {
                            row[i].createCell(j).setCellFormula(value);
                        } catch (Exception e) {
                            row[i].createCell(j).setCellValue(value);
                        }



                        row[i].getCell(j).setCellStyle(r3);
                    }catch (Exception gdff){}

                    j++;
                }



//                for (int j = 0; j < head.size(); j++) {
//
//
//
//                        try {
//                            row[i].createCell(j).setCellFormula(data[i][j].trim());
//                        } catch (Exception e) {
//                            row[i].createCell(j).setCellValue(data[i][j].trim());
//                        }
//
//
//                    row[i].getCell(j).setCellStyle(r3);
//                }
            }

            for (int i = 0; i < head.size(); i++) {
                sheet.autoSizeColumn(i);
            }



            byte[] excelBytes;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                excelBytes = outputStream.toByteArray();
                return  excelBytes;
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //return new byte[0];
    }
}
