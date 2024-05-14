package unisa.dse.a2.students;

public class ListedCompany {

	/**
	 * The full name of the company
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	/**
	 * The listing code of the company
	 */
	private String code;
	
	public String getCode() {
		return code;
	}

	/**
	 * Current price of the company after last trade
	 */
	private int currentPrice;
	
	public int getCurrentPrice() {
		return currentPrice;
	}
	
	public ListedCompany(String _code, String _name, int _currentPrice)
	{
		code = _code;
		name = _name;
		currentPrice = _currentPrice;
	}
	
	/**
	 * Processing a trade should increase the current price of the company by 
	 *    quantity / 100
	 * A company's price CANNOT go below 1
	 * 
	 * @param quantity
	 * @return the price after adjustment
	 */
	private float priceChange;
	private float new_price;

	public void processTrade(int quantity)
	{
		priceChange = quantity/100;
		new_price = currentPrice+priceChange;
		if (new_price < 1){
			currentPrice = 1;
		}
		else{
			currentPrice = Math.round(new_price);
		}
	}
}
