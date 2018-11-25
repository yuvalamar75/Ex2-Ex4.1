package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class csv2kml {

	public static void main(String[] args) {
		String csvFile = "WigleWifi_20171201110209.csv";
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			br.readLine();
			
			
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);

				System.out.println("Mac: " + userInfo[0] + " , phone: " + userInfo[1] +
						" Phone: " + userInfo[2] + " Country: " + userInfo[3] );
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}


	}

}
