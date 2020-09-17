package com.parkit.parkingsystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;

public class Promos {
	public static void main(String[] args) {
		Logger logger = LogManager.getLogger("Promos");

		FareCalculatorService fareCalculatorService = new FareCalculatorService();

		InputReaderUtil inputReaderUtil = null;
		ParkingSpotDAO parkingSpotDAO;
		TicketDAO ticketDAO;
		ParkingService parckingService;

		String vehicleRegNumber;
		DataBaseConfig dataBaseConfig = new DataBaseConfig();
		try {
			vehicleRegNumber = "123";

			// récupérer un numéro de véhicule existant dans la base
			Connection con = null;
			Ticket ticket = new Ticket();
			con = dataBaseConfig.getConnection();
			PreparedStatement ps = con.prepareStatement(DBConstants.VEHICLE_REG_NUMBER_RECURRING);

			ps.setString(1, vehicleRegNumber);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.print(rs.getString("VEHICLE_REG_NUMBER"));
				String NbrVehiculeDb = rs.getString("VEHICLE_REG_NUMBER");

				System.out.println("Numéro du véhicule dans la base est : " + NbrVehiculeDb);
				if ((vehicleRegNumber.equals(NbrVehiculeDb))) {
					System.out.println("Welcome back ! Vous avez déjà utilisé notre service !");
				} else {
					System.out.println("Bienvenue dans notre Parcking ! " + vehicleRegNumber);
				}

			}
			System.out.println("pas dans la condition");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
