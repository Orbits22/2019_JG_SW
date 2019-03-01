import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * <pre>
 * sw expert academy 2383
 * </pre>
 * @author 김명우
 *
 */
public class mwkim_20190304_03 {
	static int[][] people, stair;
	static int N, nP, result;
	
	static void down() {
		ArrayList<int[]> pList = new ArrayList<int[]>();
		for(int p = 0; p < nP; p++) {
			//System.out.println("[" + people[p][5] + "]: [" + people[p][0] + "][" + people[p][1] + "] - " + people[p][2] + " :::: " + people[p][3] + " ::::" + people[p][4]);
			pList.add(people[p].clone());
		}
		int time = 0;
		ArrayList<int[]> stair1 = new ArrayList<int[]>();
		ArrayList<int[]> stair2 = new ArrayList<int[]>();
		while(true) {	
			//계단 내려가는 사람들
			for(int i = 0; i < stair1.size(); i++) {
				int[] pp = stair1.get(i);
				if(pp[1] == 0) {
					stair1.remove(i);
					i--;
					//System.out.println(time + "분: [" + pp[0] + "] 1번계단 종료!!!");
				}
				else {
					pp[1]--;
				}
			}
			for(int i = 0; i < stair2.size(); i++) {
				int[] pp = stair2.get(i);
				if(pp[1] == 0) {
					stair2.remove(i);
					i--;
					//System.out.println(time + "분: [" + pp[0] + "] 2번계단 종료!!!");
				}
				else {
					pp[1]--;
				}
			}
			
			for(int p = 0; p < pList.size(); p++) {
				int[] people = pList.get(p);
				//계단까지 도착 못한 사람
				if(people[3] > 0)
					people[3]--;
				//계단에 막 도착한 사람
				else {
					int[] temp = new int[2];
					temp[0] = people[5];
					if(people[4] == 0)
						temp[1] = stair[people[2]][2]; //대기 없이 바로 내려가기
					else
						temp[1] = stair[people[2]][2] - 1; //대기시간 없었으면 바로 내려가기 
					//(-1을 해주는 이유는 계단 내려가는 것을 아래에 구현해서 loop한번 더 실행하기 때문)
					//1번계단 선택한 사람
					if(people[2] == 0) {
						//계단 대기타는 사람
						if(stair1.size() >= 3) {
							people[4]++;
						}
						//계단 내려가고 있는 사람
						else {
							stair1.add(temp);
							pList.remove(p);
							p--;
							//System.out.println(time + "분: [" + people[5] + "] 1번계단 시작");
						}
					}
					//2번계단 선택한 사람
					else {
						if(stair2.size() >= 3) {
							people[4]++;
						}
						else {
							stair2.add(temp);
							pList.remove(p);
							p--;
							//System.out.println(time + "분: [" + people[5] + "] 2번계단 시작");
						}
					}
				}
			}
			
			if(stair1.isEmpty() && stair2.isEmpty() && pList.isEmpty())
				break;
			time++;
		}
		result = Math.min(result, time);
		//System.out.println("=================");
	}
	
	static void selectStair(int cur_p) {
		if(cur_p == nP) {
			down();
			return;
		}
		for(int s = 0; s < 2; s++) {
			people[cur_p][2] = s;
			people[cur_p][3] = Math.abs(people[cur_p][0] - stair[s][0]) + Math.abs(people[cur_p][1] - stair[s][1]);
			selectStair(cur_p + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(st.nextToken());
			people = new int[10][6]; //y, x, 계단 index, 계단까지 남은시간, 계단 대기 시간, 고유번호
			stair = new int[2][3]; //y, x, 계단 소요시간
			nP = 0;
			int nS = 0;
			
			for(int y = 1; y <= N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 1; x <= N; x++) {
					int temp = Integer.parseInt(st.nextToken());
					
					if(temp == 1) {
						people[nP][0] = y;
						people[nP][1] = x;
						people[nP][2] = 0;
						people[nP][3] = 0;
						people[nP][4] = 0;
						people[nP][5] = nP + 1;
						nP++;
					}
					else if(temp > 1) {
						stair[nS][0] = y;
						stair[nS][1] = x;
						stair[nS][2] = temp;
						nS++;
					}
				}
			}
			
			selectStair(0);
			System.out.println("#" + test + " " + result);
		}
	}

}
