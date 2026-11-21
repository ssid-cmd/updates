package resource;

public class BoiteALettre {
	private boolean plein = false;
	private Lettre lettre;

	public synchronized void deposer(Lettre lettre) throws InterruptedException {
		while (plein) {
			wait();
		}

		this.lettre = lettre;
		plein = true;
		System.out.println(lettre.getAuteur() + " dépose une lettre pour " + lettre.getDestinataire());
		notifyAll();
	}

	public synchronized void retirer(String nomConsommateur) throws InterruptedException {
		while (!plein || !nomConsommateur.equals(lettre.getDestinataire())) {
			wait();
		}

		System.out.println(nomConsommateur + " lit la lettre de " + lettre.getAuteur() + " : " + lettre.getContenu());
		lettre = null;
		plein = false;
		notifyAll();
	}
}
