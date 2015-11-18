package product.services;

import core.service.IWriter;
import core.service.impl.AbstractWriter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import product.beans.Code;
import product.beans.ProductResult;

import java.io.FileOutputStream;
import java.io.IOException;

import static core.xls.Column.A;
import static core.xls.Column.B;
import static core.xls.Column.C;
import static core.xls.Column.D;
import static core.xls.Column.E;
import static core.xls.Column.F;
import static core.xls.Column.G;
import static core.xls.Column.H;
import static core.xls.Column.I;
import static core.xls.Column.J;
import static core.xls.Column.K;
import static core.xls.Column.L;
import static core.xls.Column.M;
import static core.xls.Column.N;
import static core.xls.Column.O;
import static core.xls.Column.P;
import static core.xls.Column.Q;

/**
 * @author Mikhail Boldinov
 */
public class ProductWriter extends AbstractWriter implements IWriter<ProductResult> {

    private static final int EMPTY_COLUMN_WIDTH = 600;
    private static final int SHORT_DESCRIPTION_COLUMN_WIDTH = 8000;
    private static final int DESCRIPTION_COLUMN_WIDTH = 12000;
    private static final short HEADER_HEIGHT = 500;
    private static final short ROW_HEIGHT = 1200;
    private static final String FONT_NAME = "Calibri";
    private static final short FONT_SIZE = 11;

    public ProductWriter(String fileName) {
        super(fileName);
    }

    @Override
    public void write(ProductResult productResult) throws IOException {
        Sheet sheet = createNewSheet(wb);
        CellStyle evenRowStyle = getEvenRowStyle(true);
        CellStyle evenRowStyleNoBorder = getEvenRowStyle(false);
        CellStyle oddRowStyle = getOddRowStyle(true);
        CellStyle oddRowStyleNoBorder = getOddRowStyle(false);
        CellStyle style, styleNoBorder;

        int currentRow = 1;
        for (Code productCode : productResult.getProductCodes()) {
            Row row = createRow(sheet, currentRow, ROW_HEIGHT);
            if (currentRow % 2 == 0) {
                style = evenRowStyle;
                styleNoBorder = evenRowStyleNoBorder;
            } else {
                style = oddRowStyle;
                styleNoBorder = oddRowStyleNoBorder;
            }
            addCell(row, A, productResult.getProducer_serialNumber(), style);
            addCell(row, B, productResult.getModel(productCode.getKey()), style);
            addCell(row, C, styleNoBorder);
            addCell(row, D, productResult.getProducer_model(productCode.getKey()), style);
            addCell(row, E, styleNoBorder);
            addCell(row, F, styleNoBorder);
            addCell(row, G, productResult.getProducer(), style);
            addCell(row, H, styleNoBorder);
            addCell(row, I, styleNoBorder);
            addCell(row, J, styleNoBorder);
            addCell(row, K, styleNoBorder);
            addCell(row, L, styleNoBorder);
            addCell(row, M, styleNoBorder);
            addCell(row, N, styleNoBorder);
            addCell(row, O, productResult.getShortDescription(), style);
            addCell(row, P, productResult.getDescription(productCode.getKey(), productCode.getValue()), style);
            addCell(row, Q, productResult.getImage(), style);
            currentRow++;
            if (currentRow > MAX_ROWS_NUMBER) {
                sheet = createNewSheet(wb);
                currentRow = 1;
            }
        }
        adjustColumnsWidth();

        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
    }

    protected void writeHeader(Sheet sheet) {
        Row header = createHeader(sheet, HEADER_HEIGHT);

        CellStyle style = getHeaderStyle();
        addCell(header, A, "Производитель/Серия", style);
        addCell(header, B, "Модель", style);
        addCell(header, C, style);
        addCell(header, D, "Производитель/модель", style);
        addCell(header, E, style);
        addCell(header, F, style);
        addCell(header, G, "Производитель", style);
        addCell(header, H, style);
        addCell(header, I, style);
        addCell(header, J, style);
        addCell(header, K, style);
        addCell(header, L, style);
        addCell(header, M, style);
        addCell(header, N, style);
        addCell(header, O, "Краткое описание", style);
        addCell(header, P, "Полное описание", style);
        addCell(header, Q, "Картинка", style);

        sheet.createFreezePane(0, 1);
    }

    private void adjustColumnsWidth() {
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            autoSizeColumnWidth(sheet, A);
            autoSizeColumnWidth(sheet, B);
            setColumnWidth(sheet, C, EMPTY_COLUMN_WIDTH);
            autoSizeColumnWidth(sheet, D);
            setColumnWidth(sheet, E, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, F, EMPTY_COLUMN_WIDTH);
            autoSizeColumnWidth(sheet, G);
            setColumnWidth(sheet, H, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, I, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, J, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, K, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, L, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, M, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, N, EMPTY_COLUMN_WIDTH);
            setColumnWidth(sheet, O, SHORT_DESCRIPTION_COLUMN_WIDTH);
            setColumnWidth(sheet, P, DESCRIPTION_COLUMN_WIDTH);
            autoSizeColumnWidth(sheet, Q);
        }
    }

    private CellStyle getHeaderStyle() {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE);
        font.setBold(true);
        font.setItalic(true);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
        return cellStyle;
    }

    private CellStyle getEvenRowStyle(boolean sideBorders) {
        CellStyle cellStyle = getStyle(sideBorders);
        cellStyle.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
        return cellStyle;
    }

    private CellStyle getOddRowStyle(boolean sideBorders) {
        CellStyle cellStyle = getStyle(sideBorders);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        return cellStyle;
    }

    private CellStyle getStyle(boolean sideBorders) {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName(FONT_NAME);
        font.setFontHeightInPoints(FONT_SIZE);
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
        cellStyle.setWrapText(true);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        if (sideBorders) {
            cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        }
        return cellStyle;
    }
}
