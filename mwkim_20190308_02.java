import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * SWEA 5644 무선충전
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190308_02 {
	static int M, A;
	static int[][] user;
	static int[][] move = {{0, 0}, {-1 ,0}, {0, 1}, {1, 0}, {0, -1}}; //움직이지 않음, 상우하좌
	static int[][] cmd;
	static BC[][] bc;
	static int conn[];
	
	static class BC{
		int[] P;
		
		public BC() {
			this.P = new int[A + 1];
		}
		
		public void add(int P, int index) {
			this.P[index] = P;
		}
		
		public int[] getIndex() {
			int[] temp = new int[A + 1];
			
			for(int i = 1; i <= A; i++) {
				if(this.P[i] > 0)
					temp[i] = i;
			}
			
			return temp;
		}
		
		public int getP(int index) {
			return this.P[index] / (conn[index] == 0 ? 1 : conn[index]);
		}
	}
	
	static void moving() {
		int time = 0;
		while(time <= M) {
			//이동과 BC 접속
			int[][] temp = new int[2][2];
			for(int p = 0; p < 2; p++) {
				user[p][0] += move[cmd[p][time]][0];
				user[p][1] += move[cmd[p][time]][1];
				
				//접속 가능한 모든 BC에 대한 Index Get
				temp[p] = bc[user[p][0]][user[p][1]].getIndex();
			}
			int[] max = new int[2];
			//모든 BC에 대해 최대값 GET
			for(int a = 1; a <= A; a++) {
				for(int b = 1; b <= A; b++) {
					conn[temp[0][a]]++;
					conn[temp[1][b]]++;
					int tempA = bc[user[0][0]][user[0][1]].getP(temp[0][a]);
					int tempB = bc[user[1][0]][user[1][1]].getP(temp[1][b]);
					if(tempA + tempB > max[0] + max[1]) {
						max[0] = tempA;
						max[1] = tempB;
					}
					conn[temp[0][a]]--;
					conn[temp[1][b]]--;
				}
			}
			
			//선택한 BC에 대해 P 더하기
			for(int p = 0; p < 2; p++) {
				user[p][2] += max[p];
			}
			
			time++;
		}
	}
	
	static void setRange(int cur_y, int cur_x, int range, int P, int index) {
		boolean[][] visit = new boolean[11][11];
		visit[cur_y][cur_x] = true;
		bc[cur_y][cur_x].add(P, index);
		
		for(int k = 1; k <= range; k++) {
			for(int limit = 0; limit <= k; limit++) {
				for(int dy = 1; dy < 5; dy++) {
					int new_y = cur_y + (move[dy][0] * (k - limit));
					if(new_y > 10 || new_y <= 0)
						continue;
					
					for(int dx = 1; dx < 5; dx++) {
						int new_x = cur_x + (move[dx][1] * limit);
						if(new_x > 10 || new_x <= 0)
							continue;
						
						visit[new_y][new_x] = true;
						bc[new_y][new_x].add(P, index);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			cmd = new int[2][M + 1];
			bc = new BC[11][11];
			user = new int[2][3]; //y좌표, x좌표, 충전량
			conn = new int[A + 1];
			user[0][0] = 1;
			user[0][1] = 1;
			user[1][0] = 10;
			user[1][1] = 10;
			
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 1; c <= M; c++) {
					cmd[i][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int y = 1; y <= 10; y++) {
				for(int x = 1; x <= 10; x++) {
					bc[y][x] = new BC();
				}
			}
			
			for(int i = 1; i <= A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				setRange(y, x, c, p, i);
			}
			
			moving();
			
			System.out.println("#" + test + " "  + (user[0][2] + user[1][2]));
		}
	}

}
