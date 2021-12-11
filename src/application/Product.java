package application;
public class Product {
	    private String name;
	    private String imgPath;
	    private double price;
	    

	    public Product() {
	    }

	    public String getName() {
	        return this.name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getImgPath() {
	        return this.imgPath;
	    }

	    public void setImgPath(String imgSrc) {
	        this.imgPath = imgSrc;
	    }

	    public double getPrice() {
	        return this.price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
}
