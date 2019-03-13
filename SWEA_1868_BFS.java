package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//파핑파핑 지뢰찾기
public class SWEA_1868_BFS {

	static int N, nums[][];
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,-1,-1,0,0,1,1,1}, dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			nums = new int[N][N];
			visited = new boolean[N][N];
			Queue<Pos> q = new LinkedList<>();
			int bomb = 0; //총 지뢰 개수
			//입력
			for(int i=0; i<N; i++) {
				String row = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = row.charAt(j);
					if(map[i][j]=='*') bomb++;
				}
			}
			//주변 지뢰 카운트
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]=='*') { //지뢰일 경우
						nums[i][j] = -1;
						continue;
					}
					int cnt = 0; //주변 지뢰 개수
					for(int d=0; d<8; d++) {
						int px = i+dx[d];
						int py = j+dy[d];
						if(px<0||px>=N||py<0||py>=N) continue;
						if(map[px][py]=='*') cnt++;
					}
					nums[i][j] = cnt;
				}
			}
			
			int click = 0;
			int cnt = 0; //한꺼번에 터지는 칸의 개수
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(nums[i][j]==0 && !visited[i][j]) {
						q.add(new Pos(i, j));
						visited[i][j] = true;
						click++;
						cnt++;
						while(!q.isEmpty()) {
							Pos p = q.poll();
							if(nums[p.x][p.y]!=0) continue;
							for(int d=0; d<8; d++) {
								int px = p.x+dx[d];
								int py = p.y+dy[d];
								if(px<0||px>=N||py<0||py>=N) continue;
								if(!visited[px][py]&&map[px][py]!='*') {
									q.add(new Pos(px, py));
									visited[px][py] = true;
									cnt++;
								}
							}
						}
					}
				}
			}
			System.out.println("#"+T+" "+(N*N-bomb-cnt+click));
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