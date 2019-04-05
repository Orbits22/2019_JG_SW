#include<iostream>
#include<queue>
using namespace std;

typedef struct obj {
	int r, c, predir, rotationNum;
}Obj;

int W, H, r, c;
char map[100][100];
int minRotationNum[100][100];
int dr[4] = { -1,0,1,0 };
int dc[4] = { 0,1,0,-1 };

queue<Obj> Q;
Obj p[2];

void init() {
	scanf("%d %d", &W, &H);
	for (int i = 0; i < H; i++)
		scanf("%s", map[i]);

	int cnt = 0;
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			minRotationNum[i][j] = 987654321;
			if (map[i][j] == 'C') {
				p[cnt].r = i;
				p[cnt].c = j;
				cnt++;
			}
		}
	}
}

int bfs() {
	minRotationNum[p[0].r][p[0].c] = 0;

	for (int i = 0; i < 4; i++)
		Q.push({ p[0].r,p[0].c,i,0 });

	int nowR, nowC, nowDir, nowRotationNum;

	while (!Q.empty()) {
		nowR = Q.front().r;
		nowC = Q.front().c;
		nowDir = Q.front().predir;
		nowRotationNum = Q.front().rotationNum;

		Q.pop();

		for (int nextDir = 0; nextDir < 4; nextDir++) {
			int nextR = nowR + dr[nextDir];
			int nextC = nowC + dc[nextDir];
			int nextRotationNum = nowRotationNum;

			if (0 <= nextR && nextR < H &&
				0 <= nextC && nextC < W &&
				map[nextR][nextC] != '*') {

				if (nextDir != nowDir) nextRotationNum++;

				if (minRotationNum[nextR][nextC] >= nextRotationNum) {
					minRotationNum[nextR][nextC] = nextRotationNum;
					Q.push({ nextR, nextC, nextDir, nextRotationNum });
				}
			}
		}
	}
	return minRotationNum[p[1].r][p[1].c];
}

int main() {
	int answer;
	init();

	answer = bfs();

	printf("%d\n", answer);
	return 0;
}