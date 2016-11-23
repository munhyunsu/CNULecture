#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>


void sig_int();

int main()
{
    signal(SIGINT, sig_int);
    while(1) {
        printf("Heart bit\n");
        sleep(1);
    }
}

void sig_int()
{
    printf("Bye!\n");
    exit(0);
}
