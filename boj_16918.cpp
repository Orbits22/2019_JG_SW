#include<iostream>
using namespace std;
int R, C, N, expTime[200][200];
char map[200][201];
int dr[4] = { -1,0,1,0 };
int dc[4] = { 0,1,0,-1 };
void init() {
	scanf("%d %d %d", &R, &C, &N);
	for (int i = 0; i < R; i++) {
		scanf("%s", map[i]);
	}
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (map[i][j] == 'O')
				expTime[i][j] = 3;
		}
	}
}

void explode(int nowTime) {
	int nextR, nextC;
	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			if (expTime[r][c] == nowTime) 
			{
				map[r][c] = '.';
				for (int i = 0; i < 4; i++) {
					nextR = r + dr[i];
					nextC = c + dc[i];
					if (0 <= nextR && nextR < R &&
						0 <= nextC && nextC < C) {
						map[nextR][nextC] = '.';
					}
				}
				
			}
		}
	}
}

void setBomb(int nowTime) {
	for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
			if (map[i][j] == '.') {
				map[i][j] = 'O';
				expTime[i][j] = nowTime + 3;
			}
		}
	}
}

void printMap() {
	for (int r = 0; r < R; r++)
		printf("%s\n", map[r]);
}

int main() {

	init();

	for (int time = 1; time <= N; time++) {
		explode(time);
		if (time % 2 == 0) {
			setBomb(time);
		}
	}

	printMap();

	return 0;
}