package main;

import java.util.Scanner;
import chart.ChartCreator;
import java.util.ArrayList;
import java.util.List;
import csv.*;
//This class is the main class for reading .csv file and plot line chart.
public class mainreader {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double[] out = new double[1440];
		CsvReader input = new CsvReader();
		CsvWriter output = new CsvWriter();
		List<double[]> devices = new ArrayList<double[]>();
		System.out.println("Please insert name of the files. Type 'done' after the last file.");
		input.csvIN(devices);
		while (devices.size() == 0) {
			System.out.println("No input file!");
			input.csvIN(devices);
		}
		System.out.println(devices.size());
		for (int i = 0; i < out.length; i++) {
			int j = 0;
			double energy = 0;
			while (j < devices.size()) {
				energy += devices.get(j)[i];
				j++;
			}
			out[i] = energy;
		}
		System.out.println("Start writing output!");
		output.writer(out);
		System.out.println("Done!");
		sc.close();
		ChartCreator chart = new ChartCreator("Chart", out);
		chart.draw("Chart", out);
	}
}
