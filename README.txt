Chunky Leopard's README File

Running our program:
To run our program, you need to only run the MainMenu.java class.

Needed Files:
There are many files in out SokobanMathPuzzle repository. All are necessary to tun our program correctly with 
all of the functionalities working properly. 

Dependencies:
Our program is based on a two repository system meaning our display module is dependent on these two repositories. 
Each repository has it's own dependencies that include the interpretation, validation, and creation. The math 
repository is depended on two Latex files that allows for the display to properly work.

Downloads:
1) Download the zip file of the repository. 

2) Instructions to get the math working (in eclipse):
- Go to https://jar-download.com/?search_box=jlatexmath and download the 1.0.7 jar (it's a small button above the first blue box).
  Extract .zip to somewhere safe, probably in it's own separate folder. This should contain 3 .jar files, jlatexmath-1.0.7.jar,
  jlatexmath-font-cyrillic-1.0.7.jar, and jlatexmath-font-greek-1.0.7.jar.
- In order to make the import simpler, I combined the 2 font .jars into the main .jar. You can do this by extracting 
  the .jars and adding the files into the archive, by using an archive manager and just drag-dropping, or other methods, so 
  that the folders correspond.
- Open eclipse and right clickthe Sokoban project.  
- Open the Build Path submenu and click Configure Build Path.   
- Navigate to the Java Build Path on theleft side.  
- Navigate to the Libraries tab. 
- Click on Classpath and click on Add External JARs.
- Find the location you extracted the .jar to and select it and click Ok.
- Select Apply and Close. (edited) 
