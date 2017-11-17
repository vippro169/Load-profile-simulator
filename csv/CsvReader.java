package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
//This class read value from .csv file and pass to a double array
public class CsvReader {
	public List<double[]> csvIN(List<double[]> devices) {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		FileReader fr = null;
		try {
			while (true) {
				double[] curve = new double[1440];
				String text = "";
				System.out.print("Filename: ");
				text = sc.nextLine();
				if(text.equalsIgnoreCase("done")) break;
				fr = new FileReader(text);
				br = new BufferedReader(fr);
				String line;
				for (int i = 0; i < curve.length; i++) {
					curve[i] = 0;
				}
				int i = 0;
				while ((line = br.readLine()) != null) {
					String[] split = line.split("\\,");
					curve[i] = Double.parseDouble(split[0]);
					i++;
				}
				devices.add(curve);
				br.close();
				fr.close();
				sc.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			csvIN(devices);
		} catch (NumberFormatException e) {
			System.out.println("Wrong energy input in CSV file!");
		} catch (IOException e) {
		}
		return devices;

	}
}
