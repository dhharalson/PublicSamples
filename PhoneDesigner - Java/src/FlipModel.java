
public class FlipModel extends Phone implements Repairable {

	public FlipModel() {
		super("Flip Model", "Horse Racer 300", 3, 16);

	}

	@Override
	public String howToRepair() {
		return "To repair phone, contact customer service.";
	}

	@Override
	public double costToRepair() {
		return 50.0;
	}

}
