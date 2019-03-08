package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//혁진이의 프로그램 검증(BFS)
public class SWEA_1824 {

	static int R, C;
	static char[][] map;
	static boolean[][][][] visited;
	static Queue<Info> q;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0}; //동남서북 순
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visited = new boolean[R][C][16][4];
			q = new LinkedList<>();
			
			for(int i=0; i<R; i++) {
				String row = br.readLine();
				for(int j=0; j<C; j++) {
					map[i][j] = row.charAt(j);
				}
			}
			
			q.add(new Info(0,0,0,0));
			visited[0][0][0][0] = true;
			
			String ans = "NO";
			loop:
			while(!q.isEmpty()) {
				int qSize = q.size();
				for(int i=0; i<qSize; i++) {
					Info p = q.poll();
					char order = map[p.x][p.y];
					int px = p.x;
					int py = p.y;
					int pm = p.m;
					int pd = p.d;
					if(order=='@') { //도착
						q.clear();
						ans = "YES";
						break loop;
					}else if(order=='?') {
						for(int d=0; d<4; d++) {
							int nx = px+dx[d];
							int ny = py+dy[d];
							if(nx<0) nx = R-1;
							if(nx>=R) nx = 0;
							if(ny<0) ny = C-1;
							if(ny>=C) ny = 0;
							if(!visited[nx][ny][pm][d]) {
								q.add(new Info(nx, ny, pm, d));
								visited[nx][ny][pm][d] = true;
							}
						}
					}else {
						if(order=='<'||order=='>'||order=='^'||order=='v') { //방향 바꿈
							if(order=='>') pd = 0;
							else if(order=='v') pd = 1;
							else if(order=='<') pd = 2;
							else if(order=='^') pd = 3;
						}else if(order=='_') {
							if(pm==0) pd = 0;
							else pd = 2;
						}else if(order=='|') {
							if(pm==0) pd = 1;
							else pd = 3;
						}else if(order=='.') {
							//암무 것도 안 함
						}else if('0'<=order&&order<='9') {
							pm = order-'0';
						}else if(order=='+') {
							pm++;
							if(pm>15) pm = 0;
						}else if(order=='-') {
							pm--;
							if(pm<0) pm = 15;
						}
						
						px = px+dx[pd];
						py = py+dy[pd];
						if(px<0) px = R-1;
						if(px>=R) px = 0;
						if(py<0) py = C-1;
						if(py>=C) py = 0;
						if(!visited[px][py][pm][pd]) {
							q.add(new Info(px, py, pm, pd));
							visited[px][py][pm][pd] = true;
						}
					}
					
				}
			}
			
			System.out.println("#"+T+" "+ans);
		}
	}

}
class Info{
	int x, y, m, d; //위치, 메모리, 방향
	Info(int x, int y, int m, int d){
		this.x = x;
		this.y = y;
		this.m = m;
		this.d = d;
	}
}