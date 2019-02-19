#define _CRT_SECURE_NO_WARNINGS
#define N_MAX 55

#include<iostream>
#include<cmath>
using namespace std;

int N, L, R, cnt;
int sum, num, val;

int A[N_MAX+N_MAX-1][N_MAX+N_MAX-1];
bool visited[N_MAX + N_MAX - 1][N_MAX + N_MAX - 1];

bool checkT() {
	bool result = false;
	for (int i = 0; i < N + N - 1; i+=2) {
		for (int j = 0; j < N + N - 1; j+=2) {
			if (visited[i][j] == true)
				result = true;
		}
	}
	return result;
}

void DFS(int r, int c) {
	int check = 0;
	if (visited[r][c] == true)
		return;
	
	sum += A[r][c];

	visited[r][c] = true;
	num++;
	
	if (r - 2 >= 0 && A[r - 1][c] == 1) { // ��
		DFS(r-2, c);
		check++;
	}
	if (r + 2 < N+N-1 && A[r + 1][c] == 1) {// ��
		DFS(r + 2, c);
		check++;
	}
	if (c - 2 >= 0 && A[r][c-1] == 1) {// ��
		DFS(r, c-2);
		check++;
	}
	if (c + 2 < N+N-1 && A[r][c+1] == 1) {// ��
		DFS(r, c+2);
		check++;
	}
	if (check == 0) {
		visited[r][c] = false;
		sum = 0;
	}
}

int main() {
	scanf("%d %d %d", &N, &L, &R);
	
	for (int i = 0; i < N+N-1; i++) {
		for (int j = 0; j < N+N-1; j++) {
			if(i%2 == 0 && j%2 == 0) // �� ���� �α���
				scanf("%d", &A[i][j]);
		}
	}

	while (true) 
	{
		sum = num = val = 0;
		for (int i = 0; i < N + N - 1; i++) {
			for (int j = 0; j < N + N - 1; j++) {
				visited[i][j] = false;
			}
		}
	
		// ���漱 ���θ� ǥ���Ѵ�.
		for (int i = 0; i < N + N - 1; i++) 
		{
			for (int j = 0; j < N + N - 1; j++) {
				int sub = 0;
				if (i % 2 == 0) {// ¦�� ���� ���
					if (j % 2 != 0) // Ȧ�� ����
					{
						sub = A[i][j - 1] - A[i][j + 1];
						if (sub < 0) // sub ���� ���밪
							sub = -sub;

						if (L <= sub && sub <= R) // ���漱�� ����
							A[i][j] = 1;
						else // ���漱�� ����
							A[i][j] = 0;
						
					}
				}
				else {// Ȧ�� ���� ���
					if (j % 2 == 0)// ¦�� ����
					{
						sub = A[i-1][j] - A[i+1][j];

						if (sub < 0) // sub ���� ���밪
							sub = -sub;

						if (L <= sub && sub <= R) // ���漱�� ����
							A[i][j] = 1;
						else // ���漱�� ����
							A[i][j] = 0;
					}
				}
			}
		}

		//DFS�� sum�� ���ϸ鼭 �湮üũ���Ѵ�.
		for (int i = 0; i < N + N - 1; i++) 
		{
			for (int j = 0; j < N + N - 1; j++) {
				sum = num = val = 0;
				if(i%2 == 0 && j%2 == 0)
					DFS(i, j);

				if (sum != 0 && num != 0) {
					val = sum / num;

					for (int i = 0; i < N + N - 1; i+=2) {
						for (int j = 0; j < N + N - 1; j+=2) {
							if (visited[i][j] == true) {
								A[i][j] = val;
							}
						}
					}
				}
			}
		}

		if (checkT() == false)
			break;
		cnt++;
		//�ٽ� �ݺ��Ѵ�.
	}
	printf("%d\n", cnt);
	for (;;);
	return 0;
}