package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	public UntradedCompanyException(String companyCode)
	{
		String exception = companyCode + "is not a listed company on this exchange";
		System.err.println(exception);
	}
}
