#!/bin/bash
echo "Task4 start"

grep --include=\*.java -rnwlI 'thumbtack_online_school_2019_2_oleg_bakunov-master/' -e "ArrayList"  | xargs cat  > ArrayList.txt

echo "Task4 complete";
