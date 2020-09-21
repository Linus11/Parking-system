package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

	private DBConstants dbConstants;

	public static final int THIRTY_MINUTES = 30; // 30 * 60 * 1000
	public static final int HOUR = 60; // 24 * 60 * 1000
	public static final int TWINTY_FOUR_MINUTES = 45; // 24 * 60 * 60 * 1000

	private TicketDAO ticketDAO;

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}

		int inHour = ticket.getInTime().getHours();
		int outHour = ticket.getOutTime().getHours();

		int inMinutes = ticket.getInTime().getMinutes();
		int outMinutes = ticket.getOutTime().getMinutes();

		// TODO: Some tests are failing here. Need to check if this logic is
		// correct

		int duration = outHour - inHour;
		int duration_in_Minutes = inMinutes - outMinutes;

		String resultSet = DBConstants.RESULT_SET;

		if (resultSet != null) {
			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(100 / (5 * Fare.CAR_RATE_PER_HOUR));
				break;
			}
			case BIKE: {
				ticket.setPrice(100 / (5 * Fare.BIKE_RATE_PER_HOUR));
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}
		}

		if (duration_in_Minutes <= HOUR) {

			if (duration_in_Minutes <= THIRTY_MINUTES) {
				switch (ticket.getParkingSpot().getParkingType()) {
				case CAR: {
					ticket.setPrice(0);
					break;
				}
				case BIKE: {
					ticket.setPrice(0);
					break;
				}
				default:
					throw new IllegalArgumentException("Unkown Parking Type");
				}

			}

			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(0.75 * Fare.CAR_RATE_PER_HOUR);
				break;
			}
			case BIKE: {
				ticket.setPrice(0.75 * Fare.BIKE_RATE_PER_HOUR);
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}

		} else {

			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(duration * Fare.CAR_RATE_PER_HOUR);
				break;
			}
			case BIKE: {
				ticket.setPrice(duration * Fare.BIKE_RATE_PER_HOUR);
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}

		}
	}
}

/*
 * 
 * int duration = 60;
 * 
 * final int THIRTY = 30; final int HOUR = 60; final int DAY = 720; String type
 * = "Bike"; // car or bike double price_bike = 5; double price_car = 7; double
 * price_car_promo = 100 / (price_car * 5); double price_bike_promo = 100 /
 * (price_bike * 5); int recurring = 1; // "1" si le véhicule est déjà présent
 * dans la base // "0" si c'est non" double add_promo_car_if_exist = 0; double
 * add_promo_bike_if_exist = 0;
 * 
 * if (duration <= THIRTY) {
 * 
 * System.out.println("30 minutes");
 * 
 * switch (type) { case "Car": {
 * 
 * System.out.println("c'est un car ! l'entrée est gratuite (-30 minutes) ");
 * break; } case "Bike": {
 * 
 * System.out.println("c'est un bike ! l'entrée est gratuite (-30 minutes) ");
 * break; }
 * 
 * default: break; } } else if (duration <= HOUR) { if (recurring > 0) {
 * add_promo_car_if_exist = price_car_promo; add_promo_bike_if_exist =
 * price_car_promo;
 * 
 * System.out.println("Vous êtes déjà client, promo de 5% appliquée"); }
 * System.out.println("hour"); switch (type) { case "Car": {
 * 
 * System.out.println("c'est un car ! vous payez : " + (price_car -
 * add_promo_car_if_exist)); break; } case "Bike": {
 * 
 * System.out.println("c'est un Bike ! Vous payez : " + (price_bike -
 * add_promo_bike_if_exist)); break; }
 * 
 * default: break; } } else { if (duration <= DAY && duration > HOUR) {
 * 
 * if (recurring > 0) { add_promo_car_if_exist = price_car_promo;
 * add_promo_bike_if_exist = price_car_promo;
 * System.out.println("Vous êtes déjà client, promo de 5% appliquée"); }
 * 
 * System.out.println("Day"); switch (type) { case "Car": {
 * 
 * System.out.println("c'est un Bike ! Vous payez : " + price_car +
 * add_promo_car_if_exist); break; } case "Bike": {
 * 
 * System.out.println("c'est un Bike ! Vous payez : " + price_bike +
 * add_promo_bike_if_exist); break; }
 * 
 * default: break; } } else { // more than 24h System.out.println("+ 24h");
 * 
 * if (recurring > 0) { add_promo_car_if_exist = price_car_promo;
 * add_promo_bike_if_exist = price_car_promo;
 * System.out.println("Vous êtes déjà client, promo de 5% appliquée"); }
 * 
 * switch (type) { case "Car": {
 * 
 * System.out.println("c'est un Bike ! Vous payez : " + price_car +
 * add_promo_car_if_exist); break; } case "Bike": {
 * 
 * System.out.println("c'est un Bike ! Vous payez : " + price_bike +
 * add_promo_bike_if_exist); break; }
 * 
 * default: break; } } }
 * 
 * }
 * 
 * 
 */
*/