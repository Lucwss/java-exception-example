package teste6;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		SimpleDateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
		
		
		try {
			System.out.print("Room: ");
			Integer room = input.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy) :");
			Date checkIn = formatBR.parse(input.next());
			System.out.print("Check-out date (dd/MM/yyyy) :");
			Date checkOut = formatBR.parse(input.next());
			Reservation res = new Reservation(room, checkIn, checkOut);
			breakLine();
			
			System.out.println("Reservation: " + res);
			
			breakLine();
			
			System.out.print("Would you like to update the reservation ? ");
			Character choice = input.next().charAt(0);
			
			if(choice == 'y' || choice == 'Y') {
				System.out.println("Enter data to update the reservation: ");
	
				System.out.print("Check-in date (dd/MM/yyyy) :");
				checkIn = formatBR.parse(input.next());
				System.out.print("Check-out date (dd/MM/yyyy) :");
				checkOut = formatBR.parse(input.next());	
				res.updateDates(checkIn, checkOut);
				
				System.out.println("Reservation: " + res);
			}
			System.out.println(res);
		} catch(ParseException e) {
			System.out.println("Invalid date format");
		} catch (DomainException e) {
			System.out.println("Error in reservation : " + e.getMessage());
		}
		
		
		System.out.println("End of program");
		input.close();	
	}
	
	public static void breakLine() {
		System.out.println();
	}
}
