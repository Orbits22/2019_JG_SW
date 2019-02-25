package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//줄기세포배양
public class SWEA_5653 {

	static int N, M, K, map[][];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static PriorityQueue<Cell> pq;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>(new Comparator<Cell>() { //살아있는 세포들

			@Override
			public int compare(Cell o1, Cell o2) {
				if(o1.tt>o2.tt) return 1; //큐 삽입시간 오름차순 정렬
				else if(o1.tt==o2.tt) return o2.l-o1.l; //같으면 생명력 수치 순으로 정렬
				else return -1;
			}
		});
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //세로 크기
			M = Integer.parseInt(st.nextToken()); //가로 크기
			K = Integer.parseInt(st.nextToken()); //배양 시간
			map = new int[2*K+N][2*K+M];
			pq.clear();
			for(int i=K; i<K+N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=K; j<K+M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]!=0) pq.add(new Cell(i, j, map[i][j], 0, 0, 0));
				}
			}
					
			for(int time=1; time<=K; time++) {
				int qSize = pq.size();
				for(int i=0; i<qSize; i++) {
					Cell c = pq.poll();
					if(c.s==1&&c.t==0) { //활성 상태되고 한 시간 후
						//네 방향으로 번식
						for(int d=0; d<4; d++) {
							int px = c.x+dx[d];
							int py = c.y+dy[d];
							if(map[px][py]==0) { //번식 가능
								map[px][py] = c.l;
								pq.add(new Cell(px, py, c.l, 0, 0, c.tt+1));
							}
						}
					}
					c.t += 1;
					if(c.t==c.l) { //상태 변화
						c.s += 1;
						c.t = 0;
					}
					if(c.s!=2) { //죽지 않았으면 다시 큐에 넣어줌
						c.tt += 1;
						pq.add(c);
					}
				}
			}
			
			System.out.println("#"+tc+" "+pq.size());
		}
	}

}
class Cell{
	int x, y, l, t, s, tt; //위치, 생명력, 상태 변화 후 지난 시간, 상태(0:비활 1:활 2:죽음), 큐 삽입 시간
	Cell(int x, int y, int l, int t, int s, int tt){
		this.x = x;
		this.y = y;
		this.l = l;
		this.t = t;
		this.s = s;
		this.tt = tt;
	}
}