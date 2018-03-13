#!/usr/bin/env bash




#Rename file to include date/timestamp

NOW=$(date +'%d-%m-%Y_%T')

mv TestNGResults/testng-results.xml TestNGResults/testng-results_$NOW.xml

