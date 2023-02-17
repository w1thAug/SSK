package model.dto.export;

import model.dto.EsmResultOfType;
import model.dto.SdqResultOfType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.Date;
import java.util.ArrayList;

import static model.dto.export.EsmColumnInfo.*;
import static model.dto.export.EsmRecordColumnInfo.*;
import static model.dto.export.LangColumnInfo.*;
import static model.dto.export.SdqColumnInfo.*;
import static model.dto.export.UserColumnInfo.*;

public class SskExcelByUser {
    private String fileName="default.xlsx";
    private Workbook wb;
    private Sheet sheet;
    private int rowIndex = 0;

    private CellStyle headerCellStyle, bodyCellStyle;

    /*init*/
    public SskExcelByUser(){
        wb = new XSSFWorkbook();
        sheet = wb.createSheet("검사 정보");
        setHeaderCellStyle();
        setBodyCellStyle();
    }

    /*Get FileName*/
    public String getFileName(){
        return fileName;
    }

    /*Get Workbook*/
    public Workbook getWorkBook(){
        return wb;
    }

    /*User data export*/
    public void addUserData(UserExcelDTO userExcelDTO){
        this.fileName = userExcelDTO.getId() + "_" + userExcelDTO.getLoginId() + "_" + userExcelDTO.getName() + "_" + new Date(System.currentTimeMillis()) +"_아동별.xlsx";
        sheet.createRow(rowIndex++);
        Row headerRow = sheet.createRow(rowIndex++);

        for(UserColumnInfo x : UserColumnInfo.getAllColumns()){
            createCellWithStyle(headerRow,x.getColumnIndex(),x.getColumnText(),headerCellStyle);
        }

        Row bodyRow  = sheet.createRow(rowIndex++);
        createCellWithStyleInt(bodyRow, ID.getColumnIndex(), userExcelDTO.getId(), bodyCellStyle);
        createCellWithStyle(bodyRow, NAME.getColumnIndex(), userExcelDTO.getName(), bodyCellStyle);
        createCellWithStyle(bodyRow, LOGIN_ID.getColumnIndex(), userExcelDTO.getLoginId(), bodyCellStyle);
        createCellWithStyle(bodyRow, EMAIL.getColumnIndex(), userExcelDTO.getEmail(), bodyCellStyle);
        createCellWithStyle(bodyRow, BIRTH.getColumnIndex(), userExcelDTO.getBirthStr(), bodyCellStyle);
        createCellWithStyle(bodyRow, GENDER.getColumnIndex(), userExcelDTO.getGenderStr(), bodyCellStyle);

        sheet.setColumnWidth(EMAIL.getColumnIndex(), 2000);
    }

    /*Lang Data Export*/
    public void addLangData(ArrayList<LangExcelDTO> langExcelDTOS){
        sheet.createRow(rowIndex++);
        sheet.createRow(rowIndex++);

        Row headerRow = sheet.createRow(rowIndex++);

        for(LangColumnInfo x : LangColumnInfo.getAllColumns()){
            createCellWithStyle(headerRow,x.getColumnIndex(),x.getColumnText(),headerCellStyle);
            setAutoSizeColumnPlus(sheet,x.getColumnIndex());
        }

        for(int i=0;i<langExcelDTOS.size();i++){
            Row bodyRow  = sheet.createRow(rowIndex++);
            createCellWithStyleInt(bodyRow, LANG_ID.getColumnIndex(), langExcelDTOS.get(i).getId(), bodyCellStyle);
            createCellWithStyle(bodyRow, LANG_DATE.getColumnIndex(), langExcelDTOS.get(i).getDateStr(), bodyCellStyle);
            createCellWithStyle(bodyRow, LANG_AGE_GROUP.getColumnIndex(), langExcelDTOS.get(i).getAgeGroupStr(), bodyCellStyle);
            sheet.autoSizeColumn(LANG_DATE.getColumnIndex());sheet.autoSizeColumn(LANG_AGE_GROUP.getColumnIndex());
            createCellWithStyleInt(bodyRow, LANG_ANSWER1.getColumnIndex(), langExcelDTOS.get(i).getReplyList().get(0), bodyCellStyle);
            createCellWithStyleInt(bodyRow, LANG_ANSWER2.getColumnIndex(), langExcelDTOS.get(i).getReplyList().get(1), bodyCellStyle);
            createCellWithStyleInt(bodyRow, LANG_ANSWER3.getColumnIndex(), langExcelDTOS.get(i).getReplyList().get(2), bodyCellStyle);
            createCellWithStyleInt(bodyRow, LANG_ANSWER4.getColumnIndex(), langExcelDTOS.get(i).getReplyList().get(3), bodyCellStyle);
            createCellWithStyleInt(bodyRow, LANG_ANSWER5.getColumnIndex(), langExcelDTOS.get(i).getReplyList().get(4), bodyCellStyle);
        }
    }

    /*Sdq Data Export*/
    public void addSdqData(ArrayList<SdqExcelDTO> sdqExcelDTOS){
        sheet.createRow(rowIndex++);
        sheet.createRow(rowIndex++);

        Row headerRow = sheet.createRow(rowIndex++);

        for(SdqColumnInfo x : SdqColumnInfo.getAllColumns()){
            createCellWithStyle(headerRow,x.getColumnIndex(),x.getColumnText(),headerCellStyle);
            setAutoSizeColumnPlus(sheet,x.getColumnIndex());
        }

        for(int i=0;i<sdqExcelDTOS.size();i++){
            Row bodyRow  = sheet.createRow(rowIndex++);
            createCellWithStyleInt(bodyRow, SDQ_ID.getColumnIndex(), sdqExcelDTOS.get(i).getId(), bodyCellStyle);
            createCellWithStyle(bodyRow, SDQ_TARGET.getColumnIndex(), sdqExcelDTOS.get(i).getTarget(), bodyCellStyle);
            createCellWithStyle(bodyRow, SDQ_DATETIME.getColumnIndex(), sdqExcelDTOS.get(i).getDateStr(), bodyCellStyle);
            sheet.autoSizeColumn(SDQ_DATETIME.getColumnIndex());
            createCellWithStyleInt(bodyRow, SDQ_ANSWER1.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(0), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER2.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(1), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER3.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(2), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER4.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(3), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER5.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(4), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER6.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(5), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER7.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(6), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER8.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(7), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER9.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(8), bodyCellStyle);
            createCellWithStyleInt(bodyRow, SDQ_ANSWER10.getColumnIndex(), sdqExcelDTOS.get(i).getReplyList().get(9), bodyCellStyle);

            ArrayList<SdqResultOfType> scoreList = sdqExcelDTOS.get(i).getScoreList();

            /*match sdq result and type column*/
            for(int j = 0 ; j<scoreList.size();j++){
                String typeName = scoreList.get(j).getSdqType();
                int columnIndex = SdqColumnInfo.findByColumnText(typeName).getColumnIndex();

                createCellWithStyleInt(bodyRow, columnIndex, scoreList.get(j).getResult(), bodyCellStyle);
                sheet.autoSizeColumn(columnIndex);

            }

        }

    }

    /*Esm Data Export*/
    public void addEsmData(ArrayList<EsmExcelDTO> esmExcelDTOS){
        sheet.createRow(rowIndex++);
        sheet.createRow(rowIndex++);

        Row headerRow = sheet.createRow(rowIndex++);

        for(EsmColumnInfo x : EsmColumnInfo.getAllColumns()){
            createCellWithStyle(headerRow,x.getColumnIndex(),x.getColumnText(),headerCellStyle);
            setAutoSizeColumnPlus(sheet,x.getColumnIndex());
        }

        for(int i=0;i<esmExcelDTOS.size();i++){
            Row bodyRow  = sheet.createRow(rowIndex++);
            createCellWithStyleInt(bodyRow, ESM_ID.getColumnIndex(), esmExcelDTOS.get(i).getId(), bodyCellStyle);
            createCellWithStyle(bodyRow, ESM_DATETIME.getColumnIndex(), esmExcelDTOS.get(i).getDateStr(), bodyCellStyle);
            sheet.autoSizeColumn(ESM_DATETIME.getColumnIndex());
            createCellWithStyleInt(bodyRow, ESM_ANSWER1.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(0), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER2.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(1), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER3.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(2), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER4.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(3), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER5.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(4), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER6.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(5), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER7.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(6), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER8.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(7), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER9.getColumnIndex(), esmExcelDTOS.get(i).getReplyList().get(8), bodyCellStyle);
            createCellWithStyleInt(bodyRow, ESM_ANSWER10.getColumnIndex(),esmExcelDTOS.get(i).getReplyList().get(9), bodyCellStyle);

            ArrayList<EsmResultOfType> scoreList = esmExcelDTOS.get(i).getScoreList();

            /*match sdq result and type column*/
            for(int j = 0 ; j<scoreList.size();j++){
                String typeName = scoreList.get(j).getEsmType();
                int columnIndex = EsmColumnInfo.findColumnByEsmType(typeName).getColumnIndex();

                createCellWithStyleInt(bodyRow, columnIndex, scoreList.get(j).getResult(), bodyCellStyle);
            }

        }
    }

    /*ESM Record Data Export*/
    public void addEsmRecordData(ArrayList<EsmRecordExcelDTO> esmRecordExcelDTOS){
        sheet.createRow(rowIndex++);
        sheet.createRow(rowIndex++);

        Row headerRow = sheet.createRow(rowIndex++);

        for(EsmRecordColumnInfo x : EsmRecordColumnInfo.getAllColumns()){
            createCellWithStyle(headerRow,x.getColumnIndex(),x.getColumnText(),headerCellStyle);
            setAutoSizeColumnPlus(sheet,x.getColumnIndex());
        }

        for(int i=0;i<esmRecordExcelDTOS.size();i++){
            Row bodyRow  = sheet.createRow(rowIndex++);
            createCellWithStyleInt(bodyRow, ESM_RECORD_ID.getColumnIndex(), esmRecordExcelDTOS.get(i).getId(), bodyCellStyle);
            createCellWithStyle(bodyRow, ESM_RECORD_DATETIME.getColumnIndex(), esmRecordExcelDTOS.get(i).getDateStr(), bodyCellStyle);
            sheet.autoSizeColumn(ESM_RECORD_DATETIME.getColumnIndex());
            createCellWithStyle(bodyRow, ESM_RECORD_TEXT.getColumnIndex(), esmRecordExcelDTOS.get(i).getText(), bodyCellStyle);
        }
    }

    public void setAutoSizeColumnPlus(Sheet sheet, int columnIndex){
        sheet.autoSizeColumn(columnIndex);
        sheet.setColumnWidth(columnIndex,sheet.getColumnWidth(columnIndex)+1000);
    }
    public void createCellWithStyle(Row row, int columnIndex, String value, CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    public void createCellWithStyleInt(Row row, int columnIndex, int value, CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    public void createCellWithStyleFloat(Row row, int columnIndex, float value, CellStyle cellStyle){
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }



    /*Set Standard Cell Style - header, body*/
    public void setHeaderCellStyle(){
        headerCellStyle = (XSSFCellStyle) wb.createCellStyle();

        // 배경색 지정
        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 정렬
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 테두리
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
    }

    public void setBodyCellStyle(){
        bodyCellStyle = (XSSFCellStyle) wb.createCellStyle();

        // 정렬
        bodyCellStyle.setAlignment(HorizontalAlignment.CENTER);
        bodyCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 테두리
        bodyCellStyle.setBorderTop(BorderStyle.THIN);
        bodyCellStyle.setBorderBottom(BorderStyle.THIN);
        bodyCellStyle.setBorderLeft(BorderStyle.THIN);
        bodyCellStyle.setBorderRight(BorderStyle.THIN);
    }
}
