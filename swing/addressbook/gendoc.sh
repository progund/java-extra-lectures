#!/bin/bash

PACKAGES="org.contactcompany.api.container org.contactcompany.api.util org.contactcompany.api.entry org.contactcompany.api.ui.textmenu"
#PACKAGES="org.addressbook.ui.cli.menu"
javadoc -sourcepath src -d doc -link  http://docs.oracle.com/javase/7/docs/api/ $PACKAGES
