package logic;

import java.util.List;
import java.util.Random;
import chart.ChartCreator;
import entities.Appliances;
//This class simulate the energy consumption
public class Simulator {
	//This method run the simulation 
	public Simulator (List<Appliances> apps, List<String> house){
		Integer[] result= new Integer[86400];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		for (int i=0; i<house.size(); i++){
			for(int j=0; j<apps.size(); j++){
				if(house.get(i).equals(apps.get(j).getName())){
					List<Integer[]> curves = apps.get(j).getLoad();
					Integer[] app= new Integer[86400];
					for (int a = 0; a < app.length; a++) {
						app[a] = 0;
					}
					if(house.get(i).equals("ccc")) {
						int on[] = {68400};
						System.out.println("Test");
						runTime(app, on, curves);
					}
					else if(house.get(i).equals("coffee")){
						int on[] = {25200};
						System.out.println("Test");
						runTime(app, on, curves);
					}
					else if(house.get(i).equals("kettle")){
						int on[] = {20000};
						runTime(app, on, curves);
					}
					else {
						int on[] = {28800,72000};
						runTime(app, on, curves);
					}
					for(int z=0; z<86400; z++){
						result[z] += app[z];
					}
					new ChartCreator(apps.get(j).getName(),app);
				}
			}
		}
		String title="Energy Consumption";
		ChartCreator chart = new ChartCreator(title,result);
		chart.draw(title, result);
	}
	//This method run an appliance at ON time
	public void runTime(Integer[] app, int on[], List<Integer[]> curves){
		Random rd = new Random();
		for(int b=0; b<on.length; b++){
			int ontime = on[b]-1;
			int load = rd.nextInt(curves.size());
			for(int c=0; c<curves.get(load).length; c++){
				app[ontime] = curves.get(load)[c];
				ontime++;
			}
		}
	}
}
