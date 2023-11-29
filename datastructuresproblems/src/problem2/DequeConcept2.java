package problem2;
import java.util.*;
public class DequeConcept2 {
	public static void main(String args[]) {
		Random rand = new Random();
		Deque<Integer> shopcenter = new ArrayDeque<>();
		try {
			while(true) {
				int package1 = rand.nextInt(10000);
				int package2 = rand.nextInt(10000);
				int package3 = rand.nextInt(10000);
				shopcenter.add(package1);
				shopcenter.add(package2);
				shopcenter.add(package3);
				System.out.println("Total Packages: "+ shopcenter.size());
				int first = shopcenter.removeFirst();
				int last = shopcenter.removeLast();
				System.out.println("first package: "+first+", last package: "+last);
				System.out.println("New size of shopcenter: "+shopcenter.size());
				Thread.sleep(3000);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
