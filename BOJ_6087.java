package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//레이저 통신
public class BOJ_6087 {

	static int W, H, sx, sy, ex, ey;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new boolean[H][W];
		sx = sy = ex = ey = -1;
		Queue<Pos> q = new LinkedList<>();
		for(int i=0; i<H; i++) {
			String row = br.readLine();
			for(int j=0; j<W; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j]=='C') {
					if(sx==-1) { //시작점
						sx = i;
						sy = j;
					}else { //도착점
						ex = i;
						ey = j;
					}
				}
			}
		}
		visited[sx][sy] = true;
		for(int d=0; d<4; d++) {
			for(int k=1; k<=100; k++) {
				int px = sx+dx[d]*k;
				int py = sy+dy[d]*k;
				if(px<0||px>=H||py<0||py>=W) break;
				if(map[px][py]=='*') break;
				q.add(new Pos(px, py));
				visited[px][py] = true;
			}
		}
		int turn = 0;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				Pos p = q.poll();
				if(p.x==ex&&p.y==ey) { //도착
					System.out.println(turn);
					return;
				}
				for(int d=0; d<4; d++) {
					for(int k=1; k<=100; k++) {
						int px = p.x+dx[d]*k;
						int py = p.y+dy[d]*k;
						if(px<0||px>=H||py<0||py>=W) break;
						if(map[px][py]=='*') break;
						if(!visited[px][py]) {
							q.add(new Pos(px, py));
							visited[px][py] = true;
						}
					}
				}
			}
			turn++;
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