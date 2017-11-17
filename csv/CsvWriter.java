package csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//This class write output into a .csv file
public class CsvWriter {
	public void writer(double[] out){
		BufferedWriter bw = null;
		FileWriter fw = null;
		try{
			File file = new File(".\\Data\\output.csv");
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, false);
			bw = new BufferedWriter(fw);
			int i = 0;
			while(i<out.length){
				String data = "";
				data = Double.toString(out[i]) + "," + (i+1) + "\n";
				bw.write(data);
				i++;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
		}
	}
}
