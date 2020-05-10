package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}

		int inHour = ticket.getInTime().getHours();
		int outHour = ticket.getOutTime().getHours();

		// TODO: Some tests are failing here. Need to check if this logic is
		// correct
		int duration = outHour - inHour;

//		// Si la durée est moins de 30 minutes le ticket est gratuit
//		if (duration < 30) {
//			switch (ticket.getParkingSpot().getParkingType()) {
//			case CAR: {
//				ticket.setPrice(0);
//				break;
//			}
//			case BIKE: {
//				ticket.setPrice(0);
//				break;
//			}
//			default:
//				throw new IllegalArgumentException("Unkown Parking Type");
//			}
//
//		} else {

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
		// }
	}
}