#!/bin/bash

UI=$1
if [[ -z "$UI" ]]
then
    UI=cli
fi
java -cp bin/ -Dui="$UI" org.contactcompany.application.main.AddressBook
