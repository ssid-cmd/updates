package threads;

import resource.Station;

public class Bus extends Thread {
	private Station station;
	private int heureArrivee;

	public Bus(Station station, int heureArrivee) {
		super();
		this.station = station;
		this.heureArrivee = heureArrivee;
	}

	@Override
	public void run() {
		try {
			sleep(heureArrivee);
			System.out.println("le Bus arrive a la station....");
			station.chargerPassagers();
			System.out.println("le bus quitte la station....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
