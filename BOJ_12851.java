package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//숨바꼭질2
public class BOJ_12851 {

	static int N, K, MAX=100000;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //수빈이 위치
		K = Integer.parseInt(st.nextToken()); //동생의 위치
		visited = new boolean[MAX+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		int time = 0;
		int ans = 0;
		boolean go = true;
		while(!q.isEmpty()) {
			int qSize = q.size();
			for(int i=0; i<qSize; i++) {
				int x = q.poll();
				if(x==K) {
					go = false;
					ans++;
				}
				visited[x] = true;
				if(x-1>=0&&!visited[x-1]) {
					q.add(x-1);
				}
				if(x+1<=MAX&&!visited[x+1]) {
					q.add(x+1);
				}
				if(x*2<=MAX&&!visited[x*2]) {
					q.add(x*2);
				}
			}
			if(!go) break;
			time++;
		}
		System.out.println(time);
		System.out.println(ans);
	}

}
