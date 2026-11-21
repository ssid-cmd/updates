package threads;

import resource.Station;

public class Passager extends Thread {
	private String nom;
	private Station station;
	private int heureArrivee;

	public Passager(String nom, Station station, int heureArrivee) {
		super(nom);
		this.nom = nom;
		this.station = station;
		this.heureArrivee = heureArrivee;
	}

	@Override
	public void run() {
		try {
			sleep(heureArrivee);
			System.out.println(nom + " arrive a la station");
			station.attendreBus();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
