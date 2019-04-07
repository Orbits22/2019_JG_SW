import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16987 {
	static int N, result;
	static int[][] eggs;
	
	static void crush(int index, int crushed) {
		result = Math.max(result, crushed);
		if(index == N) {
			return;
		}
		
		if(eggs[index][0] <= 0) {
			crush(index + 1, crushed);
			return;
		}
		
		for(int right = 0; right < N; right++) {
			if(index == right)
				continue;
			
			if(eggs[right][0] <= 0)
				continue;
			
			int newC = crushed;
			eggs[index][0] -= eggs[right][1];
			eggs[right][0] -= eggs[index][1];
			if(eggs[index][0] <= 0)
				newC++;
			if(eggs[right][0] <= 0)
				newC++;

			crush(index + 1, newC);
			eggs[index][0] += eggs[right][1];
			eggs[right][0] += eggs[index][1];
			//crush(index + 1, crushed);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new int[N][2];
		result = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i][0] = Integer.parseInt(st.nextToken());
			eggs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		crush(0, 0);
		System.out.println(result);
	}

}
