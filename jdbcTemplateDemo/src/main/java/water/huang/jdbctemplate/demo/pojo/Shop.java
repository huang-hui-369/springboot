package water.huang.jdbctemplate.demo.pojo;

public class Shop {

	private int id;
    private String shopName;
    private String body;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", shopName=" + shopName + ", body=" + body + "]";
	}
	
}
