#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;
char map[5][5];
int group_map[5][5], answer;
bool visited[5][5];
int dy[4] = { -1,1,0,0 };
int dx[4] = { 0,0,-1,1 };

int dfs(int r, int c) {
	int ret = 1;
	visited[r][c] = true;
	for (int i = 0; i < 4; i++) {
		int rr = r + dy[i];
		int cc = c + dx[i];
		if (0 <= rr && rr < 5 &&
			0 <= cc && cc < 5) {
			if (visited[rr][cc] == false &&
				group_map[rr][cc] == 1) {
				
				ret += dfs(rr, cc);
			}
		}
	}
	return ret;
}

int main() {
	for (int i = 0; i < 5; i++)
		scanf("%s", &map[i]);
	
	vector<int> state(25, 0);
	for (int i = 0; i < 7; i++)
		state[24 - i] = 1;

	do {
		int cntY = 0;
		int sr, sc;
		memset(group_map, 0, sizeof(group_map));
		for (int i = 0; i < 25; i++) {
			int r = i / 5;
			int c = i % 5;
			
			group_map[r][c] = state[i];
			if (state[i] == 1) {
				if (map[r][c] == 'Y')
					cntY++;
				sr = r;
				sc = c;
			}
		}

		if (cntY >= 4)
			continue;

		memset(visited, 0, sizeof(visited));
		if (dfs(sr, sc) == 7)
			answer++;
		
	} while (next_permutation(state.begin(), state.end()));

	printf("%d\n", answer);

	return 0;
}