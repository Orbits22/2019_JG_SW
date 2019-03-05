package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//활주로 건설
public class SWEA_4014 {

	static int N, X, map[][], ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			count_h();
			count_v();
			
			System.out.println("#"+T+" "+ans);
		}
	}
	private static void count_h() {
		for(int i=0; i<N; i++) {
			int h = map[i][0];
			int cnt = 1; //동일 높이 블록수 카운팅
			boolean ok = true;
			boolean low = false;
			for(int j=1; j<N; j++) {
				if(map[i][j]==h) cnt++;
				else {
					if(Math.abs(map[i][j]-h)>1) {
						ok = false;
						break;
					}
					if(map[i][j]>h) { //더 높아짐
						if(cnt<X) {
							ok = false;
							break;
						}
						if(low) {
							if(cnt<2*X) {
								ok = false;
								break;
							}
						}
						low = false;
						cnt = 1;
						h = map[i][j];
					}else { //더 낮아짐
						if(low) {
							if(cnt<X) {
								ok = false;
								break;
							}
						}
						cnt = 1;
						low = true;
						h = map[i][j];
					}
				}
			}
			if(low&&cnt<X) ok = false;
			if(ok) {
				//System.out.println(i+" 가로");
				ans++;
			}
		}
	}

	private static void count_v() {
		for(int j=0; j<N; j++) {
			int h = map[0][j];
			int cnt = 1; //동일 높이 블록수 카운팅
			boolean ok = true;
			boolean low = false;
			for(int i=1; i<N; i++) {
				if(map[i][j]==h) cnt++;
				else {
					if(Math.abs(map[i][j]-h)>1) {
						ok = false;
						break;
					}
					if(map[i][j]>h) { //더 높아짐
						if(cnt<X) {
							ok = false;
							break;
						}
						if(low) {
							if(cnt<2*X) {
								ok = false;
								break;
							}
						}
						low = false;
						cnt = 1;
						h = map[i][j];
					}else { //더 낮아짐
						if(low) {
							if(cnt<X) {
								ok = false;
								break;
							}
						}
						cnt = 1;
						low = true;
						h = map[i][j];
					}
				}
			}
			if(low&&cnt<X) ok = false;
			if(ok) {
				//System.out.println(j+" 세로");
				ans++;
			}
		}
	}
}
