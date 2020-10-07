package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVDataProvider {
    public static Object[][] readCsv(File file) throws IOException {
        Object obj[][] = null;
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);
            // Skipping Header
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();

            List<String[]> allData = csvReader.readAll();

            int rowCount = allData.size();
            System.out.println("Row count= " + rowCount);

            String[] headers = allData.get(0);

            int colCount = headers.length;
            System.out.println("Col count=" + colCount);

            obj = new Object[rowCount][colCount];

            for (int i = 0, j = 0; i < rowCount; i++) {
                while (i < rowCount) {
                    String[] rowData = allData.get(i);
                    for (String cell : rowData) {
                        System.out.print("Row: " + i + "Cell= " + j);
                        obj[i][j] = cell;
                        System.out.println(" | " + obj[i][j]);
                        j++;
                    }
                    i++;
                    j=0;
                }
                j = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // To print 2D array
        System.out.println("CSV Data : " + Arrays.deepToString(obj));
        return obj;
    }

    @DataProvider(name = "credentials")
    public static Object[][] getathleteData() throws IOException {
		File filePath = new File("src/test/resources/NewData.csv");
		Object obj[][] = readCsv(filePath);

		return obj;
	}
}
