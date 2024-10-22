#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define DEBUG false

int isPalindrome(char input[]){
    int size = strlen(input);
    char reversed[size + 1];

    for (int i = 0; i < size; i++){
        reversed[size - 1 - i] = input[i];
    }
    reversed[size] = '\0';

    bool same = true;
    int current = 0;
    for (int i = 0; i < size; i++) {
        if (reversed[i] != input[i]){
            same = false;
            break;
        }
    }

    if (DEBUG) {
        printf("Reversing: %s\n", input);
        printf("Reversed: %s\n", reversed);
    }
    return same;
}

bool main() {
    while (true){
        char scanned[100];
        printf("Write next palindrome\n");
        scanf("%s", scanned);
        printf("Checking...\n");

        bool palindrome;
        palindrome = isPalindrome(scanned);

        if (palindrome){
            printf("True, a palindrome\n\n");
        } else {
            printf("False, not a palindrome\n\n");
        }
    }
}