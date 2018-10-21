#!/bin/bash

echo "Starting Winstone web server and servlet container..."
echo "====================================================="
echo
echo -e "Access the system as a plain web server, using an HTML form:\nhttp://localhost:8080/search.html"

java -jar winstone.jar --webroot=webroot
