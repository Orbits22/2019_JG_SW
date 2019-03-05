#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<cstring>
using namespace std;

int N, X, answer;
int map[20][20];
bool check[20][20];

void init() {
	scanf("%d %d", &N, &X);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	answer = 0;
}

bool isAbleHD(int r, int c) {
	for (int i = 0; i < X; i++)
	{
		if (c + i == N
			|| map[r][c] != map[r][c + i])
		{
			return false;
		}
	}

	for (int i = 0; i < X; i++)
		check[r][c + i] = true;

	return true;
}

bool isAbleHA(int r, int c) {
	for (int i = 0; i < X; i++)
	{
		if (-1 == c - i
			|| map[r][c] != map[r][c - i]
			|| check[r][c - i] == true)
		{
			return false;
		}
	}

	return true;
}

bool isAbleVD(int r, int c) {
	for (int i = 0; i < X; i++)
	{
		if (r + i == N
			|| map[r][c] != map[r + i][c])
		{
			return false;
		}
	}

	for (int i = 0; i < X; i++)
		check[r + i][c] = true;

	return true;
}

bool isAbleVA(int r, int c) {
	for (int i = 0; i < X; i++)
	{
		if (-1 == r-i
			|| map[r][c] != map[r - i][c]
			|| check[r - i][c] == true)
		{
			return false;
		}
	}

	return true;
}

bool checkLineH(int r, int c) {
	
	if (c == N - 1) return true;

	if (map[r][c] == map[r][c + 1])
	{
		return checkLineH(r, c + 1);
	}
	
	if (map[r][c] == map[r][c + 1] + 1 
		&& isAbleHD(r, c + 1))		   // 내리막길
	{
		return checkLineH(r,c + X);
	}

	if (map[r][c] == map[r][c + 1] - 1
		&& isAbleHA(r, c))              // 오르막길
	{
		return checkLineH(r,c + 1);
	}

	return false;
}

bool checkLineV(int r, int c) {

	if (r == N - 1) return true;

	if (map[r][c] == map[r + 1][c])
	{
		return checkLineV(r + 1, c);
	}

	if (map[r][c] == map[r + 1][c] + 1
		&& isAbleVD(r + 1, c))		   // 내리막길
	{
		return checkLineV(r + X, c);
	}

	if (map[r][c] == map[r + 1][c] - 1
		&& isAbleVA(r, c))              // 오르막길
	{
		return checkLineV(r + 1, c);
	}

	return false;
}

void solve() {
	//가로
	memset(check, 0, sizeof(check));
	for (int r = 0; r < N; r++) {
		if (checkLineH(r,0))
			answer++;
	}
	//세로
	memset(check, 0, sizeof(check));
	for (int c = 0; c < N; c++) {
		if (checkLineV(0, c))
			answer++;
	}
}

int main() {
	int T;
	scanf("%d", &T);
	for(int t = 1 ; t<=T ; t++)
	{
		init();

		solve();

		printf("#%d %d\n", t, answer);
	}
	return 0;
}
