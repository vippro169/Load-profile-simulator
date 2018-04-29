package entities;

import java.util.ArrayList;
import java.util.List;
//This class is appliance entity
public class Appliances {
	private String name;
	private List<Integer[]> load = new ArrayList<Integer[]>();
	public Appliances(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Integer[]> getLoad() {
		return load;
	}
	public void setLoad(List<Integer[]> load) {
		this.load = load;
	}
}
