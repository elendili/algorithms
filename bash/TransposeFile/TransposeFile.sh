#!/usr/bin/env bash
# rs command exists on Ubunta and mac os
# (
#  cat file.txt
#  echo -e "\n"
# ) | rs -S' ' -S' ' -T
f=file.txt

# NF - number of fields/columns
# NR - number of current record
awk '
{
    for (i = 1; i <= NF; i++) {
        if(NR == 1) {
            s[i] = $i;
        } else {
            s[i] = s[i] " " $i; # concat the line
        }
    }
}
END {
    for (i = 1; s[i] != ""; i++) {
        print s[i];
    }
}' $f
