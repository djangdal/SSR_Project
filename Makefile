all:
	javac Game.java -cp .:"lib/*"

run:
	java -cp ".:lib/*" Game

clean:
	rm *.class