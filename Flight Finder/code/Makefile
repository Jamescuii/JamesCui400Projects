runTests: runDataWranglerTests runAlgorithmEngineerTests runBackendDeveloperTests runFrontendDeveloperTests

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java --module-path ./lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FrontendDeveloper.class BackendDeveloper.class
	javac --module-path ./lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar FrontendDeveloperTests.java

run: FrontendDeveloper.class BackendDeveloper.class
	java --module-path ./lib/ --add-modules javafx.controls FrontendDeveloper FrontendInterface

FrontendDeveloper.class: FrontendDeveloper.java FrontendInterface.java
	javac --module-path ./lib/ --add-modules javafx.controls FrontendDeveloper.java FrontendInterface.java

BackendDeveloper.class:
	javac BackendDeveloper.java

runDataWranglerTests: DataWranglerTests.class
	java --module-path ./lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java
	javac --module-path ./lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar DataWranglerTests.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java --module-path ./lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c AlgorithmEngineerTests	

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java
	javac --module-path ./lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar AlgorithmEngineerTests.java
	
runBackendDeveloperTests: BackendDeveloperTests.class
	java --module-path ./lib/ --add-modules javafx.controls --add-exports javafx.graphics/com.sun.javafx.application=ALL-UNNAMED -jar junit5.jar -cp .:JavaFXTester.jar -c BackendDeveloperTests
	
BackendDeveloperTests.class: BackendDeveloperTests.java
	javac --module-path ./lib/ --add-modules javafx.controls -cp .:junit5.jar:JavaFXTester.jar BackendDeveloperTests.java

clean:
	rm *.class
