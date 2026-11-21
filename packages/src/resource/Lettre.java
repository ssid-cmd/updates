package resource;

public class Lettre {
	private final String contenu;
	private final String auteur;
	private final String destinataire;

	public Lettre(String contenu, String auteur, String destinataire) {
		this.contenu = contenu;
		this.auteur = auteur;
		this.destinataire = destinataire;
	}

	public String getContenu() {
		return contenu;
	}

	public String getAuteur() {
		return auteur;
	}

	public String getDestinataire() {
		return destinataire;
	}
}
