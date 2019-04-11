import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17135 {
	static int result, N, M, D, maxEnemy;
	static int[][] map;
	static int[][] range = {{-1, 0}, {0, 1}, {0, -1}};
	
	static void arrayCopy(int[][] temp, int[][] origin) {
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < M; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}
	
	static void playGame(int[][] newMap, int[] archers) {
		arrayCopy(newMap, map);
		
		int turn = 0;
		int dead = 0;
		while(turn < maxEnemy) {
			ArrayList<int[]> deathList = new ArrayList<int[]>();
			
			for(int a = 0; a < M; a++) {
				if(archers[a] == 0)
					continue;
				
				int min_distance = Integer.MAX_VALUE;
				Queue<int[]> queue = new LinkedList<int[]>();
				int[] min = new int[3];
				boolean[][] visit = new boolean[N][M];
				min[0] = N - 1;
				min[1] = a;
				min[2] = 1;
				visit[min[0]][min[1]] = true;
				queue.add(min);
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					if(newMap[cur[0]][cur[1]] == 1) {
						if(min_distance > cur[2]) {
							min_distance = cur[2];
							min[0] = cur[0];
							min[1] = cur[1];
						}
						else if(min_distance == cur[2]) {
							if(min[1] > cur[1]) {
								min[0] = cur[0];
								min[1] = cur[1];
							}
						}
					}
					
					for(int d = 0; d < 3; d++) {
						int[] next = new int[3];
						next[0] = cur[0] + range[d][0];
						next[1] = cur[1] + range[d][1];
						
						if(next[0] >= N || next[1] >= M || next[0] < 0 || next[1] < 0)
							continue;
						
						next[2] = Math.abs(N - next[0]) + Math.abs(a - next[1]);
						if(next[2] > D)
							continue;
						
						if(visit[next[0]][next[1]])
							continue;
						
						visit[next[0]][next[1]] = true;
						queue.add(next);
					}
				}
				
				deathList.add(min);
			}
			
			for(int i = 0; i < deathList.size(); i++) {
				int[] tmp = deathList.get(i);
				if(newMap[tmp[0]][tmp[1]] == 0)
					continue;
				
				newMap[tmp[0]][tmp[1]] = 0;
				dead++;
			}
			
			for(int y = N - 2; y >= 0; y--) {
				for(int x = 0; x < M; x++) {
					newMap[y + 1][x] = newMap[y][x];
					newMap[y][x] = 0;
				}
			}
			turn++;
		}
		
		result = Math.max(result, dead);
	}
	
	static void setArcher(int arr[], int index, int n, int r, int target) {
		if(r == 0) {
			map[N] = new int[M];
			for(int i = 0; i < index; i++) {
				map[N][arr[i]] = 2;
			}
			playGame(new int[N][M], map[N]);
		}
		else if(n == target) {
			return;
		}
		else {
			arr[index] = target;
			setArcher(arr, index + 1, n, r - 1, target + 1);
			setArcher(arr, index, n, r, target + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		result = 0;
		map = new int [N + 1][M];
		maxEnemy = 0;
		
		for(int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				if(map[y][x] == 1 && maxEnemy < N - y) {
					maxEnemy = N - y;
				}
			}
		}
		setArcher(new int[M], 0, M, 3, 0);
		
		System.out.println(result);
	}

}
