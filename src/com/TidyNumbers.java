package com;

import utils.ReadFromFile;
import utils.WriteToFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class TidyNumbers {
	
	static ArrayList<Integer>  numbersNeeded = new ArrayList<Integer>();

	static final String inputFile = "/home/kristo/workspace/CodeJam/src/files/input.in";
	static final String outPutFile = "/home/kristo/workspace/CodeJam/src/files/output.out";

	public static void main(String[] args) throws IOException {

		ReadFromFile readFromFileObject = new ReadFromFile(inputFile);	
		WriteToFile  witeToFile = new WriteToFile(outPutFile);
		
		ArrayList<Long> startNumbersArray = readFromFileObject.getFileContentAsLongArray();
		int numberOfLines = startNumbersArray.get(0).intValue();


		for(int i = 1; i <= numberOfLines; i++){

			long currentNuber = startNumbersArray.get(i);

			long lastTidyNumber = 0;

			for(long k = 1; k <= currentNuber; k++){

				ArrayList<Long>  numberDigits =  getNumberDigits(k);
				if(checkArrayNumbersAreAssending(numberDigits)){
					lastTidyNumber = k;
				}
			}

			witeToFile.writeToFile("Case #"+i+": " + lastTidyNumber);
		}

		witeToFile.closeWriter();
		System.out.println("Done!");
	}
	
	private static ArrayList<Long> getNumberDigits(long paramNumber){
		long number = paramNumber;
		ArrayList<Long>  stack = new ArrayList<Long>();
		while (number > 0) {
			stack.add(number % 10);
		    number = number / 10;
		}

		Collections.reverse(stack);

		return stack;
	}	
	
	private static boolean checkArrayNumbersAreAssending(ArrayList<Long> numbers){

		for (int i = 0; i < numbers.size()-1; i++) {
			if (numbers.get(i) > numbers.get(i+1)) {
				return false;
			}
		}
		return true;
	}
}
