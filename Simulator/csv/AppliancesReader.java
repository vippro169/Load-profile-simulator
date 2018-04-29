package csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entities.Appliances;
//This class read value from .csv file and pass to a double array
public class AppliancesReader {
	public List<Appliances> appIN() {
		List<Appliances> apps = new ArrayList<Appliances>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
				fr = new FileReader("./Data/appliances.csv");
				br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] split = line.split("\\,");
					String name = split[0];
					Integer[] curve = new Integer[split.length-1];
					for(int a=1; a<split.length; a++){
						curve[a-1] = Integer.parseInt(split[a]);
					}
					int check = 0;
					for(int i=0; i<apps.size(); i++){
						if(apps.get(i).getName().equals(name)){
							apps.get(i).getLoad().add(curve);
							check++;
						}
					}
					if(check==0){
						Appliances app = new Appliances(name);
						app.getLoad().add(curve);
						apps.add(app);
					}
				}	
				br.close();
				fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("appliances.csv not found!");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format in file!");
			System.exit(0);
		} catch (IOException e) {
		}
		return apps;
	}
}
