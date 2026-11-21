package threads;

import resource.BoiteALettre;
import resource.Lettre;

public class Producteur extends Thread {
	private final BoiteALettre boite;
	private final Lettre lettre;

	public Producteur(BoiteALettre boite, String nom, Lettre lettre) {
		super(nom);
		this.boite = boite;
		this.lettre = lettre;
	}

	@Override
	public void run() {
		try {
			boite.deposer(lettre);
		} catch (InterruptedException e) {
			interrupt();
		}
	}
}
