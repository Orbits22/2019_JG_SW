import java.util.ArrayList;
import java.util.Arrays;

public class Combi_Perm {

	public static void main(String[] args) {
		int n = 5;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++) {
			System.out.println("COMBI: [" + i + "]================");
			combi(new int[i], 0, n, i, 0);
			list.add(i);
			System.out.println("PERM: [" + i + "]================");
			perm(list, 0);
		}
	}
	
	static void combi(int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			System.out.println(Arrays.toString(arr));
		}
		else if(n == target)
			return;
		else {
			arr[index] = target + 1;
			combi(arr, index + 1, n, r - 1, target + 1);
			combi(arr, index, n, r, target + 1);
		}
	}
	
	static void perm(ArrayList<Integer> list, int pivot) {
		if(list.size() == pivot) {
			System.out.println(Arrays.toString(list.toArray()));
			return;
		}
		else {
			for(int i = pivot; i < list.size(); i++) {
				swap(list, i, pivot);
				perm(list, pivot + 1);
				swap(list, i, pivot);
			}
		}
	}
	
	static void swap(ArrayList<Integer> list, int a, int b) {
		int temp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, temp);
	}
}
