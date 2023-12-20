"""
Instructions
Develop a set of classes for a college to use in various student service and personnel applications. 

Classes you need to design include the following:

Person - A Person contains a first name, last name, street address, zip code, and phone number. The class also includes a method that sets each data field, using a series of dialog boxes and a display method that displays all of a Person’s information on a single line at the command line on the screen.

CollegeEmployee - CollegeEmployee descends from Person. A CollegeEmployee also includes a Social Security number, an annual salary, and a department name, as well as methods that override the Person methods to accept and display all CollegeEmployee data.

Faculty - Faculty descends from CollegeEmployee. This class also includes a Boolean field that indicates whether the Faculty member is tenured, as well as methods that override the CollegeEmployee methods to accept and display this additional piece of information.

Student - Student descends from Person. In addition to the fields available in Person, a Student contains a major field of study and a grade point average as well as methods that override the Person methods to accept and display these additional facts.

Write an application named CollegeList that implements a list of four “regular” CollegeEmployee, three Faculty, and seven Students. 

1. Prompt the user to specify which type of person’s data will be entered (C, F, or S), or allow the user to quit (Q). While the user chooses to continue (that is, does not quit), accept data entry for the appropriate type of Person. 

output

2. If the user attempts to enter data for more than four CollegeEmployees, three Faculty, or seven Students, display an error message. 

3. When the user quits, display a report on the screen listing each group of Persons under the appropriate heading of “College Employees,” “Faculty,” or “Students.” 

4. If the user has not entered data for one or more types of Persons during a session, display an appropriate message under the appropriate heading.
"""


#Person parent class
class Person:
  # use variable = "" down below as default value. So that whatever I'm putting in constructor goes into the object.
  def __init__(self,
               firstName="",
               lastName="",
               streetAd="",
               zipNum="",
               phoneNum=""):
    self.firstName = firstName
    self.lastName = lastName
    self.streetAd = streetAd
    self.zipNum = zipNum
    self.phoneNum = phoneNum

  def set_data(self):
    self.firstName = input("Enter first name: ")
    self.lastName = input("Enter last name: ")
    self.streetAd = input("Enter street address: ")
    self.zipNum = input("Enter zip code: ")
    self.phoneNum = input("Enter phone number: ")

  def display_data(self):
    print(f"Name: {self.firstName} {self.lastName}. ")
    print(f"Address: {self.streetAd}, {self.zipNum}. ")
    print(f"Phone: {self.phoneNum}. ")


#College employee Class
class CollegeEmployee(Person):
  #Defining a function
  def __init__(self,
               firstName="",
               lastName="",
               streetAd="",
               zipNum="",
               phoneNum="",
               socialNum="",
               annualSalary=0,
               deptName=""):
    #Calling a function
    super().__init__(firstName, lastName, streetAd, zipNum,
                     phoneNum)  #Super collects data from Person
    self.socialNum = socialNum
    self.annualSalary = annualSalary
    self.deptName = deptName

  def set_data(self):
    super().set_data()
    self.socialNum = input("Enter Social Security Number: ")
    self.annualSalary = float(input("Enter annual salary: "))
    self.deptName = input("Enter department name: ")

  def display(self):
    #super().display()
    print(f"Name: {self.firstName} {self.lastName}. ")
    print(f"Address: {self.streetAd}, {self.zipNum}. ")
    print(f"Phone: {self.phoneNum}. ")
    print(f"Social Security Number: {self.socialNum}")
    print(f"Annual Salary: ${self.annualSalary:.2f}")
    print(f"Department Name: {self.deptName}")
    print()


#Faculty class
class Faculty(CollegeEmployee):

  def __init__(self,
               firstName="",
               lastName="",
               streetAd="",
               zipNum="",
               phoneNum="",
               socialNum="",
               annualSalary=0,
               deptName="",
               tenured=False):
    super().__init__(firstName, lastName, streetAd, zipNum, phoneNum,
                     socialNum, annualSalary, deptName)
    self.tenured = tenured

  def set_data(self):
    super().set_data()
    self.tenured = input(
      "Is the Faculty member tenured? (y/n): ").lower() == "y"

  def display(self):
    #super().display()
    print(f"Name: {self.firstName} {self.lastName}. ")
    print(f"Address: {self.streetAd}, {self.zipNum}. ")
    print(f"Phone: {self.phoneNum}. ")
    print(f"Social Security Number: {self.socialNum}")
    print(f"Annual Salary: ${self.annualSalary:.2f}")
    print(f"Department Name: {self.deptName}")
    if self.tenured:
      print("Tenured: yes")
    else:
      print("Tenured: no")
    print()


#Student Class
class Student(Person):

  def __init__(self,
               firstName="",
               lastName="",
               streetAd="",
               zipNum="",
               phoneNum="",
               major="",
               GPA=0):
    super().__init__(firstName, lastName, streetAd, zipNum, phoneNum)
    self.major = major
    self.GPA = GPA

  def set_data(self):
    super().set_data()
    self.major = input("Enter major: ")
    self.GPA = float(input("Enter grade point average: "))

  def display(self):
    #super().display()
    print(f"Name: {self.firstName} {self.lastName}. ")
    print(f"Address: {self.streetAd}, {self.zipNum}. ")
    print(f"Phone: {self.phoneNum}. ")
    print(f"Major: {self.major}")
    print(f"Grade Point Average: {self.GPA:.2f}")
    print()


#Start code
choice = ""
employeeList = []
facultyList = []
studentList = []
print("\nChoose the type of person to enter:")

#Try using .append to append to list
while True:
  print()
  choice = input(
    "C for College Employee, F for Faculty, S for Student. Q to quit:  ")

  if not choice in ["C", "F", "S", "Q"]:
    print("Nope! Pick from list above")
    continue

  if choice == "C":
    if len(employeeList) < 4:
      person = CollegeEmployee()
      person.set_data()
      employeeList.append(person)
    else:
      print("List is too Long")
      continue

  elif choice == "F":
    if len(facultyList) < 3:
      person = Faculty()
      person.set_data()
      facultyList.append(person)
    else:
      print("List is too Long")
      continue

  elif choice == "S":
    if len(employeeList) < 7:
      person = Student()
      person.set_data()
      studentList.append(person)
    else:
      print("List is too Long")
      continue  #continue will go to the top\ of the while loop.

  elif choice == "Q":
    break

for person in employeeList:
  person.display()
for person in facultyList:
  person.display()
for person in studentList:
  person.display()
