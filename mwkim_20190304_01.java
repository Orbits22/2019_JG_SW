import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * <pre>
 * sw expert academy 2477
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190304_01 {
	static int A, B, K, result;
	static Executor[] N, M;
	static Customer[] customer;
	static ArrayList<Customer> A_waitList, B_waitList;
	
	static class Customer{
		int arrival; //도착시간
		int c_num; //고객번호
		int a_num; //창구 번호
		int b_num; //정비소 번호
		boolean a_fin; //창구 완료
		boolean b_fin; //정비 완료
		int wait; //정비창구 대기시간
		
		public Customer(int arrival, int c_num) {
			this.arrival = arrival;
			this.c_num = c_num;
			this.a_num = 0;
			this.b_num = 0;
			this.a_fin = false;
			this.b_fin = false;
			this.wait = 0;
		}
	}
	
	static class Compare implements Comparator<Customer>{
		@Override
		public int compare(Customer c1, Customer c2) {
			if(c1.b_num == 0 && c2.b_num == 0) {
				if(c1.wait < c2.wait) {
					return 1;
				}
				else if(c1.wait == c2.wait) {
					if(c1.a_num > c2.a_num) {
						return 1;
					}
					else if(c1.a_num == c2.a_num)
						return 0;
					else
						return -1;
				}
				else
					return -1;
			}
			else
				return 0;
		}
	}
	
	static class Executor {
		int customer; //처리중인 고객
		int elapsed; //처리시간
		int fin_time; //완료시간
		
		public Executor(int fin_time) {
			this.fin_time = fin_time;
			this.customer = -1;
			this.elapsed = -1;
		}
	}
	
	static void execute(int c_num) {
		int time = 0;
		ArrayList<Customer> fin = new ArrayList<Customer>();
		while(!A_waitList.isEmpty() || !B_waitList.isEmpty() || c_num <= K) {
			while(c_num <= K && customer[c_num].arrival == time) {
				if(!customer[c_num].a_fin) {
					A_waitList.add(customer[c_num]);
					c_num++;
				}
			}
			//System.out.println("[시각: " + time + "][c_num: " + c_num + "][A대기자수: " + A_waitList.size() + "][B대기자수: "+ B_waitList.size() + "][완료자수: " + fin.size() + "]");
			for(int i = 0; i < A_waitList.size(); i++) {
				Customer cust = A_waitList.get(i);
				//접수 창구
				if(!cust.a_fin && !cust.b_fin) {
					for(int a = 1; a < N.length; a++) {
						if(N[a].elapsed == -1 && cust.a_num == 0) {
							cust.a_num = a;
							N[a].elapsed = 0;
							N[a].customer = cust.c_num;
						}
						
						if(N[a].elapsed == N[a].fin_time && cust.a_num == a) {
							cust.a_fin = true;
							N[a].elapsed = -1;
							N[a].customer = -1;
							B_waitList.add(cust);
							A_waitList.remove(i);
							i--;
							//System.out.println(time + "초: [A 완료: " + cust.c_num + "(" + a +")]");
						}
					}
				}
			}
			Collections.sort(B_waitList, new Compare());
			for(int i = 0; i < B_waitList.size(); i++) {
				Customer cust = B_waitList.get(i);
				//정비 창구
				if(cust.a_fin && !cust.b_fin) {
					for(int b = 1; b < M.length; b++) {
						if(M[b].elapsed == -1 && cust.b_num == 0) {
							cust.b_num = b;
							M[b].elapsed = 0;
							M[b].customer = cust.c_num;
						}
						
						if(M[b].elapsed == M[b].fin_time && cust.b_num == b) {
							cust.b_fin = true;
							M[b].elapsed = -1;
							M[b].customer = -1;
							fin.add(cust);
							B_waitList.remove(i);
							i--;
							//System.out.println(time + "초: [B 완료: " + cust.c_num + "(" + b +")]");
						}
					}
				}
			}
			
			elapsed();
			time++;
		}
		
		for(int i = 0; i < fin.size(); i++) {
			//System.out.println("[고객 " + fin.get(i).c_num + "]: " + fin.get(i).a_num + " | " + fin.get(i).b_num);
			
			if(fin.get(i).a_num == A && fin.get(i).b_num == B) {
				result += fin.get(i).c_num;
			}
		}
	}
	
	static void elapsed() {
		//접수 창구
		for(int a = 1; a < N.length; a++) {
			if(N[a].elapsed != -1 && N[a].elapsed < N[a].fin_time) {
				N[a].elapsed++;
			}
		}
		
		//정비 창구
		for(int b = 1; b < M.length; b++) {
			if(M[b].elapsed != -1 && M[b].elapsed < M[b].fin_time) {
				M[b].elapsed++;
			}
		}
		
		for(int i = 0; i < B_waitList.size(); i++) {
			Customer cust = B_waitList.get(i);
			cust.wait++;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)	);
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = new Executor[Integer.parseInt(st.nextToken()) + 1];
			M = new Executor[Integer.parseInt(st.nextToken()) + 1];
			K = Integer.parseInt(st.nextToken());
			customer = new Customer[K + 1];
			A_waitList = new ArrayList<Customer>();
			B_waitList = new ArrayList<Customer>();
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int a = 1; a < N.length; a++) {
				N[a] = new Executor(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int b = 1; b < M.length; b++) {
				M[b] = new Executor(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			int end_c = 0;
			for(int c_num = 1; c_num <= K; c_num++) {
				int arrival = Integer.parseInt(st.nextToken());
				customer[c_num] = new Customer(arrival, c_num);
				if(arrival == 0) {
					A_waitList.add(customer[c_num]);
					end_c++;
				}
			}
			
			execute(end_c + 1);
			
			System.out.println("#" + test + " " + (result == 0 ? -1 : result));
		}
	}

}
