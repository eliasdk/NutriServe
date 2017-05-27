package application.entity;
/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

public class Product extends Entity{
	private String type;
	private String supplier;
	private String id;
	private String category;
	private String guaranteeInfo;

	public Product(String type, String supplier, String id, String category, String guaranteeinfo) {
		this.type = type;
		this.supplier = supplier;
		this.id = id;
		this.category = category;
		this.guaranteeInfo = guaranteeinfo;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setGuaranteeinfo(String guaranteeinfo) {
		this.guaranteeInfo = guaranteeinfo;
	}

	public String getGuaranteeinfo() {
		return guaranteeInfo;
	}

	@Override
	public String toString() {
		return category;
	}
	public String getJson(){
		return "{\"category\":\""+getCategory()+"\",\"guaranteeInfo\":\""+getGuaranteeinfo()+"\","+
			  "\"id\":\""+getId()+"\",\"supplier\":\""+getSupplier()+"\",\"type\":\""+getType()+"\"}";
	}
}