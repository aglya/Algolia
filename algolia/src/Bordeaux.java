package src;

public class Bordeaux {

	private String name;
	private int year;
	private String type;
	private String domain;
	private int quantity;
	private double price;
	private int quality;
	private String image;
	private String objectID;
	
	//constructor
	public Bordeaux(){}
	
	public String getName() {return name;}
	public Bordeaux setName(String name){
		this.name = name; 
		return this;
	}
	
	public int getYear() {return year;}
	public Bordeaux setYear(int year){
		this.year = year; 
		return this;
	}
	
	public String getType() {return type;}
	public Bordeaux setType(String type){
		this.type = type; 
		return this;
	}
	
	public String getDomain() {return domain;}
	public Bordeaux setDomain(String domain){
		this.domain = domain; 
		return this;
	}
	
	public int getQuantity() {return quantity;}
	public Bordeaux setQuantity(int quantity){
		this.quantity = quantity; 
		return this;
	}
	
	public double getPrice() {return price;}
	public Bordeaux setPrice(double price){
		this.price = price; 
		return this;
	}
	
	public int getQuality() {return quality;}
	public Bordeaux setQuality(int quality){
		this.quality = quality; 
		return this;
	}
	
	public String getImage() {return image;}
	public Bordeaux setImage(String image){
		this.image = image; 
		return this;
	}
	
//	public String getObjectID() {return objectID;}
//	public Bordeaux setObjectID(String objectID){
//		this.objectID = objectID; 
//		return this;
//	}
}