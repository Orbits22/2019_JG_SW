#define _CRT_SECURE_NO_WARNINGS
#define N_MAX 55

#include<iostream>
#include<queue>
using namespace std;

int N, L, R, cnt;
int sum, num, val;

int A[N_MAX][N_MAX];
bool visited[N_MAX][N_MAX];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

queue<pair<int, int>> q;

void DFS(int x, int y) {
	int r, c, sub;

	if (visited[x][y] == true)
		return;

	// �湮üũ, num ����, �α��� ����, ť�� enqueue
	visited[x][y] = true;
	num++;
	sum += A[x][y];
	q.push(make_pair(x, y));

	//�̵� ������ ��ǥ�� ������ w�� ���� ��, ���� ��ǥ�� ���ÿ� push�Ѵ�.
	for (int i = 0; i < 4; i++)
	{
		r = x + dy[i];
		c = y + dx[i];

		if (!(0 <= r && 0 <= c && r < N && c < N))
			continue;

		// �α��� ���� ���Ѵ�.
		sub = A[x][y] - A[r][c];
		if (sub < 0) sub = -sub;

		if ((L <= sub && sub <= R) && (visited[r][c] == false)) {
			DFS(r, c);
		}
	}
}

int main() {
	scanf("%d %d %d", &N, &L, &R);

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &A[i][j]);
		}
	}

	while (true) {
		int groupNum = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum = num = val = 0;

				DFS(i, j);

				if (num > 1) {
					// ������ �̷�� ���
					val = sum / num;

					while (!q.empty())
					{
						int x, y;
						pair<int, int> p = q.front();
						x = p.first;
						y = p.second;

						A[x][y] = val;
						q.pop();

						//���� ���� ī��Ʈ
						groupNum++;
					}
				}
				else if (num == 1) {
					// �ϳ��� �湮�� ��� �ٽ� �ʱ�ȭ �ؾ� �Ѵ�.
					q.pop();
					visited[i][j] = false;
				}
			}
		}

		if (groupNum == 0)
			break;
		else
			cnt++;
	}

	printf("%d\n", cnt);
	return 0;
}