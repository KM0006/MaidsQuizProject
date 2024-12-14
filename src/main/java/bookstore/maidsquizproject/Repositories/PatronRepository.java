package bookstore.maidsquizproject.Repositories;

import org.springframework.jdbc.core.RowMapper;
import bookstore.maidsquizproject.Models.Patron;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.SQLException;

@Repository
@SuppressWarnings("FieldCanBeLocal")
public class PatronRepository extends JdbcDaoSupport
{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PatronRepository(DataSource DataSource)
	{
		super.setDataSource(DataSource);
		this.jdbcTemplate = getJdbcTemplate();
	}

	public Patron PatronGetById(int Id)
	{
		return jdbcTemplate.query(this.PatronGetById, new PatronRepository.PatronRowMapper(), Id).getFirst();
	}

	public List<Patron> PatronList()
	{
		return jdbcTemplate.query(this.PatronList, new PatronRepository.PatronRowMapper());
	}

	public List<Patron> PatronActiveList()
	{
		return jdbcTemplate.query(this.PatronActiveList, new PatronRepository.PatronRowMapper());
	}

	public void PatronInsert(Patron patron)
	{
		jdbcTemplate.query(this.PatronInsert, (ResultSetExtractor<Void>) _ -> null, patron.FirstName, patron.LastName, patron.Email, patron.PhoneNumber);
	}

	public void PatronUpdate(Patron patron)
	{
		jdbcTemplate.query(this.PatronUpdate, (ResultSetExtractor<Void>) _ -> null, patron.Id, patron.FirstName, patron.LastName, patron.Email, patron.PhoneNumber);
	}

	public void PatronDelete(int Id)
	{
		jdbcTemplate.query(this.PatronDelete, (ResultSetExtractor<Void>) _ -> null, Id);
	}



	public static class PatronRowMapper implements RowMapper<Patron>
	{
		@Override
		public Patron mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new Patron
			(
				rs.getInt("Id"),
				rs.getString("FirstName"),
				rs.getString("LastName"),
				rs.getString("Email"),
				rs.getString("PhoneNumber"),
				rs.getBoolean("IsActive")
			);
		}
	}

	private final String PatronGetById = "call BookStore.PatronGetById(?);";

	private final String PatronList = "call BookStore.PatronList();";

	private final String PatronActiveList = "call BookStore.PatronActiveList();";

	private final String PatronInsert = "call BookStore.PatronInsert(?, ?, ?, ?, @dummy);";

	private final String PatronUpdate = "call BookStore.PatronUpdate(?, ?, ?, ?, ?, ?);";

	private final String PatronDelete = "call BookStore.PatronDelete(?);";
}
