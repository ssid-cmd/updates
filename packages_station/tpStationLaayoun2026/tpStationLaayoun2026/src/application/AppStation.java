package application;

import resource.Station;
import threads.Bus;
import threads.Passager;

public class AppStation {

	public static void main(String[] args) {
		// creation de la station
		Station station = new Station();

		// creation de bus
		Bus bus = new Bus(station, 4000);

		//creation des passagers
		Passager [] passagers= {
				                 new Passager("Hibat Allah", station, 1000),
				                 new Passager("Soukaina", station, 2000),
				                 new Passager("Houcien", station, 3000),
				                 new Passager("Salah Eddine", station, 5000),
		                 };
		
		// lancer les threads (bus et les passagers)
		bus.start();
		for (Passager psg : passagers) {
			psg.start();
		}

	}

}
