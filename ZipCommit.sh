#!/bin/bash

# Define the paths to the files and the destination zip file
file1_path="src/test/resources/edu/ncsu/csc326/coffeemaker/CoffeeMaker.feature"
file2_path="src/test/java/edu/ncsu/csc326/coffeemaker/TestSteps.java"
desktop_path="combined_files.zip"

# Create a zip file containing the selected files
zip -j "$desktop_path" "$file1_path" "$file2_path"

echo "Files have been zipped to $desktop_path"
