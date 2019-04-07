import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17071 {
	
	static int move(int cur, int type) {
		if(type == 0)
			return cur * 2;
		else if(type == 1)
			return cur + 1;
		else
			return cur - 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		int[][] dp = new int[2][500001];
		
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] temp = new int[2];
		temp[0] = N;
		temp[1] = 0;
		int time = 0;
		queue.add(temp);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(time < cur[1]) {
				time++;
				K += time;
			}

			//System.out.println(cur[1] + ": " + K + " | " + cur[0]);
			if(K > 500000)
				break;
			
			if(cur[0] == K || dp[cur[1] % 2][K] > 0) {
				min = Math.min(min, cur[1]);
				break;
			}
			
			for(int i = 0; i < 3; i++) {
				int[] next = new int[2];
				next[0] = move(cur[0], i);
				next[1] = cur[1] + 1;
				
				if(next[0] < 0 || next[0] > 500000)
					continue;
				
				if(dp[next[1] % 2][next[0]] > 0 && dp[next[1] % 2][next[0]] < next[1])
					continue;
				
				dp[next[1] % 2][next[0]] = next[1];
				queue.add(next);
			}
		}
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
