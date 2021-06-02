package water.huang.jdbctemplate.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import water.huang.jdbctemplate.demo.pojo.Shop;



@Repository
public class ShopRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public int insert(Shop pojo) {
		 String sql = "insert into shop(id,shop_name,body) values(null,?,?)";
	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
	        	@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					 PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
		                ps.setString(1,pojo.getShopName());
		                ps.setString(2,pojo.getBody());
		                return ps;
				}
	        },keyHolder);
	        
	        return Integer.parseInt(keyHolder.getKey().toString());
	}

	
	public int update(Shop pojo) {
		String sql = "update shop set shop_name=?,body=? where id=?";
        
        return jdbcTemplate.update(sql, pojo.getShopName(), pojo.getBody() );
		
	}

	
	public int delete(int id) {
		String sql = "delete from shop where id = ?";
        return jdbcTemplate.update(sql,id);
		
	}

	
	public Shop selectRowMapper(int id) {
		Shop shop = jdbcTemplate.queryForObject("select * from shop where id = ?", new ShopRowMapper(), id);
		return shop;
	}
	
	public Map<String, Object> selectMapper(int id) {
		Map<String, Object> map = jdbcTemplate.queryForMap("select * from shop where id = ?", id);
		return map;
	}

	
	public List<Map<String, Object>> selectAll() {
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from shop");
		return mapList;
	}

}


class ShopRowMapper implements RowMapper<Shop> {

    @Override
    public Shop mapRow(ResultSet resultSet,int i) throws SQLException{
    	Shop pojo = new Shop();
    	pojo.setId(resultSet.getInt("id"));
    	pojo.setShopName(resultSet.getString("shop_name"));
    	pojo.setBody(resultSet.getString("body"));
        
        return pojo;
    }
}