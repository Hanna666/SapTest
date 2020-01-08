package framework.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TimeStamp {
	
	
	private static File createFile(XSSFWorkbook wb, String folderTo) throws FileNotFoundException, IOException {
	    //LOG.debug("Create File");
		
	    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	    File f = Paths.get(folderTo, "Results-" + timestamp + ".xlsx").toFile();
	    try (FileOutputStream fos = new FileOutputStream(f)) {
	        wb.write(fos);
	    }
	    return f;
	}

}
