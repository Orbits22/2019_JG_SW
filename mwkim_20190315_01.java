import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * SWEA 1249: 보급로
 * @author 김명우
 *
 */
public class mwkim_20190315_01 {
	static int N;
	static int[][] map, dp;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static void moving() {
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] temp = new int[3];
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		queue.add(temp);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(cur[0] == N - 1 && cur[1] == N - 1) {
				dp[cur[0]][cur[1]] = Math.min(dp[cur[0]][cur[1]], cur[2]);
			}
			else {
				for(int i = 0; i < 4; i++) {
					int[] next = new int[3];
					next[0] = cur[0] + move[i][0];
					next[1] = cur[1] + move[i][1];
					
					if(next[0] >= N || next[1] >= N || next[0] < 0 || next[1] < 0)
						continue;
					
					next[2] = cur[2] + map[next[0]][next[1]];
					if(dp[next[0]][next[1]] <= next[2])
						continue;
					
					dp[next[0]][next[1]] = next[2];
					queue.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			
			for(int y = 0; y < N; y++) {
				char[] temp = br.readLine().toCharArray();
				for(int x = 0; x < N; x++) {
					map[y][x] = Character.getNumericValue(temp[x]);
					dp[y][x] = Integer.MAX_VALUE;
				}
 			}
			moving();
			System.out.println("#" + test + " " + dp[N - 1][N - 1]);
		}
	}

}
