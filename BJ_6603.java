import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6603 {
	static StringBuffer sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			if(k == 0)
				break;
			
			int[] arr = new int[k];
			for(int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			combi(arr, new int[k],  0, k, 6, 0);
			sb.append("\n");
		}
		sb.delete(sb.length() - 2, sb.length());
		System.out.print(sb.toString());
	}
	
	static void combi(int[] origin, int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			for(int i = 0; i < index; i++) {
				if(i == index - 1)
					sb.append(origin[arr[i]]);
				else
					sb.append(origin[arr[i]] + " ");
			}
			sb.append("\n");
		}
		else if(target == n) {
			return;
		}
		else {
			arr[index] = target;
			combi(origin, arr, index + 1, n, r - 1, target + 1);
			combi(origin, arr, index, n, r, target + 1);
		}
	}
}
