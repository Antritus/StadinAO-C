// Online C compiler to run C program online
#include <stdio.h>
#include <stdlib.h>
// Boolean
#include <stdbool.h>

// Amount of numbers selected from random
#define SELECT 100
// The highest number selectable
#define SELECTABLE 500
// 0 = IGNORE
// 1 = HIGH_LOW
// 2 = LOW_HIGH
#define SORT 1
// true = Own line for each lottery number
// false = numbers in row
#define DEBUG false
#define DEBUG_NUMBER false
#define DEBUG_ALREADY_FOUND false
#define DEBUG_ZERO false

enum Sort {
    HIGH_LOW,
    LOW_HIGH,
    IGNORE,
};

// HIGH_LOW
int compare_high_low(const void* a, const void* b)
{
    return (*(int*)b - *(int*)a);
}

// LOW_HIGH
int compare_low_high(const void* a, const void* b)
{
    return (*(int*)a - *(int*)b);
}

int main() {
    printf("Rolling Numbers\n");
    int numbers[SELECT];
    int selected = 0;
    int randomInt;

    while (selected < SELECT) {
        randomInt = (rand() % (SELECTABLE - 1)) + 1;  // Generates random numbers between 1 and SELECTABLE-1
        if (DEBUG_NUMBER) {
            printf("%d\n", randomInt);
        }

        if (randomInt == 0 && DEBUG_ZERO) {
            printf("0\n");
            continue;
        }

        bool found = false;
        for (int checkup = 0; checkup < selected; checkup++) {
            if (numbers[checkup] == randomInt) {
                found = true;
                if (DEBUG_ALREADY_FOUND) {
                    printf("ALREADY FOUND %d\n", randomInt);
                }
                break;
            }
        }

        if (!found) {
            numbers[selected] = randomInt;
            selected++;
            if (DEBUG==true){
                printf("New... %d\n", randomInt);
            }
        }
    }

    if (selected == SELECT) {
        printf("Sorting...\n");
        if (SORT == HIGH_LOW) {
            if (DEBUG == true){
                printf("HIGH_LOW\n");
            }
            qsort(numbers, SELECT, sizeof(int), compare_high_low);
        } else if (SORT == LOW_HIGH) {
            if (DEBUG == true){
                printf("LOW_HIGH\n");
            }
            qsort(numbers, SELECT, sizeof(int), compare_low_high);
        }

        for (int i = 0; i < SELECT; i++) {
            printf("%d ", numbers[i]);
        }
        if (DEBUG == false){
            printf("\n");
        }
    }
    return 0;
}