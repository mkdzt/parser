import model.URL.Companion.url
import page.property.ApartmentPages
import page.property.PropertyPages

suspend fun main(args: Array<String>) {
    val apartmentPages = ApartmentPages()
    apartmentPages.getAllApartmentInfo()
    /*val url = url{
        region = Constants.minskRegion
        sale = Constants.sale
        flats = Constants.flats
        property = Constants.property
        idObject = "2201328"
    }
    val propertyPages = PropertyPages()
    val property = propertyPages.getPropertyInfo(url)
    println(property)*/
    //println("Купалы просп., 71".toAddress())
}