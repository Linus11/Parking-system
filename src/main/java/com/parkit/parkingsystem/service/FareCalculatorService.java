package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

<<<<<<< HEAD
	private DBConstants dbConstants;

	public static final int THIRTY_MINUTES = 1800000; // 30 * 60 * 1000
	public static final int HOUR = 1440000; // 24 * 60 * 1000
	public static final int TWINTY_FOUR_HOURE = 1440000; // 24 * 60 * 60 * 1000
=======
	private TicketDAO ticketDAO;
>>>>>>> 42e1948297fe16c7b78afe652c43f0cee25bcbc6

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}

		int inHour = ticket.getInTime().getHours();
		int outHour = ticket.getOutTime().getHours();

		// TODO: Some tests are failing here. Need to check if this logic is
		// correct
<<<<<<< HEAD
		int duration = outHour - inHour;

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

		if (duration < HOUR) {

			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(0.75 * Fare.CAR_RATE_PER_HOUR);
				break;
			}
			case BIKE: {
				ticket.setPrice(0.75 * Fare.BIKE_RATE_PER_HOUR);
=======

		int duration = outHour - inHour;

		int isSet = 0;
		// isSet = ticketDAO.getNumber_History(ticket.getVehicleRegNumber());

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

		if (isSet > 0) {
			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(duration * Fare.CAR_RATE_PER_5_POUR_CENT);
				break;
			}
			case BIKE: {
				ticket.setPrice(duration * Fare.BIKE_RATE_PER_5_POUR_CENT);
>>>>>>> 42e1948297fe16c7b78afe652c43f0cee25bcbc6
				break;
			}
			default:
				throw new IllegalArgumentException("Unkown Parking Type");
			}
<<<<<<< HEAD

		}

		if (duration < THIRTY_MINUTES) {

=======
		} else if (duration < 30) {
>>>>>>> 42e1948297fe16c7b78afe652c43f0cee25bcbc6
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
<<<<<<< HEAD

		}

		else {

=======
		} else {
>>>>>>> 42e1948297fe16c7b78afe652c43f0cee25bcbc6
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
<<<<<<< HEAD

		}

	}
}
=======
		}

	}
}
>>>>>>> 42e1948297fe16c7b78afe652c43f0cee25bcbc6
