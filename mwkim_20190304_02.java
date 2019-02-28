import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * 
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190304_02 {
	static int N, M, result, cost, pay, house;
	static int[][] matrix;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	
	
	static void setSecuZone() {
		boolean[][] temp = new boolean[N][N];
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				temp = new boolean[N][N];
				pay = 0;
				house = 0;
				
				temp[y][x] = true;
				if(matrix[y][x] == 1) {
					house++;
					pay += M;
				}
				
				for(int k = 0; k <= N; k++) {
					cost = calcCost(k + 1);
					
					for(int limit = k; limit >= 0; limit--) {
						for(int dy = 0; dy < 4; dy++) {
							int new_y = y + (move[dy][0] * (k - limit));
							
							if(new_y >= N || new_y < 0)
								continue;
							
							for(int dx = 0; dx < 4; dx++) {
								int new_x = x + (move[dx][1] * limit);
								
								if(new_x >= N || new_x < 0)
									continue;
								
								if(temp[new_y][new_x])
									continue;
								
								temp[new_y][new_x] = true;
								if(matrix[new_y][new_x] == 1) {
									house++;
									pay += M;
								}
							}
						}
					}
					if(pay >= cost) {
						result = Math.max(result, house);
						if(result == N * N)
							return;
					}
				}
			}
		}
	}
	
	static int calcCost(int k) {
		return (k * k) + ((k - 1) * (k - 1));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;
			
			matrix = new int[N][N];
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					matrix[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			setSecuZone();
			System.out.println("#" + test + " " + result);
		}
	}

}
