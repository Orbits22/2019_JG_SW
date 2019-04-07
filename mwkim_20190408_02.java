import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BJ_16637: 괄호 추가하기
 * @author 김명우
 *
 */
public class mwkim_20190408_02 {
	static int N;
	static int result;
	static int[] operand;
	static int[] operator; //0: +, 1: -, 2: *
	static boolean[] bracket, checked;
	
	static void addBracket(int index) {
		if(index >= N / 2) {
			Queue<Integer> operandQ = new LinkedList<Integer>();
			
			for(int i = 0; i < N / 2 + 1; i++) {
				if(bracket[i]) {
					int temp = operand[i];
					switch(operator[i]) {
					case 0:
						temp += operand[i + 1];
						break;
					case 1:
						temp -= operand[i + 1];
						break;
					case 2:
						temp *= operand[i + 1];
						break;
					}
					operandQ.add(temp);
					i++;
				}
				else {
					operandQ.add(operand[i]);
				}
			}
			
			int value = operandQ.poll();
			for(int i = 0; i < N / 2; i++) {
				if(bracket[i])
					continue;
				else {
					int tmp = operandQ.poll();
					switch(operator[i]) {
					case 0:
						value += tmp;
						break;
					case 1:
						value -= tmp;
						break;
					case 2:
						value *= tmp;
						break;
					}
				}
			}

			result = Math.max(result, value);
			return;
		}
		
		if(checked[index]) {
			if(bracket[index])
				addBracket(index + 2);
			else
				addBracket(index + 1);
			return;
		}
		
		if(index - 1 >= 0 && bracket[index - 1]) {
			addBracket(index + 1);
			return;
		}
		
		checked[index] = true;
		bracket[index] = true;
		addBracket(index + 2);
		bracket[index] = false;
		addBracket(index + 1);
		checked[index] = false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		operand = new int[N / 2 + 1];
		operator = new int[N / 2];
		bracket = new boolean[N / 2 + 1];
		checked = new boolean[N / 2];
		result = Integer.MIN_VALUE;
		String temp = br.readLine();
		char[] arr = temp.toCharArray();
		for(int i = 0; i < N; i++) {
			if(i % 2 == 0)
				operand[i / 2] = Character.getNumericValue(arr[i]);
			else {
				int change = 0;
				switch(arr[i]) {
				case '+':
					change = 0;
					break;
				case '-':
					change = 1;
					break;
				case '*':
					change = 2;
					break;
				}
				operator[i / 2] = change;
			}
		}
		addBracket(0);
		
		System.out.println(result);
	}

}
