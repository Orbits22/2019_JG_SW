#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;

typedef struct tree {
	int x, y, age;
}Tree;

int N, M, K;
int A[11][11]; // S2D2�� �߰��� ���
int ground[11][11]; // ���� ���� �ִ� ���

// �ֺ� 8�� ��ǥ for������ ������ ���� �迭
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };

vector<Tree> trees; // ���� ����ִ� ����
queue<Tree> DeadQ; // ���� ����
queue<Tree> FiveQ; // ���̰� 5�ǹ���� ����
queue<Tree> AliveQ; // ��Ƴ��� ����
queue<Tree> BirthQ; // ���� ���ܳ� ����

//age ������������ ����ü ������ ���� �Լ�
bool comp(const Tree &a, const Tree &b) {
	return a.age < b.age;
}

void InitFunc() {
	// N,M,K �Է�
	scanf("%d %d %d", &N, &M, &K);

	// S2D2�� �߰��� ��� �Է�
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			scanf("%d", &A[r][c]);
		}
	}

	// ������ ��ǥ�� ���� �Է�
	for (int i = 0; i < M; i++)
	{
		Tree tmp;
		scanf("%d %d %d", &tmp.x, &tmp.y, &tmp.age);
		trees.push_back(tmp);
	}

	// ���� ����� 5�� �ʱ�ȭ
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			ground[i][j] = 5;
		}
	}
}

void SpringFunc() {
	//������ �ڽ��� ���̸�ŭ ����� �԰�, ���̰� 1�����Ѵ�.
	for(int i = 0 ; i<trees.size(); i++)
	{
		int x = trees[i].x, y = trees[i].y, age = trees[i].age;

		// ����� ������ ��� �״´�.
		if (ground[x][y] < age) {
			DeadQ.push(trees[i]);
		}
		else { 
			// ����� �԰� ���̸� �Դ´�.
			ground[x][y] -= age;
			trees[i].age++;
			
			// ��Ƴ��� ���� push
			AliveQ.push(trees[i]);

			if (trees[i].age % 5 == 0)
				// ���̰� 5�� ����� ���� push
				FiveQ.push(trees[i]);
		}
	}
}

void SummerFunc() {
	//���� �׾��� �������� ����� �ȴ�.
	while (!DeadQ.empty())	{
		Tree tree = DeadQ.front();
		DeadQ.pop();

		ground[tree.x][tree.y] += tree.age / 2;
	}
}

void AutumnFunc() {
	//���̰� 5�� ����� �������� �����Ѵ�.
	while (!FiveQ.empty()) {
		Tree tmp = FiveQ.front();
		int x = tmp.x, y = tmp.y;
		int a, b;

		FiveQ.pop();

		for (int i = 0; i < 8; i++) {
			// ��ǥ �ֺ� 8ĭ�� ���� 1�� ������ �߰��ȴ�.
			a = x + dx[i];		b = y + dy[i];
			if (1 <= a && a <= N && 1 <= b && b <= N) {
				BirthQ.push({a,b,1});
			}
		}
	}
}

void WinterFunc() {
	// S2D2�� ����� �߰��Ѵ�.
	for (int r = 1; r <= N; r++) {
		for (int c = 1; c <= N; c++) {
			ground[r][c] += A[r][c];
		}
	}
}

int main() {
	// �Է� & �ʱ�ȭ
	InitFunc();

	// ���̰� ���� ������ ����
	sort(trees.begin(), trees.end(), comp);

	while (K--)
	{
		// ��
		SpringFunc();
		// ����
		SummerFunc();
		// ����
		AutumnFunc();
		// �ܿ�
		WinterFunc();

		// trees���͸� ����.
		trees.clear();

		// ���ܳ� ������ ��Ƴ��� ������ push
		while (!BirthQ.empty()) {
			trees.push_back(BirthQ.front());
			BirthQ.pop();
		}

		while (!AliveQ.empty()) {
			trees.push_back(AliveQ.front());
			AliveQ.pop();
		}
	}

	// ���� ����ִ� ������ ���� ���
	cout << trees.size();

	return 0;
}