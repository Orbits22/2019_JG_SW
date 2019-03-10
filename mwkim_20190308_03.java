import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mwkim_20190308_03 {
	static int N, result;
	static int[][] matrix;
	static int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	static ArrayList<int[]> particle;
	
	static void moving() {
		int time = 1;
		while(!particle.isEmpty() && time <= 4000) {
			ArrayList<int[]> crash = new ArrayList<int[]>();
			
			for(int i = 0; i < particle.size(); i++) {
				int[] temp = particle.get(i);
				
				int new_y = temp[0] + move[temp[2]][0];
				int new_x = temp[1] + move[temp[2]][1];
				
				if(new_y > 4000 || new_x > 4000 || new_y < 0 || new_x < 0)
					continue;
				
				if(matrix[new_y][new_x] > 0) {
					System.out.println((time/2) + "초: [" + new_y + "][" + new_x + "]: " + matrix[new_y][new_x]);
					matrix[new_y][new_x] += temp[3];
					matrix[temp[0]][temp[1]] = 0;
					temp[0] = new_y;
					temp[1] = new_x;
					crash.add(temp);
					particle.remove(i);
					i--;
				}
				else {
					matrix[new_y][new_x] = 1;
					matrix[temp[0]][temp[1]] = 0;
					temp[0] = new_y;
					temp[1] = new_x;
				}
			}
			
			for(int i = 0; i < crash.size(); i++) {
				int[] temp = crash.get(i);
				
				result += temp[3];
				matrix[temp[0]][temp[1]] = 0;
				crash.remove(i);
				i--;
			}
			
			time++;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			matrix = new int[4003][4003];
			particle = new ArrayList<int[]>();
			result = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int[] temp = new int[5];
				temp[1] = (Integer.parseInt(st.nextToken()) * 2) + 2000;
				temp[0] = (Integer.parseInt(st.nextToken()) * 2) + 2000;
				temp[2] = Integer.parseInt(st.nextToken());
				temp[3] = Integer.parseInt(st.nextToken());
				temp[4] = i + 1;
				
				particle.add(temp);
				matrix[temp[0]][temp[1]] = 1;
			}
			
			moving();
			
			System.out.println("#" + test + " " + result);
		}
	}

}
