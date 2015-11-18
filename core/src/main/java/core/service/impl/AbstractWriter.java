package core.service.impl;

import core.xls.Column;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Mikhail Boldinov
 */
public abstract class AbstractWriter {
    protected static final int MAX_ROWS_NUMBER = 65535;
    private static final short DEFAULT_ROW_HEIGHT = 300;

    protected String fileName;
    protected Workbook wb;

    protected AbstractWriter(String fileName) {
        this.fileName = fileName;
        wb = new HSSFWorkbook();
    }

    protected Sheet createNewSheet(Workbook wb) {
        Sheet sheet = wb.createSheet();
        writeHeader(sheet);
        return sheet;
    }

    protected abstract void writeHeader(Sheet sheet);

    protected Row createHeader(Sheet sheet, Short rowHeight) {
        return createRow(sheet, 0, rowHeight);
    }

    protected Row createHeader(Sheet sheet) {
        return createRow(sheet, 0);
    }

    protected Row createRow(Sheet sheet, int rowId) {
        return createRow(sheet, rowId, null);
    }

    protected Row createRow(Sheet sheet, int rowId, Short rowHeight) {
        Row row = sheet.createRow(rowId);
        row.setHeight(rowHeight != null ? rowHeight : DEFAULT_ROW_HEIGHT);
        return row;
    }

    protected void addCell(Row row, Column column, String value) {
        addCell(row, column, value, null);
    }

    protected void addCell(Row row, Column column, CellStyle style) {
        addCell(row, column, null, style);
    }

    protected void addCell(Row row, Column column, String value, CellStyle style) {
        Cell cell = row.createCell(column.getNum());
        if (value != null) {
            cell.setCellValue(value);
        }
        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    protected void autoSizeColumnWidth(Sheet sheet, Column column) {
        sheet.autoSizeColumn(column.getNum());
    }

    protected void setColumnWidth(Sheet sheet, Column column, int width) {
        sheet.setColumnWidth(column.getNum(), width);
    }
}
