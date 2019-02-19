#define _CRT_SECURE_NO_WARNINGS
#define N_MAX 55

#include<iostream>
#include<stack>
#include<queue>
using namespace std;

int N, L, R, cnt;
int sum, num, val;

int A[N_MAX][N_MAX];
bool visited[N_MAX][N_MAX];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

stack<pair<int, int>> s;
queue<pair<int, int>> q;

bool checkT() {
	bool result = false;
	for (int i = 0; i < N ; i++) {
		for (int j = 0; j < N ; j++) {
			if (visited[i][j] == true)
				result = true;
		}
	}
	return result;
}

void DFS(pair<int, int> v) 
{
	int x, y, r, c, sub;
	pair<int, int> w;
	
	x = v.first;	y = v.second;

	if (visited[x][y] == true)
		return;

	// �湮üũ, num ����, �α��� ����, ť�� enqueue
	visited[x][y] = true;
	num++;
	sum += A[x][y];
	q.push(v);

	do {
		// w�� �ʱ�ȭ�Ѵ�. ( w: ���� ��ǥ )
		w = make_pair(-1, -1);

		//�̵������� ������ ������ w�� ���� ��, ���� ��ǥ�� ���ÿ� push�Ѵ�.
		for (int i = 0; i < 4; i++) 
		{
			x = v.first;	y = v.second;
			r = x + dy[i];	c = y + dx[i]; // ��, ��, ��, �� ����
			
			// �迭 A �ε��� ��ȿ���� üũ�Ѵ�.
			if (!(0 <= r && 0 <= c && r < N && c < N))
				continue;

			// �α��� ���� ���Ѵ�.
			sub = A[x][y] - A[r][c];
			if (sub < 0) sub = -sub;

			// ��輱�� ���� �ش� ���� ���� �湮���� ���� ���
			if ( (L <= sub && sub <= R) && (visited[r][c] == false) ) { 
				// w : ���� ��ǥ
				w = make_pair(r, c);
				// ���� ��ǥ�� push�Ѵ�.
				s.push(v);
				break;
			}
		}

		//������ �̷�� ���
		while (!(w.first == -1 && w.second == -1)) 
		{
			// �湮üũ, num ����, �α��� ����, ť�� enqueue
			x = w.first;	y = w.second;
			visited[x][y] = true;
			num++;
			sum += A[x][y];
			q.push(w);

			// v(���� ��ǥ)�� w�� �Ͽ� �ݺ��Ѵ�.
			v = w;
			w = make_pair(-1, -1);

			//�̵������� ������ ������ w�� ���� ��, ���� ��ǥ�� ���ÿ� push�Ѵ�.
			for (int i = 0; i < 4; i++) 
			{
				r = x + dy[i];	c = y + dx[i];

				if (!(0 <= r && 0 <= c && r < N && c < N))
					continue;

				sub = A[x][y] - A[r][c];
				if (sub < 0) sub = -sub;

				if ((L <= sub && sub <= R) && (visited[r][c] == false)) {
					w = make_pair(r, c);
					s.push(v);
					break;
				}
			}
		}

		if (s.empty() == true) {
			v = make_pair(-1, -1);
			continue;
		}

		v = s.top();
		s.pop();

	} while (!(v.first == -1 && v.second == -1));

	if (num == 1) {
		num = sum = 0;
		visited[x][y] = false;
		q.pop();
	}
}

int main() {
	scanf("%d %d %d", &N, &L, &R);

	for (int i = 0; i < N ; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &A[i][j]);
		}
	}

	while (true)
	{
		sum = num = val = 0;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N ; j++) {
				visited[i][j] = false;
			}
		}

		//DFS�� �湮üũ�� �ϸ鼭 sum,num�� ���Ѵ�.
		for (int i = 0; i < N ; i++)
		{
			for (int j = 0; j < N ; j++) {
				sum = num = val = 0;

				DFS(make_pair(i,j));

				if (sum != 0 && num != 0) 
				{
					val = sum / num;

					// ť�� �ִ� ��� ��ǥ�� ������ A�迭 �� ��ǥ�� �ش��ϴ� ���� val�� ����
					while (!q.empty()) 
					{
						int x, y;
						pair<int, int> p = q.front();
						q.pop();
						x = p.first;	y = p.second;

						A[x][y] = val;
					}
					
				}
			}
		}

		// �湮�� ���� �ϳ��� ���� ���(�α� �̵��� �Ͼ�� �ʴ� ���) �����Ѵ�.
		if (checkT() == false)
			break;

		// cnt�� ������Ų �� �ٽ� �ݺ��Ѵ�.
		cnt++; // �α� �̵� Ƚ�� ī��Ʈ
	}

	printf("%d\n", cnt);
	return 0;
}