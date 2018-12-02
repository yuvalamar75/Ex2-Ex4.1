package File_format;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVReaderTest {
	String path="‪‪D:\\test\\WigleWifi_20171201110209.csv";
	ArrayList<String[]> data;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		data=CSVReader.data(path);
		String[] a=data.get(2);
		String actual=a[1];	
		String expected="love";
		assertEquals(expected, actual,"Wrong data");
	
	
	}

}
