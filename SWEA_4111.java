package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//무선 단속 카메라
public class SWEA_4111 {

	static int N, K, arr[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine()); //단속 카메라 개수
			K = Integer.parseInt(br.readLine()); //수신기의 개수
			int len = 0;
			arr = new int[N]; //좌표들 저장
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			if(N>K) {
				Arrays.sort(arr); //오름차순으로 정렬
				PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
				for(int i=0; i<N-1; i++) {
					pq.add(arr[i+1]-arr[i]); //간격을 (내림차순으로)저장
				}
				len = arr[arr.length-1]-arr[0];
				for(int i=0; i<K-1; i++) {
					len-=pq.poll();
				}
			}
			System.out.println("#"+T+" "+len);
		}
	}

}
