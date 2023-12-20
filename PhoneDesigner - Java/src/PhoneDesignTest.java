import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PhoneDesignTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		FlipModel flipPhone1 = new FlipModel();
		flipPhone1.getImei().add('A');
		FlipModel flipPhone2 = (FlipModel) flipPhone1.clone();

		FoldModel foldPhone1 = new FoldModel();
		foldPhone1.getImei().add('B');
		FoldModel foldPhone2 = (FoldModel) foldPhone1.clone();

		BarModel barPhone1 = new BarModel();
		barPhone1.getImei().add('C');
		BarModel barPhone2 = (BarModel) barPhone1.clone();

		// Compare phones based on cache and storage
		List<Phone> phones = Arrays.asList(flipPhone1, flipPhone2, foldPhone1, foldPhone2, barPhone1, barPhone2);
		Collections.sort(phones);

		// Display phone details
		for (Phone phone : phones) {
			phone.display();
			if (phone instanceof Repairable) {
				Repairable repairablePhone = (Repairable) phone;
				System.out.println("Repair Information:");
				System.out.println(repairablePhone.howToRepair());
				System.out.println("Cost to Repair: $" + repairablePhone.costToRepair());
			}
			System.out.println();
		}
	}
}
