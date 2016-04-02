package store.file.service;

import receipt.product.*;
import store.exceptions.UnsuccessfullOperationStoreException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cashier.Cashier;
public class StoreFileManager {
	
	public static void saveBooksDatabase(List<Book> books, File filePath) throws FileNotFoundException, IOException {
		
		if(filePath == null) {
			throw new FileNotFoundException("error: incorrect file path");
		}
		
		try(FileOutputStream fileStream = new FileOutputStream(filePath);
				BufferedOutputStream bufferedStream = new BufferedOutputStream(fileStream);
					ObjectOutputStream out = new ObjectOutputStream(bufferedStream)) {
						
			out.writeObject(books);
			
		}
		

	}
	
	@SuppressWarnings("unchecked")
	public static List<Book> loadBooksDatabase(File filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		if(filePath == null) {
			return null;
		}
		
		List<Book> books;
		
		try(ObjectInputStream in = new ObjectInputStream
				(new BufferedInputStream(new FileInputStream(filePath)))) {
			
			books = (List<Book>) in.readObject();
			
		}
		
		return books;
	}
	
	public static List<Cashier> loadCashiersList(File filePath) throws FileNotFoundException, IOException {
		
		if(filePath == null) {
			return null;
		}
		
		List<Cashier> cashiers = new ArrayList<>();
		
		try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
				Scanner sc = new Scanner(inputStream)) {
			
			while(sc.hasNextLine()) {
				
				String[] tokens = sc.nextLine().split("^");
				String cashierName = tokens[0];
				String cashierID = tokens[1];
				
				Cashier cashier = new Cashier(cashierName, cashierID);
				
				if(cashier != null) {
					
					cashiers.add(cashier);
				}
			}
		}
		return cashiers;
	}
	
	public static void saveCashiersList(List<Cashier> cashiers, File filePath) throws UnsuccessfullOperationStoreException, IOException {
		
		if(cashiers == null || filePath == null) {
			throw new UnsuccessfullOperationStoreException("Something wrong with either the cashiers list or the file path!");
		}
		
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
			
			for(Cashier cashier : cashiers) {
				
				String line = cashier.getName() + Cashier.fieldSeparator + cashier.getCashierId() + "\n";
				writer.println(line);
				
			}
		}
 	}
	
	
}


	