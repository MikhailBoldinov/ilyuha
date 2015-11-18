package description.services;

import core.io.IWriter;
import core.io.impl.AbstractWriter;
import description.beans.DescriptionResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static core.xls.Column.A;
import static core.xls.Column.B;

/**
 * @author Mikhail Boldinov
 */
public class DescriptionWriter extends AbstractWriter implements IWriter<DescriptionResult> {

    public DescriptionWriter(String fileName) {
        super(fileName);
    }

    @Override
    public void write(DescriptionResult descriptionResult) throws IOException {
        Sheet sheet = createNewSheet(wb);
        Map<String, String> resultMap = descriptionResult.getResultMap();
        int currentRow = 1;
        for (String code : resultMap.keySet()) {
            Row row = createRow(sheet, currentRow);
            String result = resultMap.get(code);
            addCell(row, A, code);
            addCell(row, B, result);
            currentRow++;
        }
        autoSizeColumnWidth(sheet, A);
        autoSizeColumnWidth(sheet, B);

        FileOutputStream fileOut = new FileOutputStream(fileName);
        wb.write(fileOut);
        fileOut.close();
    }

    @Override
    protected void writeHeader(Sheet sheet) {
        Row header = createHeader(sheet);
        addCell(header, A, "Код");
        addCell(header, B, "Описание");
        sheet.createFreezePane(0, 1);
    }
}
