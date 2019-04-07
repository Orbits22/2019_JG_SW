import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ16918: 봄버맨
 * @author 김명우
 *
 */
public class mwkim_20190408_01 {
	static int R, C, N;
	static int[][] map;
	static boolean[][] bomb;
	static int[][] range = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	static void install() {
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(!bomb[y][x]) {
					bomb[y][x] = true;
					map[y][x] = 3;
				}
			}
		}
	}
	
	static void decreaseTime() {
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(bomb[y][x]) {
					if(map[y][x] > 0)
						map[y][x]--;
				}
			}
		}
	}
	
	static void explosion() {
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(bomb[y][x]) {
					if(map[y][x] == 0) {
						for(int d = 0; d < 4; d++) {
							int new_y = y + range[d][0];
							int new_x = x + range[d][1];
							
							if(new_y >= R || new_x >= C || new_y < 0 || new_x < 0)
								continue;
							
							if(bomb[new_y][new_x] && map[new_y][new_x] == 0)
								continue;
							
							map[new_y][new_x] = 0;
							bomb[new_y][new_x] = false;
						}
						map[y][x] = 0;
						bomb[y][x] = false;
					}
				}
			}
		}
	}
	
	static void running() {
		int time = 0;
		
		do {
			if(time % 2 == 0 && time > 0)
				install();
			explosion();
			decreaseTime();
			
			time++;
		}while(time <= N);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		char[][] tmp_map = new char[R][C];
		bomb = new boolean[R][C];
		map = new int[R][C];
		for(int y = 0; y < R; y++) {
			String temp = br.readLine();
			tmp_map[y] = temp.toCharArray();
			for(int x = 0; x < C; x++) {
				if(tmp_map[y][x] == 'O') {
					map[y][x] = 3;
					bomb[y][x] = true;
				}
			}
		}
		
		running();
		
		for(int y = 0; y < R; y++) {
			for(int x = 0; x < C; x++) {
				if(bomb[y][x])
					System.out.print("O");
				else
					System.out.print(".");
			}
			System.out.println("");
		}
	}

}
