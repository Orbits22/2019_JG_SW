package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//사랑의 카운슬러
public class SWEA_1494 {

	static int N;
	static long ans, INF=Long.MAX_VALUE, sx, sy;
	static Pos[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			arr = new Pos[N]; //전체 지렁이의 위치
			ans = INF;
			sx = sy = 0; //모든 x, 모든 y 더한 값
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[i] = new Pos(x, y);
				sx += x;
				sy += y;
			}
			comb(0, 0, 0, 0);
			System.out.println("#"+T+" "+ans);
		}
	}
	static void comb(int idx, int cnt, long x, long y) { //조합
		if(cnt==N/2) {
			long vx = (sx-x)-x;
			long vy = (sy-y)-y;
			ans = Math.min(ans, vx*vx+vy*vy);
			return;
		}
		for(int i=idx; i<N; i++) {
			comb(i+1, cnt+1, x+arr[i].x, y+arr[i].y);
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