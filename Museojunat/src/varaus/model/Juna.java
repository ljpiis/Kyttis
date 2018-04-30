package varaus.model;



/**
 * Model class for a train.
 * 
 */
public class Juna {

	private String nimi;
    private String tyyppi;  // Voisi luoda class Tyyppi, joka ois höyry- tai diesel
    private int vaunuja;
    private int paikkojaVaunussa;   
    private boolean[][] paikat; // tai boolean-taulukoista koostuva array -- vaunut
                                    // asetettais jokaiseen junaan tasan kolme vaunua?
    //eli jokaisessa vaunussa 20 paikkaa? -RK

    /**
     * Default constructor.
     */
    public Juna() {
        this(null, null, 3, 20);
        this.paikat = new boolean[3][20];
        for (int j=0; j<3; j++) {
        	for(int i=0 ; i<20; i++) {
            	paikat[j][i]=false;
            }
        }      
    }

    /**
     * Constructor with some initial data.
     * 
     * @param nimi
     * @param tyyppi joko h (niinkuin höyryjuna) tai d (niinkuin diisseli)
     */
    public Juna(String nimi, String tyyppi, int vaunuja, int paikkojaVaunussa) {
        this.nimi = nimi;
        setTyyppi(tyyppi);
        this.vaunuja = vaunuja;
        this.paikkojaVaunussa = paikkojaVaunussa;
        this.paikat = new boolean[vaunuja][paikkojaVaunussa];

        // Alustetaan tyhjät vaunupaikat 
        for (int j=0; j<vaunuja; j++) {
        	for(int i=0 ; i<paikkojaVaunussa; i++) {
            	paikat[j][i]=false;
            }
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
	
	public int getVaunuja() {
        return this.vaunuja;
	    }

    public void setVaunuja(int vaunuja) {
        this.vaunuja = vaunuja;
    }
    
    public int getPaikkojaVaunussa() {
        return this.paikkojaVaunussa;
	    }

    public void setPaikkojaVaunussa(int paikkojaVaunussa) {
        this.paikkojaVaunussa = paikkojaVaunussa;
    }
	
	public boolean varaaPaikka(int vaunu, int paikka) { 
		if (vaunu < 1 || vaunu > this.vaunuja || paikka < 1 || paikka > this.paikkojaVaunussa) {
			System.out.println("Junassa ei ole paikkaa numero" + paikka + "vaunussa" + vaunu + ".");
			return false;
		}
		if(paikat[vaunu][paikka]) { // eli jos true, niin on varattu!
				return false;
			}
		else {               // muutoin siis varataan paikka
			paikat[vaunu][paikka]=true;
			return true;
		}
	}
	
}
