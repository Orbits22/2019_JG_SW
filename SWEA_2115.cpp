#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
using namespace std;

int N, M, C, T;
int maxVal1, maxVal2, result;
int arr[11][11];
bool visited[11][11];

void InitFunc() {
	result = 0;
	scanf("%d %d %d", &N, &M, &C);

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			scanf("%d", &arr[i][j]);
}

void getMaxVal(int &maxVal, int r, int c, int target, int sum, int answer)
{
	if (sum > C) {
		return;
	}
	else if (target == M) {
		if (maxVal < answer)
			maxVal = answer;
		return;
	}
	else {
		getMaxVal(maxVal, r, c + 1, target + 1, sum + arr[r][c], answer + arr[r][c] * arr[r][c]);
		getMaxVal(maxVal, r, c + 1, target + 1, sum, answer);
	}
}

bool checkT(int i, int j) { // 겹치지 않는지 확인하는 함수
	for (int k = 0; k < M; k++) {
		if (visited[i][j + k] == true)
			return true;
	}
	return false;
}

int solve(int r, int c) {
	// 초기화
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			visited[i][j] = false;
	maxVal1 = maxVal2 = 0;

	//첫번째 일꾼이 선택
	for (int i = 0; i < M; i++)
		visited[r][c + i] = true;

	// 첫번째 일꾼 최대값 구하기
	getMaxVal(maxVal1, r, c, 0, 0, 0);

	//두번째 일꾼이 겹치지 않게 선택
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N - M + 1; j++) {
			if (checkT(i, j) == false) {
				// 두번째 일꾼 최대값 구하기
				getMaxVal(maxVal2, i, j, 0, 0, 0);
			}
		}
	}
	return maxVal1 + maxVal2;
}

int main() {
	scanf("%d", &T);

	for (int t = 1; t <= T; t++)
	{
		InitFunc();

		for (int r = 0; r < N; r++)
			for (int c = 0; c < N - M + 1; c++)
				result = max(result, solve(r, c));

		printf("#%d %d\n", t, result);
	}

	return 0;
}