package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String _name)
	{
		name = _name;
		this.brokers = new DSEListGeneric<>();
		this.announcements = new DSEListGeneric<>();
		this.companies = new HashMap<>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		if (company == null){
			return false;
		}
		if (companies.containsKey(company.getCode())){
			return false;
		}
		companies.put(company.getCode(), company);
		return true;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		if (broker == null){
			return false;
		}
		if (brokers.contains(broker)){
			return false;
		}
		brokers.add(broker);
		return true;
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException
	{
		int successfulTrades = 0;
		for (int i = 0; i < brokers.size(); i++){
			StockBroker currentBroker = brokers.get(i);
			Trade nextTrade = currentBroker.getNextTrade();
			if (nextTrade == null){
				continue;
			}
			String nextTradeCompanyCode = nextTrade.getCompanyCode();
			if (!companies.containsKey(nextTradeCompanyCode)){
				throw new UntradedCompanyException(nextTradeCompanyCode);
			}
			ListedCompany nextTradeCompany = companies.get(nextTradeCompanyCode);
			int nextTradeQuantity = nextTrade.getShareQuantity();
			int priceBeforeTrade = nextTradeCompany.getCurrentPrice();
			nextTradeCompany.processTrade(nextTradeQuantity);
			successfulTrades++;
			
			String announcement = String.format("Trade: %d %s @ %d via %s", nextTradeQuantity, nextTradeCompanyCode, priceBeforeTrade, currentBroker.getName());
			announcements.add(announcement);
		}
		return successfulTrades;
	}
	
	public int runCommandLineExchange(Scanner sc)
	{
		return 0;
	}
}
