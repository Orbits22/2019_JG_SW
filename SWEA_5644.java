/*package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//무선 충전
public class SWEA_5644 {

	static int M, A, move_a[], move_b[];
	static int[] dx = {0,-1,0,1,0}, dy = {0,0,1,0,-1};
	static Pos a, b; //a와 b의 현재 위치
	static BC[] bc;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); //총 이동 시간
			A = Integer.parseInt(st.nextToken()); //BC의 개수
			move_a = new int[M]; //A의 이동 정보
			move_b = new int[M]; //B의 이동 정보
			bc = new BC[A+1];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				move_a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				move_b[i] = Integer.parseInt(st.nextToken());
			}
			for(int i=1; i<=A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[i] = new BC(x, y, c, p);
			}
			a = new Pos(1,1);
			b = new Pos(10,10);
			
			//시뮬레이션
			int sum = 0;
			for(int time=0; time<=M; time++) {
				//두 명의 사용자가 BC 선택하는 경우 완탐
				int max = 0; //현재 time에 최대 충전량
				for(int i=1; i<=A; i++) { //a가 접속할 bc 선택
					for(int j=1; j<=A; j++) { //b가 접속할 bc 선택
						int dist_a = Math.abs(bc[i].x-a.x)+Math.abs(bc[i].y-a.y);
						int dist_b = Math.abs(bc[j].x-b.x)+Math.abs(bc[j].y-b.y);
						boolean connect_a = (dist_a<=bc[i].c)?true:false;
						boolean connect_b = (dist_b<=bc[j].c)?true:false;
						if(connect_a && connect_b) { //둘 다 접속 가능
							if(i==j) max = Math.max(max, bc[i].p); //같은 bc에 접속
							else max = Math.max(max, bc[i].p+bc[j].p); //다른 bc에 접속
						}else if(connect_a){ //a만 접속 가능
							max = Math.max(max, bc[i].p);
						}else if(connect_b){ //b만 접속 가능
							max = Math.max(max, bc[j].p);
						}
					}
				}
				sum+=max;
				if(time==M) break;
				//이동
				a.x+=dx[move_a[time]];
				a.y+=dy[move_a[time]];
				b.x+=dx[move_b[time]];
				b.y+=dy[move_b[time]];
			}
			
			System.out.println("#"+T+" "+sum);
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
class BC{
	int x, y, c, p;
	BC(int x, int y, int c, int p){
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}*/