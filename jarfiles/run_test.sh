#!/bin/bash

PATHSEP=":"
if [[ $OS == "Windows_NT" ]] || [[ $OSTYPE == "cygwin" ]]
then
    PATHSEP=";"
fi

if [[ ! -f "students.jar" ]]
then
   echo "You need the students.jar JAR file"
   exit 1
fi
java -cp .${PATHSEP}students.jar test.TestStudents
