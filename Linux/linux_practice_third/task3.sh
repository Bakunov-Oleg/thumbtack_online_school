#!/bin/bash
echo "Task3 start";

mkdir "files_task3" -p

for dDay in {0..6};
do
  week=$(LANG=en_US date -d "2020-01-01 + $dDay days" +%A);
  echo "date;cite;country;views;clicks" > "files_task3/${week}.csv";
done

for file in files_task1/*
do
  currentDate=$(basename $file .csv)
  week=$(LANG=en_US date -d $(date -d $(sed "s/\./\//g" <<< $currentDate) +%Y-%m-%d) +%A);
  sed -e '1d' $file >> "files_task3/$week.csv" && rm $file
done

echo "Task3 comlete"; 
