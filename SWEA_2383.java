package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//점심 식사 시간
public class SWEA_2383 {

	static int N, map[][], peopleNum, ans;
	static List<P> people;
	static S[] stair;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			people = new ArrayList<>();
			stair = new S[2];
			ans = Integer.MAX_VALUE;
			int sidx = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) { //사람
						people.add(new P(i, j));
					}else if(2<=map[i][j]&&map[i][j]<=10) { //계단
						stair[sidx++] = new S(i, j, map[i][j]);
					}
				}
			}
			peopleNum = people.size();
			//DFS로 사람마다 이용할 계단을 선택
			dfs(0);
			
			System.out.println("#"+T+" "+ans);
		}
	}
	static void dfs(int idx) {
		if(idx==peopleNum) { //모든 사람 선택 완료
			go();
			return;
		}
		P p = people.get(idx);
		for(int i=0; i<2; i++) { //0 또는 1번 계단 선택
			p.sn = i;
			dfs(idx+1);
		}
	}
	static void go() {
		for(P p:people) {
			p.e = 0;
			p.dist = Math.abs(stair[p.sn].x-p.x)+Math.abs(stair[p.sn].y-p.y);
		}

		int escape = 0; //탈출 인원
		int time = 0;
		while(true) {
			time++;
			//계단 update
			for(int i=0; i<2; i++) {
				S s = stair[i];
				for(int j=0; j<3; j++) {
					if(s.occupy[j]>0) {
						s.occupy[j]--;
						if(s.occupy[j]==0) escape++;
					}
				}
			}
			if(escape==peopleNum) break;
			//사람 update
			for(int i=0; i<peopleNum; i++) {
				P p = people.get(i);
				if(p.e==1) continue; //계단 내려가는 중이거나 이동 완료한 사람
				p.dist--;
				if(p.dist<0) {
					S s = stair[p.sn];
					for(int j=0; j<3; j++) {
						if(s.occupy[j]==0) {
							p.e=1;
							s.occupy[j]=s.len;
							break;
						}
					}
				}
			}
		}
		ans = Math.min(ans, time);
	}

}
class P{
	int x, y, sn, dist, e; //위치(x,y), 이용하는 계단, 현재 계단과의 거리, 탈출여부
	P(int x, int y){
		this.x = x;
		this.y = y;
	}
}
class S{
	int x, y, len; //위치(x,y), 계단의 길이
	int[] occupy = new int[3]; //계단이 비워지기까지 남은 시간 
	S(int x, int y, int len){
		this.x = x;
		this.y = y;
		this.len = len;
	}
}