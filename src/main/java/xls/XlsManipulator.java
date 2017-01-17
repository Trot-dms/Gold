package xls;

import model.Nbp;
import model.Recommendation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import settings.Lang;
import settings.Settings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by trot on 16.01.17.
 */
public class XlsManipulator {

    private String xlsOutputFileName = Settings.CONGIG.getSaveFileName();
    private String xlsSavePath = Settings.CONGIG.getOutputPath();

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private Recommendation r = new Recommendation();


    public void setXlsOutputFileName(String xlsOutputFileName) {
        this.xlsOutputFileName = xlsOutputFileName;
    }

    public void setXlsSavePath(String xlsSavePath) {
        this.xlsSavePath = xlsSavePath;
    }

    private void saveWorkbook(Workbook workbook) throws IOException {
        try (FileOutputStream stream =
                     new FileOutputStream(xlsSavePath + xlsOutputFileName)) {
            workbook.write(stream);
        }
    }

    public void createXls(List<Nbp> dataList) throws IOException {
        try {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("Arkusz 1");

            int actualRow = 0;
            for (int i = 0; i < dataList.size(); i++) {
                createDateCell(i, 0, dataList.get(i).getData());
                createValueCell(i, 1, dataList.get(i).getCena());
                actualRow = i + 1;
            }

            createValueCell(actualRow, 0, Lang.AVERAGE_FROM_ALL_TIME);
            createNewFormula(actualRow, 1, "AVERAGE(B1:B" + actualRow + ")");
            actualRow++;
            createValueCell(actualRow, 0, String .format(Lang.AVERAGE_FROM_DAYS,r.recommendationDays));
            createValueCell(actualRow, 1, r.recommendationValue);
            actualRow++;
            createValueCell(actualRow, 0, Lang.RECOMMENDATION + r.recommendation);
            actualRow++;
            createValueCell(actualRow, 0, Lang.WARNING_IT_IS_NOT_ADVICE);
        } finally {
            saveWorkbook(workbook);
        }
    }

    private void createDateCell(int rowNumber, int columnNumber, String value) {
        Cell cell;
        cell = getNewCell(rowNumber, columnNumber);
        cell.setCellValue(value);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(workbook.createDataFormat().getFormat(
                DateTimeFormatter.ISO_LOCAL_DATE.toString()));
        cell.setCellStyle(cellStyle);
    }

    private void createValueCell(int rowNumber, int columnNumber, String value) {
        Cell cell = getNewCell(rowNumber, columnNumber);
        cell.setCellValue(value);
    }

    private void createValueCell(int rowNumber, int columnNumber, double value) {
        Cell cell = getNewCell(rowNumber, columnNumber);
        cell.setCellValue(value);
    }

    private void createNewFormula(int rowNumber, int columnNumber, String formulaString) {
        Cell cell = getNewCell(rowNumber, columnNumber);
        cell.setCellFormula(formulaString);
    }

    private Cell getNewCell(int rowNumber, int columnNumber) {
        Cell cell;
        Row row;
        if (sheet.getRow(rowNumber) == null) {
            row = sheet.createRow(rowNumber);
        } else {
            row = sheet.getRow(rowNumber);
        }
        cell = row.createCell(columnNumber);
        return cell;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.r = recommendation;
    }
}
