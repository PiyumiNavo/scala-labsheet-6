
@main def main():Unit = {
    getStudentInfoWithRetry()

}

def getStudentInfo():(String,Int,Int,Double,Char) = {
    print("--------------Enter the number of student details --------------\nName: ")
    val name = scala.io.StdIn.readLine()
    print("Marks: ")
    val marks = scala.io.StdIn.readInt()
    print("Total Possible Marks: ")
    val totalPossibleMarks = scala.io.StdIn.readInt()

    val percentage = (marks.toDouble/totalPossibleMarks.toDouble)*100
    val grade = marks match {
        case x if x >= 90 => 'A'
        case x if x >= 75 => 'B'
        case x if x >= 50 => 'C'
        case _ => 'D'
    }
    (name,marks,totalPossibleMarks,percentage,grade)
}


def printStudentRecord(studentInfo: (String,Int,Int,Double,Char)):Unit = {
    println("Name: "+studentInfo(0))
    println("Marks: "+studentInfo(1))
    println("Total Possible Marks: "+studentInfo(2))
    println("Percentage: "+studentInfo(3))
    println("Grade: "+studentInfo(4))
}


def validateInput(studentInfo: (String, Int, Int, Double, Char)):(Boolean, Option[String]) = {
    val (name, marks, totalPossibleMarks, percentage, grade) = studentInfo

    val nameValidation = if (name.nonEmpty) true else false
    val marksValidation = if (marks > 0 && marks <= 100) true else false
    val totalPossibleMarksValidation = if (totalPossibleMarks > 0 && totalPossibleMarks >= marks) true else false

    if (!nameValidation) {
        (false, Some("Name cannot be empty"))
    } else if (!marksValidation) {
        (false, Some("Marks should be between 0 and 100"))
    } else if (!totalPossibleMarksValidation) {
        (false, Some("Total possible marks should be greater than 0 and greater than or equal to marks"))
    } else {
        (true, None)
    }
}

def getStudentInfoWithRetry():(String,Int,Int,Double,Char) = {
    var studentInfo = getStudentInfo()
    var (isValid, errorMessage) = validateInput(studentInfo)
    while(!isValid){
        println(errorMessage)
        studentInfo = getStudentInfo()
        isValid = validateInput(studentInfo)(0)
    }
    studentInfo
}