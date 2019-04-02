import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ12851: 숨바꼭질2
 * @author rlaau
 *
 */
public class mwkim_20190405_01 {
	static int way, min;
	static int[] dp;
	
	static int calc(int value, int type) {
		if(type == 0)
			return value * 2;
		else if(type == 1)
			return value + 1;
		else
			return value - 1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		way = 0;
		min = Integer.MAX_VALUE;
		dp = new int[100001];
		
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] temp = new int[2];
		temp[0] = N;
		temp[1] = 0;
		queue.add(temp);
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(cur[0] == K) {
				if(cur[1] < min) {
					min = cur[1];
					way = 1;
				}
				else if(cur[1] == min) {
					way++;
				}
			}
			
			for(int i = 0; i < 3; i++) {
				int[] next = new int[2];
				next[0] = calc(cur[0], i);
				next[1] = cur[1] + 1;
				if(next[0] < 0 || next[0] > 100000)
					continue;
				
				if(dp[next[0]] > 0 && dp[next[0]] < next[1])
					continue;
				
				if(next[1] > min)
					continue;
				
				dp[next[0]] = next[1];
				queue.add(next);
			}
		}
		
		System.out.println(dp[K]);
		System.out.println(way);
	}

}
