import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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
		for(int k = 1; k <= N + 1; k++) {
			cost = calcCost(k);
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					temp = new boolean[N][N];
					pay = 0;
					house = 0;
					Queue<int[]>queue = new LinkedList<int[]>();
					int[] temp_map = new int[3];
					temp_map[0] = y;
					temp_map[1] = x;
					temp_map[2] = 1;
					queue.add(temp_map);
					
					temp[y][x] = true;
					if(matrix[y][x] == 1) {
						house++;
						pay += M;
					}

					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						
						if(cur[2] < k) {
							for(int i = 0; i < 4; i++) {
								int[] next = new int[3];
								next[0] = cur[0] + move[i][0];
								next[1] = cur[1] + move[i][1];
								
								if(next[0] >= N || next[1] >= N || next[0] < 0 || next[1] < 0)
									continue;
								
								if(temp[next[0]][next[1]])
									continue;
								
								next[2] = cur[2] + 1;
								temp[next[0]][next[1]] = true;
								if(matrix[next[0]][next[1]] == 1) {
									house++;
									pay += M;
								}
								
								queue.add(next);
							}
						}
					}
					//System.out.println("[k: " + k + "][" + y + "][" + x + "]: " + cost + ":" + pay + " :::: " + house);
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
