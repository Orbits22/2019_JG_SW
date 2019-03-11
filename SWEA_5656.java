import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656 {
	static int result, N, W, H;
	static int[][] map;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static int getWalls() {
		int walls = 0;
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				if(map[y][x] > 0)
					walls++;
			}
		}
		
		return walls;
	}
	
	static void execute(int wall) {
		if(wall == N) {
			result = Math.min(result, getWalls());
			return;
		}
		int[][] temp = new int[H][W];
		arrayCopy(temp, map);
		for(int x = 0; x < W; x++) {
			int y = 0;
			while(y < H - 1) {
				if(map[y][x] > 0)
					break;
				y++;
			}
			crash(y, x);
			down();
			execute(wall + 1);
			arrayCopy(map, temp);
		}
	}
	
	static void crash(int first_y, int first_x) {	
		int[] temp = new int[3];
		temp[0] = first_y;
		temp[1] = first_x;
		temp[2] = map[first_y][first_x];
		map[first_y][first_x] = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(temp);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int k = 1; k < cur[2]; k++) {
				for(int i = 0; i < 4; i++) {
					int[] next = new int[3];
					next[0] = cur[0] + (move[i][0] * k);
					next[1] = cur[1] + (move[i][1] * k);
					
					if(next[0] >= H || next[1] >= W || next[0] < 0 || next[1] < 0)
						continue;
					
					if(map[next[0]][next[1]] == 0)
						continue;

					next[2] = map[next[0]][next[1]];
					map[next[0]][next[1]] = 0;
					queue.add(next);
				}
			}
		}
	}
	
	static void down() {
		for(int x = 0; x < W; x++) {
			for(int y = H - 1; y >= 0; y--) {
				if(map[y][x] == 0)
					continue;
				
				int new_y = y;
				for(int ty = y + 1; ty < H; ty++) {
					if(map[ty][x] > 0) {
						break;
					}
					new_y = ty;
				}
				int temp = map[new_y][x];
				map[new_y][x] = map[y][x];
				map[y][x] = temp;
			}
		}
	}
	
	static void arrayCopy(int[][] temp, int[][] origin) {
		for(int y = 0; y < H; y++) {
			for(int x = 0; x < W; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for(int y = 0; y < H; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < W; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			result = getWalls();
			execute(0);
			System.out.println("#" + test + " " + result);
		}
	}

}
