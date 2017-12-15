#!/bin/bash

JFLAGS=""

if [ "$1" = "--clean" ]
then
    rm *.class *~ *.data
    exit 0
fi

rm *.data

test_class()
{
    TEST_CLASS=$1
    java $TEST_CLASS && echo && java $TEST_CLASS --store && echo && java $TEST_CLASS echo 
    return $?
}

naive()
{
    FILES="User.java UserCacheTest.java UserCache.java"
    echo
    echo "Testing with naive solution"
    echo "====================================="
    echo "Compiling files: $FILES"
    javac $JFLAGS $FILES && \
        test_class UserCacheTest && \
        echo && echo "Testing with naive solution: passed"
}

generic()
{
    echo
    echo "Testing with generic solution"
    echo "====================================="
    javac $JFLAGS User.java ObjectCacheTest.java se/juneday/ObjectCache.java && \
        test_class ObjectCacheTest && \
        echo && echo "Testing with generic solution: passed"
}

doc()
{
    javadoc -d doc -link  https://docs.oracle.com/javase/8/docs/api/ se.juneday
}

naive

generic

doc

