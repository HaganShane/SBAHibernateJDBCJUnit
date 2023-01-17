# SBAHibernateJDBCJUnit
SMS Application
-----------------------
1. Download the "HaganSMSZip" zip file, extract it to a location.

2. Open Spring Tool Suite 4, use the "File" in the top left corner, select "Open Projects from File System", use the "Directory" button to navigate to the unzipped folder, which will now be labeled "HaganSMS". This will add it to your workspace to be ran.

3. Database information is in the hibernate.cfg.xml, please change as needed:
    	<property name="connection.url">jdbc:mysql://localhost:3306/smsdb?createDatabaseIfNotExist=true</property>
    	<property name="connection.username">root</property>
    	<property name="connection.password">password</property>

4. Run the program, chose option 9 to Initialize DB (will create smsdb in MySQL Workbench, with the tables course, student, student_course)

5. Use the following SQL Scripts to add data to the student table and course table (please use MySQL workbench to open the scripts, then run the scripts). This should add the lab data to the tables to enable login functionality:
./src/main/resources/Student.sql
./src/main/resources/Course.sql

6. Run the application via SMSRunner again to perform various operations, ex:

Welcome to the SMS Program!
Please enter your status below: 
1. Student
2. Instructor
3. Administrator
4. Exit
1
Please enter your email: 
aiannitti7@is.gd
Please enter your password: 
TWP4hf5j
Welcome to the SMS Application, Alexandra Iannitti
Displaying information for student: Alexandra Iannitti
--- Course ID ---              --- Course Name ---            --- Instructor ---            
1                              English                        Anderea Scamaden              
2                              Mathematics                    Eustace Niemetz               
6                              Digital Logic                  Glenden Reilingen             
10                             Art                            Kingsly Doxsey                
Please enter your selection below: 
1. Register to Class
2. View All Courses
3. View your Courses
4. Logout
1
Displaying information for student: Alexandra Iannitti
--- Course ID ---              --- Course Name ---            --- Instructor ---            
1                              English                        Anderea Scamaden              
2                              Mathematics                    Eustace Niemetz               
6                              Digital Logic                  Glenden Reilingen             
10                             Art                            Kingsly Doxsey                
Use the below course list to pick a course to register for:
--- Course ID ---              --- Course Name ---            --- Instructor ---            
1                              English                        Anderea Scamaden              
2                              Mathematics                    Eustace Niemetz               
3                              Anatomy                        Reynolds Pastor               
4                              Organic Chemistry              Odessa Belcher                
5                              Physics                        Dani Swallow                  
6                              Digital Logic                  Glenden Reilingen             
7                              Object Oriented Programming    Giselle Ardy                  
8                              Data Structures                Carolan Stoller               
9                              Politics                       Carmita De Maine              
10                             Art                            Kingsly Doxsey                
Enter the Course ID you wish to register for:
8
Successfully added course!
Updated course list for Alexandra Iannitti
Displaying information for student: Alexandra Iannitti
--- Course ID ---              --- Course Name ---            --- Instructor ---            
1                              English                        Anderea Scamaden              
2                              Mathematics                    Eustace Niemetz               
6                              Digital Logic                  Glenden Reilingen             
10                             Art                            Kingsly Doxsey                
8                              Data Structures                Carolan Stoller                             
Please enter your selection below: 
1. Register to Class
2. View All Courses
3. View your Courses
4. Logout
4
Thank you for using the SMS application!

7. You will be able to rerun the program as much as you want, use the logins from the SQL script, add various courses. Note: There is no clean database function, only initialize and the scripts in the zip.
