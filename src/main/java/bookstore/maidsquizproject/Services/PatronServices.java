package bookstore.maidsquizproject.Services;

import org.springframework.stereotype.Service;
import bookstore.maidsquizproject.Models.Patron;
import bookstore.maidsquizproject.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PatronServices
{
	private final PatronRepository patronRepository;

	@Autowired
	public PatronServices(PatronRepository patronRepository)
	{
		this.patronRepository = patronRepository;
	}

	public List<Patron> PatronList()
	{
		return patronRepository.PatronList();
	}

	public List<Patron> PatronActiveList()
	{
		return patronRepository.PatronActiveList();
	}


	public Patron PatronGetById(int Id)
	{
		return patronRepository.PatronGetById(Id);
	}

	public void PatronInsert(Patron patron)
	{
		patronRepository.PatronInsert(patron);
	}

	public void PatronUpdate(Patron patron)
	{
		patronRepository.PatronUpdate(patron);
	}

	public void PatronDelete(int Id)
	{
		patronRepository.PatronDelete(Id);
	}
}
