#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <algorithm>
#include <math.h>
#include <string.h>

int map[11][11] = { 0, };
int map_buck[11][11][9] = { 0, };
int M = 0, A = 0;

int CustomerA_x = 0;
int CustomerA_y = 0;
int CustomerB_x = 0;
int CustomerB_y = 0;
int CustomerA_move[100] = { 0, };
int CustomerB_move[100] = { 0, };

int CustomerA_AP_num = 0;
int CustomerB_AP_num = 0;

int mov_x[4] = { 0,1,0,-1 };
int mov_y[4] = { -1,0,1,0 }; //�� �� �� ��
int C[9] = { 0, };
int P[9] = { 0, };
int AP_x[9] = { 0, };
int AP_y[9] = { 0, };

int sum_A[100] = { 0, };
int sum_B[100] = { 0, };
int result_sum = 0;

int charge(int i) {
	int tmp_AP_1 = 0;
	int tmp_AP_2 = 0;
	if (CustomerA_AP_num > 8 || CustomerB_AP_num > 8) { // ������ ������ ���� ���
		if (CustomerA_AP_num > 8 && CustomerB_AP_num > 8) { // �Ѵ� ������ �������
			if (CustomerA_AP_num == CustomerB_AP_num) { // �Ѵ� ���� ������ ���� ���
				tmp_AP_1 = 0;
				tmp_AP_2 = 0;
				tmp_AP_1 = map[CustomerA_y][CustomerA_x] / 10;
				tmp_AP_2 = map[CustomerA_y][CustomerA_x] % 10;
				sum_A[i] = P[tmp_AP_1];
				sum_B[i] = P[tmp_AP_2];
				result_sum = result_sum + sum_A[i] + sum_B[i];
			}
			else { // �ٸ� ������ ���� ���
				tmp_AP_1 = 0;
				tmp_AP_2 = 0;
				tmp_AP_1 = map[CustomerA_y][CustomerA_x] / 10;
				tmp_AP_2 = map[CustomerA_y][CustomerA_x] % 10 ;
				if (P[tmp_AP_1] > P[tmp_AP_2]) {
					sum_A[i] = P[tmp_AP_1];
					result_sum = result_sum + sum_A[i];
				}
				else {
					sum_A[i] = P[tmp_AP_2];
					result_sum = result_sum + sum_A[i];
				}
				tmp_AP_1 = map[CustomerB_y][CustomerB_x] / 10;
				tmp_AP_2 = map[CustomerB_y][CustomerB_x] % 10;
				if (P[tmp_AP_1] > P[tmp_AP_2]) {
					sum_B[i] = P[tmp_AP_1];
					result_sum = result_sum + sum_B[i];
				}
				else {
					sum_B[i] = P[tmp_AP_2];
					result_sum = result_sum + sum_B[i];
				}
			}
		}
		else { // �� �� �ϳ��� ������ ���� ���
			if (CustomerA_AP_num > 8) { // A�� �������ΰ��
				tmp_AP_1 = 0;
				tmp_AP_2 = 0;
				tmp_AP_1 = map[CustomerA_y][CustomerA_x] / 10;
				tmp_AP_2 = map[CustomerA_y][CustomerA_x] % 10;
				if (CustomerB_AP_num == tmp_AP_1 || CustomerB_AP_num == tmp_AP_2) {		//A������ ��ȣ �� B�� ��ġ�°� �ִ� ���
					if (CustomerB_AP_num == tmp_AP_1) {
						sum_B[i] = P[tmp_AP_1];
						sum_A[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_A[i] + sum_B[i];
					}
					if (CustomerB_AP_num == tmp_AP_2) {
						sum_A[i] = P[tmp_AP_1];
						sum_B[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_A[i] + sum_B[i];
					}
				}
				else {																	//A������ ��ȣ �� B�� ��ġ�°� ���� ���
					if (P[tmp_AP_1] > P[tmp_AP_2]) {
						sum_A[i] = P[tmp_AP_1];
						result_sum = result_sum + sum_A[i];
					}
					else {
						sum_A[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_A[i];
					}

					if (0 < CustomerB_AP_num && CustomerB_AP_num <= 8) {
						sum_B[i] = P[CustomerB_AP_num];
						result_sum = result_sum + sum_B[i];
					}
				}
			}
			else { // B�� �������� ���
				tmp_AP_1 = map[CustomerB_y][CustomerB_x] / 10;
				tmp_AP_2 = map[CustomerB_y][CustomerB_x] % 10;
				if (CustomerA_AP_num == tmp_AP_1 || CustomerA_AP_num == tmp_AP_2) {		//A������ ��ȣ �� B�� ��ġ�°� �ִ� ���
					if (CustomerA_AP_num == tmp_AP_1) {
						sum_A[i] = P[tmp_AP_1];
						sum_B[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_A[i] + sum_B[i];
					}
					if (CustomerA_AP_num == tmp_AP_2) {
						sum_B[i] = P[tmp_AP_1];
						sum_A[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_A[i] + sum_B[i];
					}
				}
				else {
					if (P[tmp_AP_1] > P[tmp_AP_2]) {
						sum_B[i] = P[tmp_AP_1];
						result_sum = result_sum + sum_B[i];
					}
					else {
						sum_B[i] = P[tmp_AP_2];
						result_sum = result_sum + sum_B[i];
					}
					if (0 < CustomerA_AP_num && CustomerA_AP_num <= 8) {
						sum_A[i] = P[CustomerA_AP_num];
						result_sum = result_sum + sum_A[i];
					}
				}
			}
		}
	}
	else {
		if (CustomerA_AP_num != CustomerB_AP_num) { // A,B���� �ٸ� ��
			if (1 <= CustomerA_AP_num && CustomerA_AP_num <= 8) { //A�� AP ���� ���
				sum_A[i] = P[CustomerA_AP_num];
				result_sum = result_sum + sum_A[i];
			}
			else if (CustomerA_AP_num == 0) { //A�� AP �ȹ��� ���
				sum_A[i] = 0;
			}
			if (1 <= CustomerB_AP_num && CustomerB_AP_num <= 8) { //B�� AP ���� ���
				sum_B[i] = P[CustomerB_AP_num];
				result_sum = result_sum + sum_B[i];
			}
			else if (CustomerB_AP_num == 0) { //B�� AP �ȹ��� ���
				sum_B[i] = 0;
			}
		}
		else {  // A,B ���� ���� ��
			if (CustomerA_AP_num != 0) { // �Ѵ� 0 �ƴ� ���
				sum_A[i] = P[CustomerA_AP_num] / 2;
				sum_B[i] = P[CustomerB_AP_num] / 2;
				result_sum = result_sum + sum_A[i] + sum_B[i];
			}
		}
	}
	return 0;
}

int move(int i) {
	switch (CustomerA_move[i]) {
	case 0 :
		break;
	case 1:
		CustomerA_y = CustomerA_y + mov_y[0];
		CustomerA_x = CustomerA_x + mov_x[0];
		break;
	case 2:
		CustomerA_y = CustomerA_y + mov_y[1];
		CustomerA_x = CustomerA_x + mov_x[1];
		break;
	case 3:
		CustomerA_y = CustomerA_y + mov_y[2];
		CustomerA_x = CustomerA_x + mov_x[2];
		break;
	case 4:
		CustomerA_y = CustomerA_y + mov_y[3];
		CustomerA_x = CustomerA_x + mov_x[3];
		break;
	}
	CustomerA_AP_num = map[CustomerA_y][CustomerA_x];
	//map[CustomerA_y][CustomerA_x] = 99;

	switch (CustomerB_move[i]) {
	case 0:
		break;
	case 1:
		CustomerB_y = CustomerB_y + mov_y[0];
		CustomerB_x = CustomerB_x + mov_x[0];
		break;
	case 2:
		CustomerB_y = CustomerB_y + mov_y[1];
		CustomerB_x = CustomerB_x + mov_x[1];
		break;
	case 3:
		CustomerB_y = CustomerB_y + mov_y[2];
		CustomerB_x = CustomerB_x + mov_x[2];
		break;
	case 4:
		CustomerB_y = CustomerB_y + mov_y[3];
		CustomerB_x = CustomerB_x + mov_x[3];
		break;
	}
	CustomerB_AP_num = map[CustomerB_y][CustomerB_x];
	//map[CustomerB_y][CustomerB_x] = 88;
	return 0;
}

int draw(int x,int y, int cap,int p, int label) {
	int cnt = 1;
	for (int i = y - cap ; i <= y - cap + cap * 2; i++) {
		if (1 <= i && i <= 10) {
			for (int j = x - cap ; j <= x - cap + cap * 2; j++) {
				if (1 <= j && j <= 10) {
					if (abs(y - i) + abs(x - j) <= cap) {
						if (map[i][j] == 0) {
							map[i][j] = label;
						}
						else {
							map[i][j] = map[i][j] * 10 + label;
						}
					}
				}
			}
		}
	}
	printf("------------------------------------\n");
	for (int i = 1; i <= 10; i++) {
		for (int j = 1; j <= 10; j++) {
			printf("%d  ", map[i][j]);
		}
		printf("\n");
	}

	return 0;
}

int main() {
	int i = 0;
	int j = 0;
	int T = 0;
	scanf("%d", &T);
	for (int test_case = 1; test_case <= T; test_case++) {
		memset(map, 0, sizeof(map));
		memset(map_buck, 0, sizeof(map_buck));

		memset(CustomerA_move, 0, sizeof(CustomerA_move));
		memset(CustomerB_move, 0, sizeof(CustomerB_move));

		memset(sum_A, 0, sizeof(sum_A));
		memset(sum_B, 0, sizeof(sum_B));

		result_sum = 0;

		CustomerA_y = 1;
		CustomerA_x = 1;
		CustomerB_y = 10;
		CustomerB_x = 10;
		
		printf("------------------------------------\n");
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				printf("%d  ", map[i][j]);
			}
			printf("\n");
		}
		scanf("%d %d", &M, &A);
		
		for (j = 1; j <= M; j++) {
			scanf("%d ", &CustomerA_move[j]);
		}
		for (j = 1; j <= M; j++) {
			scanf("%d ", &CustomerB_move[j]);
		}

		for (i = 1; i <= A; i++) {
			scanf("%d %d %d %d", &AP_x[i], &AP_y[i], &C[i], &P[i]);
			draw(AP_x[i], AP_y[i], C[i], P[i], i);
		}

		for (i = 0; i <= M; i++) { // ó������ ���� �����ؾ��ؼ� 0���� ���� move[0] = 0�̴� �ȿ����̱� ����
			move(i);
			charge(i);
			//printf("------------------------------------\n");
			//for (int i = 1; i <= 10; i++) {
			//	for (int j = 1; j <= 10; j++) {
			//		printf("%d  ", map[i][j]);
			//	}
			//	printf("\n");
			//}
		}
		printf("#%d %d\n", test_case, result_sum);
	}
	return 0;
}