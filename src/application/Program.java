package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.next();
		System.out.println();
		
		List<Sale> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
				
				Sale sale = new Sale(month, year, seller, items, total);
				list.add(sale);
				
				line = br.readLine();
			}
			Set<String> names = new HashSet<>();
			
			for(Sale s1 : list) {
				names.add(s1.getSeller());
			}
			
			System.out.println("Total de vendas por vendedor: ");
			
			for(String name : names) {
				double sum = list.stream()
				.filter(x -> x.getSeller().equals(name))
				.mapToDouble(x -> x.getTotal())
				.sum();
				
				System.out.println(name + " - R$ " + String.format("%.2f", sum));
			}
			
			System.out.println();		
		}
		catch(IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		sc.close();
	}
}
