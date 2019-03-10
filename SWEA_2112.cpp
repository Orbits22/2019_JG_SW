#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
using namespace std;

int answer;
int D, W, K;
int films[13][20]; // 0:A 1:B

void init() {
	scanf("%d %d %d", &D, &W, &K);
	for (int i = 0; i < D; i++) 
		for (int j = 0; j < W; j++) 
			scanf("%d",&films[i][j]);
	answer = 987654321;
}

bool test() {
	int cnt, success;

	success = 0;
	for (int c = 0; c < W; c++) {
		if (success != c)
			return false;

		cnt = 0;
		for (int r = 1; r < D; r++) {
			if (films[r][c] == films[r - 1][c])
				cnt++;
			else
				cnt = 0;

			if (cnt == K - 1) {
				success++;
				break;
			}
		}
	}
	if (success == W)
		return true;
	else
		return false;
}

void inject(int d, int color) {
	for (int i = 0; i < W; i++)
		films[d][i] = color;
}

void copyArr(int a[], int b[]) {
	for(int i = 0 ; i<W ; i++)
		a[i] = b[i];
}

void dfs(int d, int cnt) {
	
	if (cnt > answer)
		return;

	if (d >= D) {
		if ( test() )	
			answer = cnt;
		
		return;
	}

	int tmp[20];
	//아무것도X
	dfs(d + 1, cnt);

	//A
	copyArr(tmp, films[d]);
	inject(d, 0);
	dfs(d + 1, cnt + 1);

	//B
	inject(d, 1);
	dfs(d + 1, cnt + 1);
	copyArr(films[d], tmp);
}

int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		init();
		dfs(0,0);
		printf("#%d %d\n", t, answer);
	}
	return 0;
}