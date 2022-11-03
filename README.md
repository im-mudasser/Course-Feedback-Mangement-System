# Course-Feedback-Mangement-System #
This project was related to course feedback management system . The aim of this project was to get the reviews of students about courses and the teachers of that courses.
In this project there is a admin panel and student panel 
## Admin panel ##
* Login: Admin has a username and password.After entering the correct username and password admin was successfully login otherwise if username or password was incorrect it throw an exception.  
The admin panel have following access.
* Add Students: admin should be able to set the username and password of the students. Username and password was separated by comma. Student records was stored in a file name StudentsID.txt
* Delete Students: Admin can also remove students.for example those students that are now graduated from a university are not a part of this university so those students are not able to give the feedback.Only those students are allowed which are the part of university.
* Edit Students Record: Admin was able to edit the students record like username and password
* Feedback Panel: This feedback panel is future divide into the following.                      
    * Add Questions: Admin should able to add the question to the feedback.
    * Create FeedBack: Admin can create feedback files and every course has it separated file  Feedback files were stored in a folder “admin-feedback-files”.
    * Edit Feedback: Admin was able to edit a question or the feedback statements as well
    * Delete Feedback questions: Admin was able to delete  a specific feedback questions. 
    * Entire Feedback: Admin should be able to delete the specific feedback file or multiples files. 
    * View Students: Admin should be able to view how many students had given the feedback.
    * Calculate Percentage: Admin was able to calculate the percentage corresponding to each feedback.
    * Search: Admin should be able to search a keyword or a sentence from a student feedback.
* Logout: The admin has an option to logout from the system.
## Student panel ##
* Login: Student has a username and password.After entering the correct username and password student was successfully login otherwise if username or password was incorrect it throw an exception.Every student has it username and password set by admin in a file.
* Studnet Menu: After successfully login student would have course feedback files.Each course has its own sperate feedback file.Students can enter the feedback by selecting the following type of question:
    * The course as a whole was: 1.Excellent 2.Good 3.VeryGood 4.Poor 5.VeryPoor
    * The course content was: 1.Excellent 2.Good 3.VeryGood 4.Poor 5.VeryPoor
    * Relavence of course content was: 1.Excellent 2.Good 3.VeryGood 4.Poor 5.VeryPoor
    * Grading techniques were: 1.Excellent 2.Good 3.VeryGood 4.Poor 5.VeryPoor
    * Quality of questions or problems raised by the instructor was: 1.Excellent 2.Good 3.VeryGood                                                                  4.Poor 5.VeryPoor
    * After entering the questions student should be able to add the comment.The file of each feedback will be stored with the student username and the course file name.
* One Feedback For One Course: Student was enforce to enter one feedback for each course.if a student already submiited the feedback for a course it throw an exception "already submitted the feeback".
* Logout: The student has an option to logout from the system.
