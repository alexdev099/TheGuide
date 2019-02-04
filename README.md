This project was made in accordance with the requirements in the task folder. 
Dagger 2 in this project is used more for training purposes, but not in terms of necessity.


                                                            TASK.
                                                            
                                                            
Create a component that displays the attached file "image1_android.png"

The data is an array of FileModel which has the following properties:

- String filename

- String isFolder

- Date modDate

- FileType fileType ---> FileType is an enum. you can define what you think is best: image, pdf, movie, music, etc.

- boolean isOrange ---> Is just an indicator for showing orange bar on the left

- boolean isBlue ---> Is just an indicator for showing blue bar on the left

- When user long presses on a row, it will show a dialog with 3 options as shown in the attached file "image2_android.jpg") When one of these options is tapped, just log it.

When one of the row is (short) tapped, you need to check if it's a file or a folder (isFolder property). 

If it's a file, just log. 

If it's a folder, push start a new instance of same screen with content of this folder.There can be many subfolder levels.

Create an SQLite database that contains your sample data.

You can use dummy icons you pick, or you can quickly cut the icons from the given screenshots.

Code quality will be judged based on: 

How close the components look from the design provided (not the icons because they can be replaced easily)

How well you structured the model, view and controller, and coding conventions (Note: You can pick any convention but be consistent).

Feel free to use any libraries, either 3rd party or your own.

Extra credit: Implement a swipe to left on the rows to reveal action icons instead of a long press and dialog.
                                                            
