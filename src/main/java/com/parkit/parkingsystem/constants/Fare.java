package com.parkit.parkingsystem.constants;

public class Fare {

	public static final double BIKE_RATE_PER_HOUR = 1.0;
	public static final double CAR_RATE_PER_HOUR = 1.5;

	public static final double CAR_RATE_PER_5_POUR_CENT = 0.75 * Fare.CAR_RATE_PER_HOUR;
	public static final double BIKE_RATE_PER_5_POUR_CENT = 0.75 * Fare.BIKE_RATE_PER_HOUR;

	public static final double CAR_RATE_PER_24_H_FREE = 24 * Fare.CAR_RATE_PER_HOUR;
	public static final double BIKE_RATE_PER_24_H_FREE = 24 * Fare.BIKE_RATE_PER_HOUR;

}
