#!/usr/bin/env bash
f=file.txt
#sed "10q;d" "$f"
sed -n '10 p' "$f"
