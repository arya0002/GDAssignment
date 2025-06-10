# GDAssignment
1.Create the framework from scracth with driver manager, singleton pattern,Page factory,pojo, yaml configurator,flexibility for running test on local or cloud provider,enums, config file etc
Lot of scope to extend the framework
2.Created 2 E2E test, one for account creation and another for personal data updation
3.Beofre running check the apk file under /src/test/resources/apkFiles/leaforg.apk or change the path of apk FrameworkConstants class
4.Appium server is running on http://0.0.0.0:4723 on my pc, if url needs a change it can be changed under LocalDriverImpl class

How to Run
1.use src/testng.xml to execute the test cases
2.Check report.html, its a extent report and all the logging are getting done there

Although i have automated just 2 positive test cases due to time constraint. Automation can be extend for scenarios like 1. Login Scenarios
Valid login with correct credentials
Invalid login with wrong username/password
Forgot Password
Lockout after N failed attempts(if applicable)
Upload/update profile picture
Edit personal information (name, DOB, licence detail, etc)
View driver license details
Verify mandatory fields validation when updating details
and many more...






 
