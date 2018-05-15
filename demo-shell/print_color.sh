#!/bin/bash

for myseq in $(seq -w 0 255); do

    myidx=$((10#$myseq))
    printf "\e[48;5;%dm  $myseq  \e[0m\n" $myidx
done;
