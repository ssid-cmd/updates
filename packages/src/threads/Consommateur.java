package threads;

import java.util.Random;

import resource.BoiteALettre;

public class Consommateur extends Thread {
	private final BoiteALettre boite;
	private final Random alea = new Random();

	public Consommateur(BoiteALettre boite, String nom) {
		super(nom);
		this.boite = boite;
	}

	@Override
	public void run() {
		try {
			sleep(alea.nextInt(500));
			boite.retirer(getName());
		} catch (InterruptedException e) {
			interrupt();
		}
	}
}
