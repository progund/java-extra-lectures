#!/bin/bash

echo "Deleting ~ files (emacs backup files)"
find . -name '*~' | xargs rm -f
echo "Deleting class files"
find . -name '*.class' | xargs rm -f
