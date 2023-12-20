import java.util.ArrayList;

//Phone class implements Comparable (cache and storage) and Cloneable
abstract class Phone implements Comparable<Phone>, Cloneable {
	private String model;
	private String processor;
	private int cache;
	private int storage;
	protected ArrayList<Character> imei;

	public Phone(String model, String processor, int cache, int storage) {
		this.model = model;
		this.processor = processor;
		this.cache = cache;
		this.storage = storage;
		this.imei = new ArrayList<>();
	}

	// Clone a deep copy of the phone
	public Phone clone() throws CloneNotSupportedException {
		// Create a varable "clonedPhone" from the class Phone, and from the superclass,
		// use the clone method to
		// create a *reference* to to object, not a new instance of it.
		// We also use (Phone) to cast the result of super.clone() because clone()
		// returns Object reference.
		Phone clonedPhone = (Phone) super.clone();
		// This creates a "new" ArrayList with the same this.imei elements.
		clonedPhone.imei = new ArrayList<>(this.imei);
		return clonedPhone;
	}
	// Compare phones based on cache and storage

	@Override
	public int compareTo(Phone other) {
		// Compare the caches of "this" phone to an "other" phone.
		int cacheCompare = Integer.compare(this.cache, other.cache);
		if (cacheCompare != 0) {
			return cacheCompare;
		}
		// Compares "this" storage to an "other" storage
		return Integer.compare(this.storage, other.storage);
	}
	// Getters and setters:

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public ArrayList<Character> getImei() {
		return imei;
	}

	public void setImei(ArrayList<Character> imei) {
		this.imei = imei;
	}

	// Separate display method to display the list of phones.
	public void display() {
		System.out.println("Model: " + model);
		System.out.println("Processor: " + processor);
		System.out.println("Cache: " + cache + "MB");
		System.out.println("Storage: " + storage + "GB");
		System.out.println("IMEI: " + imei);

	}

}
