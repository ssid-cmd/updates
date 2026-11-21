package resource;

public class Station {
	private int passagersEnAtente = 0;
	private boolean busPresent = false;

	// appeler par un passager
	public synchronized void attendreBus() throws InterruptedException {
		// arrivée de passager
		passagersEnAtente++;
		System.out.println(Thread.currentThread().getName() + " attendre bus");

		// tant que le bus n'est pas present, le passager attend
		while (!busPresent) {
			wait();
		}

		// le bus est present, le passager monte dans le bus
		System.out.println(Thread.currentThread().getName() + " monte bus");
		passagersEnAtente--;

		// si tous les passagers sont monte dans le bus, le bus peut partir
		if (passagersEnAtente == 0) {
			notify();
		}

	}

	// appeler par un bus
	public synchronized void chargerPassagers() throws InterruptedException {
		// bus arrive a la station
		busPresent = true;
		System.out.println("le Bus ouvre ses portes");

		// notifier les passager pour monter dans le bus
		notifyAll();

		// tant que il y a encore des passagers, le bus attend
		while (passagersEnAtente > 0) {
			wait();
		}
		// bus quitte la station
		busPresent = false;
		System.out.println("Tous les passagers sont montés dans le bus, le bus peut partir");

	}
}
