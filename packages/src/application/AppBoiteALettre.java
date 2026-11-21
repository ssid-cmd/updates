package application;

import resource.BoiteALettre;
import resource.Lettre;
import threads.Consommateur;
import threads.Producteur;

public class AppBoiteALettre {
	public static void main(String[] args) {
		BoiteALettre boite = new BoiteALettre();

		Lettre lettreOmar = new Lettre("Hassan, on se retrouve à la bibliothèque ?", "Omar", "Hassan");
		Lettre lettreSalma = new Lettre("Majda, j'ai retrouvé ton cahier.", "Salma", "Majda");

		Producteur omar = new Producteur(boite, "Omar", lettreOmar);
		Producteur salma = new Producteur(boite, "Salma", lettreSalma);
		Consommateur hassan = new Consommateur(boite, "Hassan");
		Consommateur majda = new Consommateur(boite, "Majda");

		hassan.start();
		majda.start();
		omar.start();
		salma.start();
	}
}
