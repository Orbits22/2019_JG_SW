import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2422 {
	static int N, M, result;
	static boolean[][] notCombi;
	static int[] iceCream;
	
	static void combi(int[] arr, int index, int n, int r, int target) {
		if(r == 0) {
			boolean pass = true;
			loop:
			for(int y = 0; y < index; y++) {
				for(int x = 0; x < index; x++) {
					if(arr[y] == arr[x])
						continue;
					
					if(notCombi[arr[y]][arr[x]]) {
						pass = false;
						break loop;
					}
				}
			}
			
			if(pass) result++;
		}
		else if(n == target) {
			return;
		}
		else {
			arr[index] = target + 1;
			combi(arr, index + 1, n, r - 1, target + 1);
			combi(arr, index, n, r, target + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iceCream = new int[N];
		notCombi = new boolean[N + 1][N + 1];
		result = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			notCombi[temp1][temp2] = true;
		}
		
		combi(iceCream, 0, N, 3, 0);
		System.out.println(result);
	}

}
