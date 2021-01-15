#!/bin/bash
echo "Task2 start";

filesCount=365;
avaibleFilesCount=$(($(ls -f files_task1/ | wc -l)-2));

if [[ $filesCount != $avaibleFilesCount ]]
then
    echo "The count of files is not correct";
else
    echo "The count of files is OK";
fi

for file in files_task1/*
do
  echo "date;cite;country;views; clicks" > temp
  sed '1d' $file | sed 's/\([^;]*\);\([^;]*\);\([^.]*\).\([^.]*\).\([^;]*\);\([^;]*\)/\5\/\4\/\3;\1;\2;\6/' >> temp
  mv temp $file
done

echo "Task2 complete";

