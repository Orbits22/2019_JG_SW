package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//탈주범 검거
public class SWEA_1953 {

	static int[][] dx = {{-1,1,0,0},{-1,1,0,0},{0,0,0,0},
						 {-1,0,0,0},{0,1,0,0},{0,1,0,0},{-1,0,0,0}};
	static int[][] dy = {{0,0,-1,1},{0,0,0,0},{0,0,-1,1},
						{0,0,0,1},{0,0,0,1},{0,0,-1,0},{0,0,-1,0}};
	static int N, M, R, C, L, map[][], visited[][];
	static Queue<Pos> q = new LinkedList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			q.clear();
			q.add(new Pos(R, C));
			visited[R][C] = 1;
			int cnt = 1;
			for(int t=2; t<=L; t++) {
				int qSize = q.size();
				for(int i=0; i<qSize; i++) {
					Pos p = q.poll();
					int num = map[p.x][p.y]; //터널 번호
					for(int d=0; d<4; d++) {
						int px = p.x+dx[num-1][d];
						int py = p.y+dy[num-1][d];
						if(px<0||px>=N||py<0||py>=M) continue;
						if(visited[px][py]==0) {
							if(map[px][py]==0) continue;
							int num2 = map[px][py]; //연결여부 파악할 칸의 터널 번호
							int d2 = (d==0)?1:(d==1)?0:(d==2)?3:2; //확인해야할 방향
							if(dx[num2-1][d2]==0&&dy[num2-1][d2]==0) continue; //연결되지 않음
							//연결되면
							q.add(new Pos(px, py));
							visited[px][py] = 1;
							cnt++;
						}
					}
				}
			}
			System.out.println("#"+T+" "+cnt);
		}
	}

}
class Pos{
	int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}