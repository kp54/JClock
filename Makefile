com/kp/clock/Main.class: com/kp/clock/Main.java com/kp/clock/ClockPanel.java
	javac -encoding UTF-8 com/kp/clock/Main.java

.PHONY:run
run:
	java com.kp.clock.Main

.PHONY:clean
clean:
	rm -rf com/kp/clock/*.class

.PHONY:jar
jar:
	jar cvfm clock.jar manifest.mf com/kp/clock/*.class
