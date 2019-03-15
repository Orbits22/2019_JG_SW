import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		for(int y = 0; y < N - 1; y++) {
			for(int x = 1; x < N - 1; x++) {
				int min = Integer.MAX_VALUE;
				for(int i = 0; i < 4; i++) {
					int ny = y + move[i][0];
					int nx = x + move[i][1];
					
					if(ny >= N || nx >= N || ny < 0 || nx < 0)
						continue;
					
					min = Math.min(min, map[ny][nx]);
				}
				dp[y][x] = map[y][x] + min;
			}
		}
		
		for(int x = 0; x < N - 1; x++) {
			for(int y = 1; y < N - 1; y++) {
				int min = Integer.MAX_VALUE;
				for(int i = 0; i < 4; i++) {
					int ny = y + move[i][0];
					int nx = x + move[i][1];
					
					if(ny >= N || nx >= N || ny < 0 || nx < 0)
						continue;
					
					min = Math.min(min, map[ny][nx]);
				}
				dp[y][x] = map[y][x] + min;
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
				}
 			}
			moving();
			System.out.println("");
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					System.out.print(dp[y][x] + "\t");
				}
				System.out.println("");
			}
			
			br.close();
			System.out.println("#" + test + " " + dp[N - 1][N - 1]);
		}
	}

}
