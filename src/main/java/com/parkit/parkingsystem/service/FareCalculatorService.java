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