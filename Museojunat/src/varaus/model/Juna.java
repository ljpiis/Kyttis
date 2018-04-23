package varaus.model;



/**
 * Model class for a train.
 * 
 */
public class Juna {

	private String nimi;
    private String tyyppi;  // Voisi luoda class Tyyppi, joka ois höyry- tai diesel
    private boolean[] paikat; // tai boolean-taulukoista koostuva array -- vaunut
                                    // asetettais jokaiseen junaan tasan kolme vaunua?
    //eli jokaisessa vaunussa 20 paikkaa? -RK

    /**
     * Default constructor.
     */
    public Juna() {
        this(null, null);
        this.paikat = new boolean[60];
        for(int i=0 ; i<60; i++) {
        	paikat[i]=false;
        }
    }

    /**
     * Constructor with some initial data.
     * 
     * @param nimi
     * @param tyyppi joko h (niinkuin höyryjuna) tai d (niinkuin diisseli)
     */
    public Juna(String nimi, String tyyppi) {
        this.nimi = nimi;
        setTyyppi(tyyppi);

        // Alustetaan tyhjät vaunupaikat 
        this.paikat = new boolean[60];
        for(int i=0 ; i<60; i++) {
        	paikat[i]=false;
        }
    }
    
    //getterit ja setterit

    public String getnimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

	public String getTyyppi() {
		return tyyppi;
	}
	
	public boolean setTyyppi(String tyyppi) {
		if( tyyppi=="h" || tyyppi=="d" ) {
			this.tyyppi=tyyppi;
		return true;
		}
		else {return false;}
	}
	
	public boolean varaaPaikka(int paikka) {  //attribuutiksi vaunun numero..
		if(paikat[paikka]) { // eli jos true, niin on varattu!
			return false;
		}
		else {               // muutoin siis varataan paikka
			paikat[paikka]=true;
			return true;
		}
	}
	
}
