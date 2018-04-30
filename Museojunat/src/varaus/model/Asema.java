package varaus.model;

import java.util.ArrayList;


public class Asema {
	String nimi;
	public ArrayList<Yhteys> yhteydet;
	public ArrayList<Reitti> reitit;
	
	
	// Konstruktori
	public Asema (String nimi) {
		this.nimi = nimi;
		yhteydet = new ArrayList<Yhteys>();
		reitit = new ArrayList<Reitti>();
	}
	
	public String getNimi() {
		return this.nimi;
	}
	
	public String getReitinNimi(int indeksi) {
		return this.reitit.get(indeksi).getReitinNimi();
	}
	
	public String getReitinAsemat(int indeksi){
		return this.reitit.get(indeksi).getReitinAsemat();
	}
	
	//Lis‰‰ kummankin aseman yhteydet-listaan n‰iden asemien v‰lisen yhteyden
	public void lisaaYhteys(Asema kohde, int matka){
		this.yhteydet.add(new Yhteys (kohde, matka));
		kohde.yhteydet.add(new Yhteys(this, matka));
	}
	
	// Poistaa kaikki yhteydet t‰lt‰ asemalta paramterina annetulle asemalle
	public void poistaYhteys(String poistettavanNimi){
		for (Yhteys tutkittava : this.yhteydet) {
			if (tutkittava.getKohde().getNimi() == poistettavanNimi) {
				this.yhteydet.remove(tutkittava);
			}
		}
	}
	
	public void luoReitit(Asema lahtoasema) {
		// Onko asema p‰‰te- tai haara-asema?
		// Asema ON p‰‰te tai haara:
		if (lahtoasema.onkoPaatepysakki() || lahtoasema.onkoHaara()) {
			//K‰yd‰‰n l‰pi yhteydet ja luodaan kullekin oma reitti
			for (int i = 0; i < lahtoasema.yhteydet.size(); i++) {
				// Luodaan aseman reitit-listaan uusi reitti, sen indeksi on sama kuin yhteyden, jonka suuntaan reitti l‰htee
				lahtoasema.reitit.add(new Reitti(lahtoasema));
			}
			//K‰yd‰‰n luodut reitit yksitellen l‰pi ja lis‰t‰‰n niihin reitin asemat
			for (int i = 0; i < lahtoasema.reitit.size(); i++) {
				// Apuviittaukset
				Asema tarkasteltava = lahtoasema;
				Asema kohdeasema = lahtoasema.yhteydet.get(i).getKohde();
				Asema edellinen = lahtoasema; // T‰m‰n avulla varmistetaan ett‰ ei l‰hdet‰ taaksep‰in vahingossa
				
				// Lis‰t‰‰n l‰htˆasema reitille
				lahtoasema.reitit.get(i).lisaaAsema(lahtoasema, lahtoasema.yhteydet.get(i));
				// Lis‰t‰‰n muut asemat, kunnes vastaan tulee p‰‰te tai haara
				while (!kohdeasema.onkoPaatepysakki() && !kohdeasema.onkoHaara()) {
					// Lis‰t‰‰n kohdeasema l‰htoaseman reittiin
					lahtoasema.reitit.get(i).lisaaAsema(kohdeasema, tarkasteltava.yhteydet.get(i));
					//Siirryt‰‰n reitill‰ eteenp‰in
					edellinen = tarkasteltava;
					tarkasteltava = kohdeasema;
					kohdeasema = tarkasteltava.yhteydet.get(0).getKohde();
					//Jos on valittu taaksep‰in viev‰ yhteys, vaihdetaan
					if (kohdeasema == edellinen) {
						kohdeasema = tarkasteltava.yhteydet.get(1).getKohde();
					}
				}
			}
		// Jos asema ei ole p‰‰te tai haara vaan v‰liasema, ei reittej‰ luoda.
		}
	}
		
		
	public boolean onkoPaatepysakki(){
		if (this.yhteydet.size()<2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean onkoHaara(){
		if (this.yhteydet.size()>2) {
			return true;
		}
		else {
			return false;
		}
	}
	

	
	// Sis‰luokat Yhteys ja Reitti:
	
	// Yhteys pit‰‰ sis‰ll‰‰n tiedon yhteyden toisessa p‰‰ss‰ olevasta asemasta ja matkasta kohteen ja yhteys-olion "omistajan v‰lill‰".
	private class Yhteys{
		private Asema kohde;
		private int matka;
		
		public Yhteys(Asema kohde, int matka) {
			this.kohde = kohde;
			this.matka = matka;
		}

		public Asema getKohde() {
			return kohde;
		}

		public int getMatka() {
			return matka;
		}
	} // Yhteys
	
	
	// Reitti-luokka, joka pit‰ sis‰ll‰‰ tietyn junareitin asemat ArrayListiss‰ (ja Stringiss‰) 
	private class Reitti {
		Asema lahtoAsema;
		ArrayList<Asema> reitinAsemat;
		int matkanPituus;
		String nimi;
		String reitti;
		
		// Konstruktori
		public Reitti(Asema lahtoasema) {
			this.lahtoAsema = lahtoasema;
			reitinAsemat = new ArrayList<Asema>();
			matkanPituus = 0;
			nimi = "Nimetˆn reitti";
			reitti = "";
		}
		
		// Palauttaa reitin asemat Stringin‰
		private String getReitinAsemat() {
			return this.reitti;
		}
		
		// Palauttaa reitin l‰htˆ ja p‰‰teaseman eli reitin "nimen"
		private String getReitinNimi() {
			return this.nimi;
		}
		
		// Lis‰t‰‰n reitille uusi asema, nimet‰‰n reitti samalla periaatteella "Ensimm‰inen asema" - "Viimeinen asema", lis‰t‰‰n aseman nimi reitille ja lasketaan uusi pituus
		public void lisaaAsema(Asema lisattavaAsema, Yhteys yhteysLisattavaanAsemaan) {
			this.reitinAsemat.add(lisattavaAsema);
			this.nimi = this.lahtoAsema.getNimi() + " - " + this.reitinAsemat.get(this.reitinAsemat.size()-1).getNimi();
			if (reitti.length() == 0) {
				this.reitti = lisattavaAsema.getNimi();
			} else {
				this.reitti = this.reitti + " - " + lisattavaAsema.getNimi();
			}
			this.matkanPituus += yhteysLisattavaanAsemaan.getMatka();
		}	
	} // Reitti
}
