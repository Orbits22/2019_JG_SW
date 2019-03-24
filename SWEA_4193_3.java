package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//수영대회 결승전
public class SWEA_4193_3 {

	static int N, map[][], A, B, C, D, ans;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			bfs();
			System.out.println("#"+T+" "+ans);
		}
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new Pos(A, B));
		visited[A][B] = true;
		int time = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				Pos p = q.poll();
				if(p.x==C&&p.y==D) { //도착
					ans = time;
					return;
				}
				for(int d=0; d<4; d++) {
					int px = p.x+dx[d];
					int py = p.y+dy[d];
					if(px<0||px>=N||py<0||py>=N) continue;
					if(visited[px][py]||map[px][py]==1) continue;
					if(map[px][py]==0) {
						q.add(new Pos(px, py));
						visited[px][py] = true;
					}else { //소용돌이
						if(time%3==2) { //사라짐
							q.add(new Pos(px, py));
							visited[px][py] = true;
						}else { //안 사라짐
							q.add(p); //자기 자신을 넣는다(대기)
						}
					}
				}
			}
			time++;
		}
		ans = -1; //도착 할 수 없음
	}
}
class Pos{
	int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}