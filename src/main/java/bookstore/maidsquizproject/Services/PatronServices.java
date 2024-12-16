package bookstore.maidsquizproject.Services;

import bookstore.maidsquizproject.Exceptions.SqlConstraintCheckViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import bookstore.maidsquizproject.Models.Patron;
import bookstore.maidsquizproject.Repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
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
		Patron patron = patronRepository.PatronGetById(Id);
		if (patron == null)
		{
			throw new ResourceNotFoundException();
		}
		return patron;
	}

	public void PatronInsert(Patron patron) throws SqlConstraintCheckViolationException
	{
		try
		{
			patronRepository.PatronInsert(patron);
		}
		catch (DataAccessException dataAccessException)
		{
			throw new SqlConstraintCheckViolationException("Invalid Input data, check for typos, email formats and phone formats.");
		}

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
