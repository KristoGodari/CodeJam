package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;
import utils.ReadFromFile;
import utils.WriteToFile;

public class Main {
	
	static ArrayList<Integer>  numbersNeeded = new ArrayList<Integer>();
	static final String inputFile = "C:\\Users\\Kristo\\workspace\\CodeJam\\src\\files\\input.in";
	static final String outPutFile = "C:\\Users\\Kristo\\workspace\\CodeJam\\src\\files\\output.out";

	public static void main(String[] args) throws IOException {
		
		ReadFromFile readFromFileObject = new ReadFromFile(inputFile);	
		WriteToFile  witeToFile = new WriteToFile(outPutFile);
		
		ArrayList<Integer> startNumbersArray = readFromFileObject.getFileContentAsIntArray();
		int numberOfLines = startNumbersArray.get(0);
		
		for(int i = 1; i <= numberOfLines; i++){
			
			int resultNumber = 0;
			if(startNumbersArray.get(i) != 0){
												
				for(int j = 1; numbersNeeded.size() <10; j++){
					
					resultNumber = startNumbersArray.get(i) * j;
					ArrayList<Integer>  numberDigits =  getNumberDigits(resultNumber);
					
					for(int k = 0; k < numberDigits.size(); k++){					
						if(!checkIfNumberExistsInArray(numberDigits.get(k), numbersNeeded)){
							numbersNeeded.add(numberDigits.get(k));						
						}
					}
				}
				
				//System.out.println("Case #"+i+": " + resultNumber);
				witeToFile.writeToFile("Case #"+i+": " + resultNumber);
				numbersNeeded = new ArrayList<Integer>();
				
			}else{
				
				//System.out.println("Case #"+i+": INSOMNIA");
				witeToFile.writeToFile("Case #"+i+": INSOMNIA");
				numbersNeeded = new ArrayList<Integer>();				
			}			
		}
		witeToFile.closeWriter();
		System.out.println("Done!");
	}
	
	private static ArrayList<Integer> getNumberDigits(int paramNumber){
		int number = paramNumber;		
		ArrayList<Integer>  stack = new ArrayList<Integer>();
		while (number > 0) {
			stack.add(number % 10);
		    number = number / 10;
		}		
		return stack;		
	}	
	
	private static boolean checkIfNumberExistsInArray(int number, ArrayList<Integer> array){		
		boolean contains = array.contains(number);
		return contains;
	}	
}
