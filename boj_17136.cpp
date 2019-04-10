#include<iostream>
using namespace std;
int map[10][10], usedPapersNum[6];
int dr[4] = { -1,0,1,0 };
int dc[4] = { 0,1,0,-1 };
bool used[10][10];

void init() {
	for (int i = 0; i < 10; i++)
		for (int j = 0; j < 10; j++) 
			scanf("%d", &map[i][j]);
}

bool setOnePapers(int r, int c, int type) {
	int cnt = 0;
	int sum = 0;
	for (int i = 0; i < type; i++) {
		for (int j = 0; j < type; j++) {
			if (0 <= r + i && r + i < 10 &&
				0 <= c + j && c + j < 10) {
				// 색종이는 겹칠 수 없으므로 놓을 수 없음
				if (used[r + i][c + j] == true) 
					return false;
				
				if(map[r+i][c+j] == 1)
					cnt++;
				else 
					return false;
			}
		}
	}

	if (cnt == type * type) {
		// 색종이 개수는 5개가 최대이므로 더 이상 놓을 수 없음
		if (usedPapersNum[type] == 5) 
			return false;

		for (int i = 0; i < type; i++) {
			for (int j = 0; j < type; j++) {
				if (0 <= r + i && r + i < 10 &&
					0 <= c + j && c + j < 10) {

					used[r + i][c + j] = true;
				}
			}
		}
		return true;
	}
	else
		return false;
}

void setAllPapers(int type) {
	for (int r = 0; r < 10; r++) {
		for (int c = 0; c < 10; c++) {
			if (used[r][c] == true)
				continue;

			if (setOnePapers(r, c, type))
				usedPapersNum[type]++;
		}
	}
}

bool finish() {
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			if (map[i][j] == 1 && 
				used[i][j] == false)
					return false;
		}
	}
	return true;
}

int solve() {
	int ret = 0;

	for (int i = 5; i >= 1; i++)
		setAllPapers(i);

	for (int i = 1; i <= 5; i++)
		ret += usedPapersNum[i];

	if(!finish())
		ret = -1;

	return ret;
}

int main() {
	int answer = 0;
	init();
	answer = solve();
	printf("%d\n", answer);
	return 0;
}