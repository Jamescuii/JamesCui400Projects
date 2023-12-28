runTests: runDataWranglerTests runAlgorithmEngineerTests runBackendDeveloperTests runFrontendDeveloperTests
	
runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . -c DataWranglerTests
runBackendDeveloperTests:
	javac SongBD.java
	javac SongReaderBD.java
	javac SearchableSortedCollectionBD.java
	javac SearchableSortedCollectionInterface.java
	javac -cp .:junit5.jar BackendDeveloperTests.java -Xlint:unchecked
	java -jar junit5.jar -cp . --select-class=BackendDeveloperTests

run:
	javac RunMusicLibrary.java
	java RunMusicLibrary

SongDW.class: SongDW.java
	javac SongDW.java

SongReaderDW.class: SongReaderDW.java
	javac SongReaderDW.java

DataWranglerTests.class: SongDW.class SongReaderDW.class DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java 

runAlgorithmEngineerTests: 
	javac SortedCollectionInterfaceAE.java
	javac SearchableSortedCollectionInterface.java
	javac RedBlackTree.java
	javac SearchableSortedCollectionAE.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java
	java -jar junit5.jar -cp . --select-class=AlgorithmEngineerTests

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . -c FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java
clean:
	rm *.class
