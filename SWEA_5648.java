package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//원자 소멸 시뮬레이션
public class SWEA_5648 {

	static int N, ans;
	static List<A> list;
	static int[][] map; //충돌 여부 파악 위해 현재 원자들의 위치를 맵에 표시해야 함
	static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0}; //상하좌우
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		map = new int[4001][4001]; //좌표값 2배로
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine()); //원자 개수
			list = new ArrayList<>();
			list.add(null); //1번부터 시작하기 위해서 0번에 null값 넣어줌
			ans = 0;
			//맵 초기화
			for(int i=0; i<=4000; i++) {
				for(int j=0; j<=4000; j++) {
					map[i][j] = 0;
				}
			}
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken())*2+2000;
				int y = Integer.parseInt(st.nextToken())*2+2000;
				int d = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				list.add(new A(x,y,d,k,0));
				map[x][y] = i; //해당 원자 위치에 번호를 넣어줌
			}
			//시뮬레이션
			Queue<A> temp = new LinkedList<>(); //제거해야하는 원자
			for(int time=0; time<=4000; time++) {
				for(int i=1; i<=N; i++) {
					A a = list.get(i);
					if(a.r==1) continue; //이미 검증된 원자
					int px = a.x+dx[a.d];
					int py = a.y+dy[a.d];
					
					if(px<0||px>4000||py<0||py>4000) { //범위 벗어나면 영원히 소멸하지 않는 원자임
						a.r = 1; 
						continue;
					}
					if(map[px][py]==0) { //이동하려는 위치에 다른 원자 없음
						if(map[a.x][a.y]==i) map[a.x][a.y] = 0;
						map[px][py] = i;
						a.m = 1; //이번 turn에서 이동완료 했다는 표시
						a.x = px;
						a.y = py;
					}else {
						A a2 = list.get(map[px][py]);
						if(a2.m==0) { //아직 이동 안 한 원자이므로 충돌x
							if(map[a.x][a.y]==i) map[a.x][a.y] = 0;
							map[px][py] = i;
							a.m = 1;
							a.x = px;
							a.y = py;
						}else if(a2.m==1){ //충돌
							if(map[a.x][a.y]==i) map[a.x][a.y] = 0;
							a.m = 1;
							a.x = px;
							a.y = py;
							temp.add(a); //해당 원자 
							temp.add(a2); //같이 충돌한 원자
						}
					}
				}
				//충돌한 원자 제거
				while(!temp.isEmpty()) {
					A a = temp.poll();
					if(a.r==0) {
						map[a.x][a.y] = 0;
						a.r = 1;
						ans+=a.k;
					}
				}
				for(int i=1; i<=N; i++) {
					list.get(i).m = 0;
				}
			}
			System.out.println("#"+T+" "+ans);
		}
	}

}
class A{
	int x, y, d, k, m, r; //위치, 방향, 에너지, 이동완료 여부, 삭제 여부(범위 벗어났거나 충돌해서 소멸된 원자)
	A(int x, int y, int d, int k, int m){
		this.x = x;
		this.y = y;
		this.d = d;
		this.k = k;
		this.m = m;
	}
}