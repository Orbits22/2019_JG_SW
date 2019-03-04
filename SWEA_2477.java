package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//차량 정비소
public class SWEA_2477 {

	static int N, M, K, A, B, a[], b[], aa[], bb[], ans;
	static Queue<P> q;
	static PriorityQueue<P> pq;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		pq = new PriorityQueue<>(new Comparator<P>() {

			@Override
			public int compare(P o1, P o2) {
				if(o1.t==o2.t) {
					return o1.an-o2.an;
				}else return o1.t-o2.t;
			}
		});
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //접수 창구 개수
			M = Integer.parseInt(st.nextToken()); //정비 창구 개수
			K = Integer.parseInt(st.nextToken()); //고객 수
			A = Integer.parseInt(st.nextToken()); //찾고있는 고객이 이용한 접수창구 번호
			B = Integer.parseInt(st.nextToken()); //찾고있는 고객이 이용한 정비창구 번호
			a = new int[N+1]; //접수창구별 처리시간
			b = new int[M+1]; //정비창구별 처리시간
			aa = new int[N+1]; //접수창구별 처리완료까지 남은시간
			bb = new int[M+1]; //정비창구별 처리완료까지 남은시간
			q = new LinkedList<>();
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=K; i++) {
				int t = Integer.parseInt(st.nextToken());
				q.add(new P(i, t));
			}
			
			go();
			
			if(ans==0) ans = -1;
			System.out.println("#"+T+" "+ans);
		}
	}
	static void go() {
		while(!q.isEmpty()) {
			//창구
			for(int i=1; i<=N; i++) {
				if(aa[i]>0) aa[i]--;
			}
			for(int i=1; i<=M; i++) {
				if(bb[i]>0) bb[i]--;
			}
			//사람
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				P p = q.poll();
				if(p.t<=0&&p.an==0) { //도착했고 접수 창구 이용 전
					for(int j=1; j<=N; j++) {
						if(aa[j]==0) { //비어있는 접수 창구
							aa[j] = a[j];
							p.an = j;
							p.t = a[j];
							break;
						}
					}
				}
				p.t--;
				if(p.t<=0&&p.an!=0) { //접수 창구 이용완료. 정비창구 이용할 차례
					pq.add(p);
				}else {
					q.add(p);
				}
			}
			//정비창구 처리
			while(!pq.isEmpty()) {
				P p = pq.poll();
				boolean ok = false;
				for(int i=1; i<=M; i++) {
					if(bb[i]==0) {
						bb[i] = b[i];
						p.bn = i;
						if(p.an==A&&p.bn==B) ans+=p.n;
						ok = true;
						break;
					}
				}
				if(!ok)q.add(p);
			}
		}
	}

}
class P{
	int n, t, an, bn; //고객번호, 대기시간, 접수창구, 정비창구
	P(int n, int t){
		this.n= n; //고객번호
		this.t = t; //도착시간
	}
}