package problem2;
import java.util.*;

public class DynamicDequeStuff {
	public static void main(String[] args) {
		Deque<Integer> pre = new ArrayDeque<>();
		Deque<Integer> shuffler = new ArrayDeque<>();
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter number of values: ");
		int num = scan.nextInt();
		for(int i = 0; i < num; i++) {
			pre.add(i);
		}
		int shuffle = rand.nextInt(100);
		System.out.println("Seed: "+shuffle);
		System.out.println("Before: ");
		System.out.println(pre);
		System.out.println("After: ");
		for(int i = 0; i < shuffle; i++) {
		while(!pre.isEmpty() && pre.size() != 1) {
			int first = pre.pollFirst();
			int last = pre.pollLast();
			shuffler.add(last);
			shuffler.add(first);
		}
		if(pre.size() == 1) {
			shuffler.add(pre.poll());
		}
		//add back
		for(int j = 0; j < num; j++) {
			pre.add(shuffler.poll());
		}
		}
		System.out.println(pre);
	}
}
