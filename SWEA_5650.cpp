#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<vector>
#include<cstring>
using namespace std;

typedef struct Point {
	int r, c;
}Point;
int N;
int map[102][102];
int dr[5] = { 0,-1,0,1,0 };
int dc[5] = { 0,0,1,0,-1 };
int answer;

vector<Point> hole[11];

void init() {
	answer = 0;
	for (int i = 0; i < 11; i++)
		hole[i].clear();

	scanf("%d", &N);
	for (int i = 0; i <= N + 1; i++) {
		for (int j = 0; j <= N + 1; j++) {
			if (i == 0 || j == 0 ||
				i == N + 1 || j == N + 1)
				map[i][j] = -2;
			else
				scanf("%d", &map[i][j]);

			if (6 <= map[i][j] && map[i][j] <= 10) // ¿úÈ¦
				hole[map[i][j]].push_back({ i,j });
		}
	}
}

void go(int startR, int startC, int r, int c, int side, int score) {

	while (true)
	{
		if (r == startR &&
			c == startC) {// Ã¹ ½ÃÀÛÁ¡
			if (answer < score)
				answer = score;
			break;
		}

		if (map[r][c] == -1) {// ºí·¢È¦
			if (answer < score)
				answer = score;
			break;
		}

		if (map[r][c] == 0) { // ¾Æ¹«°Íµµ X
			r = r + dr[side];
			c = c + dc[side];
			continue;
		}

		if (6 <= map[r][c] && map[r][c] <= 10) { // ¿úÈ¦
			for (int i = 0; i < hole[map[r][c]].size(); i++) {
				int rr, cc;
				rr = hole[map[r][c]][i].r;
				cc = hole[map[r][c]][i].c;

				if (rr != r || cc != c) {
					r = rr + dr[side];
					c = cc + dc[side];
					break;
				}
			}
			continue;
		}

		switch (side) { // º®
		case 1: // »ó
		{
			if (map[r][c] == -2 ||
				map[r][c] == 1 ||
				map[r][c] == 4 ||
				map[r][c] == 5) // µÇµ¹¾Æ¿È
				side = 3;
			else if (map[r][c] == 2) // ¿À¸¥ÂÊ
				side = 2;
			else if (map[r][c] == 3) // ¿ÞÂÊ
				side = 4;
			break;
		}
		case 2: // ¿ì
		{
			if (map[r][c] == -2 ||
				map[r][c] == 1 ||
				map[r][c] == 2 ||
				map[r][c] == 5) // µÇµ¹¾Æ¿È
				side = 4;
			else if (map[r][c] == 3) // ¾Æ·¡
				side = 3;
			else if (map[r][c] == 4) // À§
				side = 1;
			break;
		}

		case 3: // ÇÏ
		{
			if (map[r][c] == -2 ||
				map[r][c] == 2 ||
				map[r][c] == 3 ||
				map[r][c] == 5) // µÇµ¹¾Æ¿È
				side = 1;
			else if (map[r][c] == 1) // ¿À¸¥ÂÊ
				side = 2;
			else if (map[r][c] == 4) // ¿ÞÂÊ
				side = 4;
			break;
		}
		case 4: // ÁÂ
		{
			if (map[r][c] == -2 ||
				map[r][c] == 3 ||
				map[r][c] == 4 ||
				map[r][c] == 5) // µÇµ¹¾Æ¿È
				side = 2;
			else if (map[r][c] == 2) // ¾Æ·¡
				side = 3;
			else if (map[r][c] == 1) // À§
				side = 1;
			break;
		}
		}

		score++;
		r = r + dr[side];
		c = c + dc[side];
	}
}

// 1: »ó, 2: ¿ì, 3: ÇÏ, 4: ÁÂ
int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		init();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= 4; k++) {
					if (map[i][j] == 0)
						go(i, j, i+dr[k], j+dc[k], k, 0);
					else
						break;
				}
			}
		}
		printf("#%d %d\n", t, answer);
	}
	return 0;
}