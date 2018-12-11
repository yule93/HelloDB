package org.dbfm.csemall;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component("offerDAO")
public class OfferDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate =new JdbcTemplate(dataSource);
	}
	public int getRowCount() {
		String sqlStatement = "select count(*) from offers";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	//query and return a single object
	public Offer getOffer(String name) {
		String sqlStatement = "select * from offers where name=?";
		
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name},
				new RowMapper<Offer>() {
					public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Offer offer = new Offer();
						
						offer.setId(rs.getInt("id"));
						offer.setName(rs.getString("name"));
						offer.setEmail(rs.getString("email"));
						offer.setText(rs.getString("text"));
						
						return offer;
					}
				}
		);
	}
	
	//query and return multiple objects
	public List<Offer> getOffers() {
		String sqlStatement = "select * from offers";
		
		return jdbcTemplate.query(sqlStatement,
				new RowMapper<Offer>() {
					public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Offer offer = new Offer();
						offer.setId(rs.getInt("id"));
						offer.setName(rs.getString("name"));
						offer.setEmail(rs.getString("email"));
						offer.setText(rs.getString("text"));
						
						return offer;
					}
				}
		);
	}
	
	public boolean insert(Offer offer) {
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement = "insert into offers (name, email, text) values (?, ?, ?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text}) == 1);
	}
	
	public boolean update(Offer offer) {
		
		int id = offer.getId();
		String name = offer.getName();
		String email = offer.getEmail();
		String text = offer.getText();
		
		String sqlStatement = "update offers set name = ?, email = ?, text = ?, where id = ?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {name, email, text, id}) == 1);
	}
	
	public boolean delete(int id) {
		String sqlStatement = "delete from offers where id = ?";
		return (jdbcTemplate.update(sqlStatement, new Object[] {id}) == 1);
	}
}
