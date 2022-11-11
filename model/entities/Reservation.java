
package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
 	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat formatBR = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {}
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Check-out date must be fater check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	//getters

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	//setters
	
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	//other methods
	
	//type long is a type integer but more than 4 bytes, therefore, obtaining 8 bytes, it means we'll get more integer characters 
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // checkOut/In.getTime() return the milliseconds quantity of that date
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // TimeUnit is a complex enum class with operations that i can use the methods of it to convert milleseconds to days
	}
	
	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		Date now = new Date();
		
		if(checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Reservation check-out must be a date after check-in");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room" 
			+ roomNumber
			+ ", check-in "
			+ formatBR.format(checkIn)
			+ ", check-out: "
			+ formatBR.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
	}
	
	
}
