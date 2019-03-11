package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//탈출
public class BOJ_3055 {

	static int R, C;
	static char[][] map;
	static boolean[][] w_visited, s_visited;
	static int[]dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		w_visited = new boolean[R][C];
		s_visited = new boolean[R][C];
		Queue<Pos> wq = new LinkedList<>(); //물 큐
		Queue<Pos> sq = new LinkedList<>(); //고슴도치 큐
		for(int i=0; i<R; i++) {
			String row = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = row.charAt(j);
				if(map[i][j]=='*') {
					wq.add(new Pos(i, j));
					w_visited[i][j] = true;
				}else if(map[i][j]=='S') {
					sq.add(new Pos(i, j));
					s_visited[i][j] = true;
				}
			}
		}
		
		int time = 0;
		while(!sq.isEmpty()) {
			//물 먼저 처리
			int qSize = wq.size();
			for(int i=0; i<qSize; i++) {
				Pos p = wq.poll();
				for(int d = 0; d<4; d++) {
					int px = p.x+dx[d];
					int py = p.y+dy[d];
					if(px<0||px>=R||py<0||py>=C) continue;
					if(!w_visited[px][py] && map[px][py]!='D'&& map[px][py]!='X') {
						wq.add(new Pos(px, py));
						w_visited[px][py] = true;
						map[px][py] = '*';
					}
				}
			}
			//고슴도치 처리
			qSize = sq.size();
			for(int i=0; i<qSize;i++) {
				Pos p = sq.poll();
				if(map[p.x][p.y]=='D') { //비버 굴에 도착
					System.out.println(time);
					return;
				}
				for(int d = 0; d<4; d++) {
					int px = p.x+dx[d];
					int py = p.y+dy[d];
					if(px<0||px>=R||py<0||py>=C) continue;
					if(!s_visited[px][py] && map[px][py]!='*'&& map[px][py]!='X') {
						sq.add(new Pos(px, py));
						s_visited[px][py] = true;
					}
				}
			}
			time++;
		}
		
		//도착할 수 없음
		System.out.println("KAKTUS");
	}

}
class Pos{
	int x, y;
	Pos(int x, int y){
		this.x = x;
		this.y = y;
	}
}