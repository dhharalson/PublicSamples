
public class FoldModel extends Phone implements Repairable {

	public FoldModel() {
		super("Fold Model", "Speed Racer 800", 6, 64);

	}

	@Override
	public String howToRepair() {
		return "We are not able to repair this type of phone.";
	}

	@Override
	public double costToRepair() {
		// TODO Auto-generated method stub
		return 0;
	}

}
