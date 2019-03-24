/*package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//소문난 칠공주
public class BOJ_1941 {

	static int N, ans;
	static char[][] map;
	static boolean[][] selected;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		map = new char[N][N];
		selected = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String row = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		go(0,0,0,0);
		System.out.println(ans);
	}
	static void go(int x, int y, int cnt, int scnt) {
		if(cnt==7) {
			if(scnt>=4) {
				if(connected(x, y)) ans++;
			}
			return;
		}
		for(int i=x; i<N; i++) {
			for(int j=y; j<N; j++) {
				if(selected[i][j]) continue;
				selected[i][j] = true;
				if(map[i][j]=='S') {
					go(i, j, cnt+1, scnt+1);
				}else {
					go(i, j, cnt+1, scnt);
				}
				selected[i][j] = false;
			}
			y = 0;
		}
	}
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean connected(int x, int y) {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.add(new Pos(x, y));
		visited[x][y] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int d=0; d<4; d++) {
				int px = p.x+dx[d];
				int py = p.y+dy[d];
				if(px<0||px>=N||py<0||py>=N) continue;
				if(selected[px][py]&&!visited[px][py]) {
					q.add(new Pos(px, py));
					visited[px][py] = true;
					cnt++;
				}
			}
		}
		return (cnt==7)?true:false; 
	}

}
class Pos{
	int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}*/