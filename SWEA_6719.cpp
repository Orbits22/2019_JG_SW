#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
using namespace std;

int N, K;
float numbers[201];
float answer;

void init() {
	scanf("%d %d", &N, &K);
	for (int i = 0; i < N; i++) {
		scanf("%f", &numbers[i]);
	}
	answer = 0;
}

void solve() {
	sort(numbers, numbers + N);
	for (int i = N - K; i < N; i++) {
		answer = (answer + numbers[i]) / 2;
	}
}

int main() {
	int T;

	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		init();
		solve();

		printf("#%d %f\n", t, answer);
	}
	return 0;
}