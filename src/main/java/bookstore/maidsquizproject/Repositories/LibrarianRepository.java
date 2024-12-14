package bookstore.maidsquizproject.Repositories;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import bookstore.maidsquizproject.Models.Librarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class LibrarianRepository extends JdbcDaoSupport
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public LibrarianRepository(DataSource DataSource)
	{
		super.setDataSource(DataSource);
		this.jdbcTemplate = getJdbcTemplate();
	}

	public Librarian LibrarianGetById(int Id)
	{
		return jdbcTemplate.query(this.LibrarianGetById, new LibrarianRowMapper(), Id).getFirst();
	}

	public List<Librarian> LibrarianList()
	{
		return jdbcTemplate.query(this.LibrarianList, new LibrarianRowMapper());
	}

	public void LibrarianUpdate(Librarian librarian)
	{
		jdbcTemplate.query(this.LibrarianUpdate, (ResultSetExtractor<Void>) _ -> null, librarian.Id, librarian.UserName, librarian.UserPassword);
	}

	public void LibrarianInsert(Librarian librarian)
	{
		jdbcTemplate.query(this.LibrarianInsert, (ResultSetExtractor<Void>) _ -> null, librarian.UserName, librarian.UserPassword);
	}

	public Librarian LibrarianGetByUserName(String UserName)
	{
		return jdbcTemplate.query(this.LibrarianGetByUsername, new LibrarianRowMapper(), UserName).getFirst();
	}

	private static class LibrarianRowMapper implements RowMapper<Librarian>
	{
		@Override
		public Librarian mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new Librarian
					(
						rs.getInt("Id"),
						rs.getString("UserName"),
						rs.getString("UserPassword")
					);
		}
	}


	private final String LibrarianGetById = "call BookStore.LibrarianGetById(?);";

	private final String LibrarianList = "call BookStore.LibrarianList();";

	private final String LibrarianUpdate = "call BookStore.LibrarianUpdate(?, ?, ?);";

	private final String LibrarianInsert = "call BookStore.LibrarianInsert(?, ?, @dummy);";

	private final String LibrarianGetByUsername = "call BookStore.LibrarianGetByUserName(?);";

}
