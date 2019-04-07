#include<iostream>
#include<climits>
#include<algorithm>
using namespace std;

int operands[10];
char operators[10];
int N, operandsCnt, operatorsCnt;
long long answer;

void init() {
	operandsCnt = operatorsCnt = 0;
	scanf("%d\n", &N);
	for (int i = 0; i < N; i++) {
		if (i % 2 == 0) 
			scanf("%1d", &operands[operandsCnt++]);
		else
			scanf("%c", &operators[operatorsCnt++]);
	}
}

long long calc(long long a, long long  b, char op) {
	long long ret = 0;
	switch (op) {
	case '+':
		ret = a + b;
		break;
	case '-':
		ret = a - b;
		break;
	case '*':
		ret = a * b;
		break;
	}
	return ret;
}

void dfs(long long result, int operatorIdx) {
	int nextResult = 0, nowResult = 0;

	if (operatorIdx >= operatorsCnt) {
		if (answer < result)
			answer = result;
		return;
	}

	nowResult = calc(result, operands[operatorIdx + 1], operators[operatorIdx]);
	dfs(nowResult, operatorIdx + 1);

	if (operatorIdx + 2 < operandsCnt) {
		nextResult = calc(operands[operatorIdx + 1], operands[operatorIdx + 2], operators[operatorIdx + 1]);
		nowResult = calc(result, nextResult, operators[operatorIdx]);
		dfs(nowResult, operatorIdx + 2);
	}
}

void solve() {
	answer = -LLONG_MAX;
	dfs(operands[0],0);
}

int main() {
	init();

	solve();
	
	printf("%d\n", answer);
	return 0;
}