import model.Address
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toDate():LocalDate{
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return LocalDate.parse(this, formatter)
}

fun String.findDate():String{
    val regex = Regex("(\\d+)[.](\\d+)[.](\\d+)")
    return regex.find(this, 0)!!.value
}

fun String.findId():String{
    val regex = Regex("(?<=ID\\s).[0-9]+")
    return regex.find(this, 0)!!.value
}

fun String.toAddress(): Address {
    val splitString = this.split(",")
    val subStreet = splitString[0].substringBefore(" ")
    val street = splitString[0].substringAfter(" ") + subStreet
    var numberOfHouse:String
    if(splitString.size == 2){
        numberOfHouse = splitString[1].removePrefix(" ")
        if(splitString[1].contains("Информация о доме")) {
            numberOfHouse = numberOfHouse.substringBefore(" ")
        }
    }
    else numberOfHouse = ""
    return Address(street, numberOfHouse)
}

fun String.toCurrency():String{
    return this.filter { it.isLetter() }
}

fun String.toPriceInt():Int{
    return this.filter { it.isDigit() }.toInt()
}

fun String.toDimension():String{
    return this.filter { !it.isDigit() }.filter { !it.isWhitespace() }
}
