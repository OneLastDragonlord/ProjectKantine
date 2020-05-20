public class Datum {
	private int dag;
	private int maand;
	private int jaar;

	/**
	 * Constructor
	 */
	public Datum(int dag, int maand, int jaar) {
		if(bestaatDatum(dag, maand, jaar)) {
			this.dag = dag;
			this.maand = maand;
			this.jaar = jaar;
		}
	}
	public Datum() {
		this.dag = 0;
		this.maand = 0;
		this.jaar = 0;
	}

	/**
	 * Setters
	 */
	public void setDag(int dag) {this.dag = dag;}
	public void setMaand(int maand) {this.maand = maand;}
	public void setJaar(int jaar) {this.jaar = jaar;}

	/**
	 * Getters
	 */
	public int getDag() {return dag;}
	public int getMaand() {return maand;}
	public int getJaar() {return jaar;}

	public boolean bestaatDatum(int dag, int maand, int jaar) {
		int aantalDagen = dag - 1;
		switch (maand) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				aantalDagen = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				aantalDagen = 30;
				break;
			case 2:
				aantalDagen = ((jaar % 4) == 0) ? 29 : 28;
				break;
		}
		return (dag > aantalDagen || dag < 1 || jaar < 1900 || jaar > 2019);
	}
	public boolean bestaatDatum2(int dag, int maand, int jaar) {
		if(jaar >= 1900 && jaar <= 2100){
			if (dag > 0 && dag <= 31) {
				if(maand > 0 && maand <= 12) {
					switch (maand) {
						case 2:
							if (dag > 28) {
								if (jaar % 4 == 0) {
									if (jaar % 100 != 0) {
										if (dag <= 29) {
											return true;
										}
									} else if (jaar % 400 == 0) {
										if (dag <= 29) {
											return true;
										}
									}
								}
							} else {
								return true;
							}
							break;
						case 4:
						case 6:
						case 9:
						case 11:
							if (dag <= 30){
								return true;
							}
							break;
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
							if (dag <= 31) {
								return true;
							}
							break;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Getter voor Sting weergave van datum
	 *
	 * @return Geboortedatum
	 */
	public String getDatumAsString() {
		// TODO
		return "";
	}
}
