#!/bin/bash
echo "Task5 start";

ls -d /etc/*
ls -d /etc/* | sed 's#/etc#C:/etc#g'|sed 's#/#\\#g'

echo "Task5 complete";
