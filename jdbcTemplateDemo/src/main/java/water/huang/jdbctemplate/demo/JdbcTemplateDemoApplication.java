package water.huang.jdbctemplate.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import water.huang.jdbctemplate.demo.pojo.Shop;
import water.huang.jdbctemplate.demo.repository.ShopRepository;

@SpringBootApplication
public class JdbcTemplateDemoApplication implements CommandLineRunner{

	@Autowired
	ShopRepository shoprepo;
	
	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateDemoApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
        System.out.println("-----  select shopRowMapper ------");
        Shop shop = shoprepo.selectRowMapper(1);
        System.out.println(shop.toString());
        System.out.println("-----  select Mapper ------");
        Map<String, Object> map = shoprepo.selectMapper(1);
        System.out.println(map.toString());
        shop.setShopName("test1");
        shop.setBody("test1 body");
        int id = shoprepo.insert(shop);
        System.out.format("-----  insert [%d]------\n", id);
        shop = new Shop();
        System.out.println("-----  select all ------");
        List<Map<String, Object>> shopList = shoprepo.selectAll();
        for(Map<String, Object> item : shopList) {
        	System.out.println(item.toString());
        }
    }

}
