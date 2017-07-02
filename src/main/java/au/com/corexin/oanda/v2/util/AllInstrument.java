package au.com.corexin.oanda.v2.util;

public enum AllInstrument {
	WEST_TEXAS_OIL("WTICO_USD", "West Texas Oil", 0.01, 10000), BRENT_OIL("BCO_USD", "Brent crude oil", 0.01, 10000000), 
	GOLD("XAU_USD", "Gold", 0.01, 1000), 
	
	EUR_AUD("EUR_AUD","EUR AUD",0.0001,10000000),EUR_NZD("EUR_NZD" ,"EUR/NZD", 0.0001,10000000),
	EUR_DKK("EUR_DKK","EUR DKK",0.0001,10000000),EUR_HUF("EUR_HUF","EUR HUF",0.01,10000000),
	EUR_SEK("EUR_SEK","EUR SEK",0.0001,10000000),EUR_TRY("EUR_TRY","EUR TRY",0.0001,10000000),
	EUR_USD("EUR_USD","EUR USD",0.0001,10000000),
	GBP_AUD("GBP_AUD","GBP AUD",  0.0001,10000000),GBP_NZD("GBP_NZD","GBP/NZD", 0.0001,10000000),
	XAG_AUD("XAG_AUD", "Silver/AUD",  0.0001,50000),XAG_NZD("XAG_NZD","Silver/NZD", 0.0001,50000),
	GOLD_AUD("XAU_AUD", "Gold/AUD", 0.01, 2000), GOLD_NZD("XAU_NZD", "Gold/NZD",0.01,2000),	
			
	 AUD_CAD("AUD_CAD","AUD/CAD", 0.0001,10000000),NZD_CAD("NZD_CAD","NZD/CAD", 0.0001,10000000),
	 AUD_CHF("AUD_CHF","AUD/CHF",  0.0001,10000000),NZD_CHF("NZD_CHF","NZD/CHF",  0.0001,10000000),
	 AUD_HKD("AUD_HKD","AUD/HKD",  0.0001,10000000),NZD_HKD("NZD_HKD","NZD/HKD",  0.0001,10000000),
	 AUD_JPY("AUD_JPY","AUD/JPY",  0.01,10000000),NZD_JPY("NZD_JPY","NZD/JPY",  0.01,10000000),
	 AUD_SGD("AUD_SGD", "AUD/SGD", 0.0001, 10000000), NZD_SGD("NZD_SGD", "NZD/SGD", 0.0001, 10000000),					 
	 AUD_USD("AUD_USD", "AUD_USD", 0.0001, 10000000), NZD_USD("NZD_USD", "NZD_USD", 0.0001, 10000000),
	 AUD_NZD("AUD_NZD", "AUD_NZD", 0.0001, 10000000),
	 
	 USD_HKD("USD_HKD","USD/HKD",  0.0001,10000000),
	 
	 SG30_SGD("SG30_SGD","Singapore 30", 0.1, 300),
	 HK33_HKD("HK33_HKD","Hong Kong 33", 1, 400),
	 JP225_USD("JP225_USD","Japan 225", 1, 100),
	 AU200_AUD("AU200_AUD","Australia 200", 1,200),
	 UK100_GBP("UK100_GBP","UK 100", 1, 100),
	 US30_USD("US30_USD","US Wall St 30", 1, 100),
	NL25_EUR("NL25_EUR", "Netherlands 25", 0.01, 2000),
			 
	 CORN_USD("CORN_USD","CORN", 0.01, 150000),
	 WHEAT_USD("WHEAT_USD","WHEAT", 0.01, 150000),
	 SOY_BEAN("SOYBN_USD","SOYBEAN",0.01,60000),

	 COPPER("XCU_USD","Copper",0.0001,250000)
	;

	public String code;
	public String displayName;
	public double pip;
	public int maxTradeUnits;

	private AllInstrument(String code, String displayName, double pip, int maxTradeUnits) {
		this.code = code;
		this.displayName = displayName;
		this.pip = pip;
		this.maxTradeUnits = maxTradeUnits;
	}
}
