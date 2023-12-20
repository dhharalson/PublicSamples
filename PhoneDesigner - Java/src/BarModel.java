
public class BarModel extends Phone implements Repairable {

	public BarModel() {
		super("Bar Model", "Dragon Slayer 600", 8, 32);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String howToRepair() {
		return "To repair phone, contact customer service.";
	}

	@Override
	public double costToRepair() {
		// TODO Auto-generated method stub
		return 40.0;
	}

}
