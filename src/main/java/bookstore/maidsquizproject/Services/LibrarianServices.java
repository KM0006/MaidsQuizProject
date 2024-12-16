package bookstore.maidsquizproject.Services;

import bookstore.maidsquizproject.Models.Librarian;
import bookstore.maidsquizproject.Repositories.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class LibrarianServices implements UserDetailsService
{

	private final LibrarianRepository librarianRepository;

	private final JwtServices jwtServices;

	@Autowired
	public LibrarianServices(LibrarianRepository librarianRepository, JwtServices jwtServices)
	{
		this.librarianRepository = librarianRepository;
		this.jwtServices = jwtServices;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Librarian librarian = librarianRepository.LibrarianGetByUserName(username);
		return User.builder().username(librarian.UserName).password(librarian.UserPassword).build();
	}

	public Librarian LibrarianGetByUserName(String username)
	{
		return librarianRepository.LibrarianGetByUserName(username);
	}

	public void LibrarianInsert(Librarian Librarian)
	{
		librarianRepository.LibrarianInsert(Librarian);
	}

	public Librarian LibrarianGetByBearerToken(String Token)
	{

		String UserName = jwtServices.ExtractUsername(Token);

		return this.LibrarianGetByUserName(UserName);

	}

}
