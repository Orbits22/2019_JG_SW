import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * sw expert academy 5653
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190225_02 {
	static int N, M, K, result;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북 동 남 서
	static Cell[][] cells;

	static class Cell {
		int state; //-1 죽음, 0 비활성, 1 활성
		int y, x;
		int life;
		int time; // 몇시 탄생인지
		
		public Cell(int y, int x, int life, int time) {
			this.y = y;
			this.x = x;
			this.life = life;
			this.state = 0;
			this.time = time;
		}
	}
	
	//1: 0시 비활성화, 1시 활성, 2시 죽음
	//2: 0~1시 비활성화, 2~3시 활성, 4시 죽음
	//3: 0~2시 비활성화, 3~5시 활성, 6시 죽음
	//4: 0~3시 비활성화, 4~7시 활성, 8시 죽음
	static void simulation() {
		for(int time = 0; time <= K; time++) {
			for(int y = 0; y < 2 * K + N; y++) {
				for(int x = 0; x < 2 * K + M; x++) {
					if(cells[y][x] == null)
						continue;
					
					if(cells[y][x].life == 0)
						continue;
					
					switch(cells[y][x].state) {
					case 0:
						if(time - cells[y][x].time >= cells[y][x].life)
							cells[y][x].state = 1;
						
						break;
					case 1:
						for(int d = 0; d < 4; d++) {
							int new_y = y + move[d][0];
							int new_x = x + move[d][1];
							
							if(cells[new_y][new_x] != null) {
								if(cells[new_y][new_x].time == time) {
									if(cells[new_y][new_x].life < cells[y][x].life) {
										cells[new_y][new_x].life = cells[y][x].life;
										cells[new_y][new_x].time = time;
									}
								}
								continue;
							}
							cells[new_y][new_x] = new Cell(new_y, new_x, cells[y][x].life, time);
						}
						if(time - cells[y][x].time >= (cells[y][x].life * 2))
							cells[y][x].state = -1;
						
						break;
					}
				}
			}
		}
		
		for(int y = 0; y < 2 * K + N; y++) {
			for(int x = 0; x < 2 * K + M; x++) {
				if(cells[y][x] == null)
					continue;
				
				if(cells[y][x].life == 0)
					continue;
				
				if(cells[y][x].state == 0 || cells[y][x].state == 1)
					result++;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cells = new Cell[2 * K + N][2 * K + M];
			result = 0;
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < M; x++) {
					int life = Integer.parseInt(st.nextToken());
					if(life != 0) {
						cells[y + ((2 * K + N) / 2)][x + ((2 * K + M) / 2)] = new Cell(y, x, life, 0);
					}
				}
			}
			simulation();
			System.out.println("#" + test + " " + result);
		}
	}

}
