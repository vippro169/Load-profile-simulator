package main;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import csv.AppliancesReader;
import entities.*;
import logic.Simulator;
public class Main {
	public static void main(String[] args) {
		System.out.println("Load Profile Simulator\n");
		System.out.println("Appliances: ");
		AppliancesReader reader = new AppliancesReader();
		List<Appliances> apps = reader.appIN();
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<apps.size(); i++){
			System.out.println(apps.get(i).getName());
		}
		System.out.println("\nPlease input the appliances you want! Type 'end' after the last appliance!");
		List<String> house = new ArrayList<String>();
		String app = "";
		int num = 1;
		while(true){
			System.out.print("Appliance "+num+": ");
			app = sc.nextLine();
			if(app.equals("end")) break;
			int check=0;
			for(int i=0; i<apps.size(); i++){
				if(app.equals(apps.get(i).getName())) {
					house.add(app);
					check++;
					num++;
					break;
				}
			}
			if (check==0) {
				System.out.println("No appliance found! Please re-enter the name!");
			}
			System.out.println();
		}
		new Simulator(apps, house);
		sc.close();
	}
}
