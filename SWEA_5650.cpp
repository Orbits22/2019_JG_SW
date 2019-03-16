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

			if (6 <= map[i][j] && map[i][j] <= 10) // ��Ȧ
				hole[map[i][j]].push_back({ i,j });
		}
	}
}

void go(int startR, int startC, int r, int c, int side, int score) {

	while (true)
	{
		if (r == startR &&
			c == startC) {// ù ������
			if (answer < score)
				answer = score;
			break;
		}

		if (map[r][c] == -1) {// ��Ȧ
			if (answer < score)
				answer = score;
			break;
		}

		if (map[r][c] == 0) { // �ƹ��͵� X
			r = r + dr[side];
			c = c + dc[side];
			continue;
		}

		if (6 <= map[r][c] && map[r][c] <= 10) { // ��Ȧ
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

		switch (side) { // ��
		case 1: // ��
		{
			if (map[r][c] == -2 ||
				map[r][c] == 1 ||
				map[r][c] == 4 ||
				map[r][c] == 5) // �ǵ��ƿ�
				side = 3;
			else if (map[r][c] == 2) // ������
				side = 2;
			else if (map[r][c] == 3) // ����
				side = 4;
			break;
		}
		case 2: // ��
		{
			if (map[r][c] == -2 ||
				map[r][c] == 1 ||
				map[r][c] == 2 ||
				map[r][c] == 5) // �ǵ��ƿ�
				side = 4;
			else if (map[r][c] == 3) // �Ʒ�
				side = 3;
			else if (map[r][c] == 4) // ��
				side = 1;
			break;
		}

		case 3: // ��
		{
			if (map[r][c] == -2 ||
				map[r][c] == 2 ||
				map[r][c] == 3 ||
				map[r][c] == 5) // �ǵ��ƿ�
				side = 1;
			else if (map[r][c] == 1) // ������
				side = 2;
			else if (map[r][c] == 4) // ����
				side = 4;
			break;
		}
		case 4: // ��
		{
			if (map[r][c] == -2 ||
				map[r][c] == 3 ||
				map[r][c] == 4 ||
				map[r][c] == 5) // �ǵ��ƿ�
				side = 2;
			else if (map[r][c] == 2) // �Ʒ�
				side = 3;
			else if (map[r][c] == 1) // ��
				side = 1;
			break;
		}
		}

		score++;
		r = r + dr[side];
		c = c + dc[side];
	}
}

// 1: ��, 2: ��, 3: ��, 4: ��
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