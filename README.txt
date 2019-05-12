Chunky Leopard's README File

Running our program:
To run our program, you need to only run the MainMenu.java class.

Needed Files:
There are many files in our SokobanMathPuzzle repository. Everything that is not in the docs folder is necessary
to run our program correctly with all of the functionalities working properly. 

Dependencies:
Our program is based on a two repository system meaning our display module is dependent on these two repositories. 
Each repository has its own dependencies that include the interpretation, validation, and creation. The math 
repository is depended on two Latex files that allows for the display to properly work. The system also depends on
JLaTeXMath, a downloadable library that allows displaying LaTeX in java as images.

Downloads:
1) Download the zip file of the repository.

2) Add JLaTeXMath to the classpath

   Instructions to get the math working (in eclipse):
   - Go to https://jar-download.com/?search_box=jlatexmath and download the 1.0.7 jar (it's a small button above the first blue box).
     Extract .zip to somewhere safe, probably in it's own separate folder. This should contain 3 .jar files, jlatexmath-1.0.7.jar,
     jlatexmath-font-cyrillic-1.0.7.jar, and jlatexmath-font-greek-1.0.7.jar.
   - Open eclipse and right click the Sokoban project.  
   - Open the Build Path submenu and click Configure Build Path.   
   - Navigate to the Java Build Path on the left side.  
   - Navigate to the Libraries tab. 
   - Click on Classpath and click on Add External JARs.
   - Find the location you extracted the .jar to and select it and click Ok.
   - Select Apply and Close.