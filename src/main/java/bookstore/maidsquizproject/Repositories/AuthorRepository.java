package bookstore.maidsquizproject.Repositories;


import org.springframework.jdbc.core.RowMapper;
import bookstore.maidsquizproject.Models.Author;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class AuthorRepository extends JdbcDaoSupport
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AuthorRepository(DataSource DataSource)
	{
		super.setDataSource(DataSource);
		this.jdbcTemplate = getJdbcTemplate();
	}

	public List<Author> AuthorList()
	{
		return jdbcTemplate.query(this.AuthorList, new AuthorRowMapper());
	}

	public void AuthorInsert(Author author)
	{
		jdbcTemplate.query(this.AuthorInsert, (ResultSetExtractor<Void>) _ -> null, author.FirstName, author.LastName);
	}

	public void AuthorUpdate(Author author)
	{
		jdbcTemplate.query(this.AuthorUpdate, (ResultSetExtractor<Void>) _ -> null, author.Id, author.FirstName, author.LastName);
	}

	public static class AuthorRowMapper implements RowMapper<Author>
	{
		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new Author
			(
				rs.getInt("Id"),
				rs.getString("FirstName"),
				rs.getString("LastName")
			);
		}
	}

	private final String AuthorList = "call BookStore.AuthorList();";

	private final String AuthorInsert = "call BookStore.AuthorInsert(?, ?, @dummy);";

	private final String AuthorUpdate = "call BookStore.AuthorUpdate(?, ?, ?);";



}
