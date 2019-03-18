package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

//보물상자 비밀번호
public class SWEA_5658 {

	static int N, K;
	static String nums;
	static Set<String> set = new HashSet<>(); //중복 제거하기 위한 set
	static PriorityQueue<String> pq = new PriorityQueue<>(); //정렬시키기 위한 pq
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //숫자의 개수
			K = Integer.parseInt(st.nextToken()); //크기 순서(K번째로 큰수 구함)
			set.clear();
			pq.clear();
			nums = br.readLine();	
			int m = N/4; //한 변에 있는 숫자 개수
			for(int i=0; i<m; i++) {
				//각 변의 숫자 구함 -> set에 넣기
				for(int j=0; j<4; j++) { //각 변에서 숫자 뽑아내기
					set.add(nums.substring(m*j, m*j+m));
				}
				//시계방향으로 한칸씩 회전
				char temp = nums.charAt(nums.length()-1);
				nums = nums.substring(0, nums.length()-1);
				nums = temp + nums;
			}
			//pq로 정렬
			for(String s:set) {
				pq.add(s);
			}
			//K번째로 큰수 == N-K+1번째로 작은 수
			int n = set.size()-K+1;
			for(int i=0; i<n-1; i++) {
				pq.poll();
			}
			String s = pq.poll(); ////n번째로 뽑히는 수가 정답(16진수)
			//10진수로 변환
			int idx = m-1;
			int ans = 0;
			for(int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				if('0'<=c&&c<='9') {
					ans+=(c-'0')*Math.pow(16, idx);
				}else {
					ans+=(c-'A'+10)*Math.pow(16, idx);
				}
				idx--;
			}
			System.out.println("#"+T+" "+ans); 
		}
	}

}
