#!/usr/bin/env bash

#Search for new results files and store them as var: Files
Files=$(find . -name 'testng-results.xml')


#Initiate the array of Results
declare -a arrResults

#find each file in the $Files variable and write to the array: arrResults
for file in $Files
do
    arrResults=("${arrResults[@]}" "$file")
done



#print out contents of $arrResults
echo $arrResults

#assign length of arrResults as value to var: countArray
countArray=${#arrResults[@]}
echo $countArray
#if the length of arrResults is equal to 0 then print out "there are no Results Files"
if [ $countArray -eq 0 ]; then
echo "there are no Results Files"

#if the length of arrResults is equal to 0 then print out "there is 1 Results Files"
else if [ $countArray -eq 1 ]; then
echo "there is 1 Results File"
#copy the file with paths stored as $arrResults to TestNGResults
mv $arrResults TestNGResults

#if no files exist print out "could not copy file because there are multiple files"
else if [ $countArray -gt 1 ]; then
echo "could not copy file because there are multiple files"

fi

fi

fi
