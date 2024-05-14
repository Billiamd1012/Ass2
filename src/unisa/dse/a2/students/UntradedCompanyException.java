package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	public UntradedCompanyException() {
        super();
    }

    // Constructor with a message
    public UntradedCompanyException(String companyCode) {
		super(companyCode + "is not a listed company on this exchange");
    }
}
