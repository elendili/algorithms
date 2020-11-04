#!/usr/bin/env bash
(tr '[:blank:]' '\n' | tr '[:space:]' '\n' | grep -v -e '^[[:space:]]*$' | sort | uniq -c | sort -r | awk '{ print $2" "$1 }') <words.txt
