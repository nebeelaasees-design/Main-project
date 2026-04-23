package Login;


	import java.io.FileInputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtils {

	    private Sheet sheet;

	    public ExcelUtils(String filePath, String sheetName) throws IOException {
	        FileInputStream file = new FileInputStream(filePath);
	        Workbook workbook = new XSSFWorkbook(file);
	        sheet = workbook.getSheet(sheetName);
	        file.close();
	    }

	    public int getRowCount() {
	        return sheet.getPhysicalNumberOfRows();
	    }

	    public String getCellData(int rowIndex, int colIndex) {
	        Row row = sheet.getRow(rowIndex);
	        Cell cell = row.getCell(colIndex);

	        if (cell == null) return "";

	        switch (cell.getCellType()) {
	            case STRING: return cell.getStringCellValue();
	            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
	            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA: return cell.getCellFormula();
	            default: return "";
	        }
	    }
	}


