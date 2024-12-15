package bookstore.maidsquizproject.Services;

import org.springframework.stereotype.Service;
import bookstore.maidsquizproject.Models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import bookstore.maidsquizproject.Repositories.AuthorRepository;

import java.util.List;

@Service
public class AuthorService
{

	private final AuthorRepository authorRepository;

	@Autowired
	public AuthorService(AuthorRepository authorRepository)
	{
		this.authorRepository = authorRepository;
	}

	public List<Author> AuthorList()
	{
		return authorRepository.AuthorList();
	}

	public void AuthorInsert(Author author)
	{
		authorRepository.AuthorInsert(author);
	}

	public void AuthorUpdate(Author author)
	{
		authorRepository.AuthorUpdate(author);
	}
}
