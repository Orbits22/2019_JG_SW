package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//견우와 직녀
public class SWEA_4727 {

	static int N, M, map[][], ans, INF = 987654321;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1}; //북남서동
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ans = INF;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//bfs();//다리 안 놓을 때
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0&&check(i, j)) { //교차하지 않으면
					map[i][j] = M;
					bfs();
					map[i][j] = 0;
				}
			}
		}
		System.out.println(ans);
	}
	//최단 시간 구하기
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new Pos(0,0,0));
		visited[0][0] = true;
		int time = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				Pos p = q.poll();
				if(p.x==N-1&&p.y==N-1) {
					ans = Math.min(ans, time);
					return;
				}
				for(int d=0; d<4; d++) {
					int px = p.x+dx[d];
					int py = p.y+dy[d];
					if(px<0||px>=N||py<0||py>=N) continue;
					if(visited[px][py] || map[px][py]==0) continue;
					if(map[px][py]==1) {
						q.add(new Pos(px, py, 0));
						visited[px][py] = true;
					}else { //다리
						if((time+1)%map[px][py]==0) { //M의 배수일 때 건널 수 있음
							if(p.prev==0) { //바로 전에 오작교 안 건넜음(오작교 연속으로 건널 수 없다)
								q.add(new Pos(px, py, 1));
								visited[px][py] = true;
							}
						}else { //쉬는 시간
							q.add(p); //자기자신 넣어줌(대기))
						}
					}
				}
			}
			time++;
		}
	}
	//교차 판별
	static boolean check(int x, int y) {
		boolean[] dd = new boolean[4];
		for(int d=0; d<4; d++) {
			int px = x+dx[d];
			int py = y+dy[d];
			if(px<0||px>=N||py<0||py>=N) continue;
			if(map[px][py]!=1) dd[d] = true; //다리도 고려해야 함!!
		}
		if((dd[0]&&dd[2])||(dd[3]&&dd[0])||(dd[1]&&dd[3])||(dd[2]&&dd[1])) { //교차
			return false;
		}else return true;
	}

}
class Pos{
	int x, y, prev; //위치, 바로 전에 오작교 이용 여부
	Pos(int x, int y, int prev){
		this.x = x;
		this.y = y;
		this.prev = prev;
	}
}