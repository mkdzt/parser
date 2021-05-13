package page.property

import Constants
import Database
import model.URL.Companion.url
import page.region.RegionPage

class ApartmentPages: PropertyPages() {

    suspend fun getAllApartmentInfo(){
        val apartmentsIDs = RegionPage.getAllRegionsApartmentsId()
        for (id in apartmentsIDs){
            val url = url{
                region = id.region
                sale = Constants.sale
                flats = Constants.flats
                property = Constants.property
                idObject = id.idObject
            }
                val result = Database.property.insertOne(getPropertyInfo(url))
                println("${result.insertedId}")
        }
    }
}