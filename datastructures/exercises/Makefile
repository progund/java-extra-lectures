JAVA_SRC= \
  se/juneday/util/Kreator.java \
  se/juneday/util/test/KreatorTest.java \
  se/juneday/trials/TryMap.java \
  se/juneday/trials/TryList.java \
  se/juneday/trials/UseMap.java \
  se/juneday/domain/Contact.java

JAVA_CLASSES=$(JAVA_SRC:%.java=%.class)
CLASSPATH=.
NR_CONTACTS=10000000

%.class:%.java
	javac -cp $(CLASSPATH) $<

all: sort remove add find naive map

sort: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryList --array-list --contacts $(NR_CONTACTS) sort
	@java se.juneday.trials.TryList --linked-list --contacts $(NR_CONTACTS) sort

remove: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryList --array-list --contacts $(NR_CONTACTS) remove
	@java se.juneday.trials.TryList --linked-list --contacts $(NR_CONTACTS) remove

add: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryList --array-list --contacts $(NR_CONTACTS) add
	@java se.juneday.trials.TryList --linked-list --contacts $(NR_CONTACTS) add

find: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryList --array-list --contacts $(NR_CONTACTS) find
	@java se.juneday.trials.TryList --linked-list --contacts $(NR_CONTACTS) find

naive: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryList --array-list --contacts $(NR_CONTACTS) naive
	@java se.juneday.trials.TryList --linked-list --contacts $(NR_CONTACTS) naive

map: $(JAVA_CLASSES) 
	@java se.juneday.trials.TryMap

usemap: $(JAVA_CLASSES) 
	@java se.juneday.trials.UseMap

compile: $(JAVA_CLASSES)




clean:
	-rm -f $(JAVA_CLASSES)
	-find . -name "*~" | xargs rm
	-find . -name "*.class" | xargs rm

