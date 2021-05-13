package page.property

import Coordinates
import Page
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import model.*
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.litote.kmongo.json
import toAddress
import toCurrency
import toDimension
import toPriceInt

open class PropertyPages {

    val gson = Gson()
    val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = GsonSerializer {
                setPrettyPrinting()
                disableHtmlEscaping()
            }
        }
    }




    suspend fun getPropertyInfo(url: URL): Property {
        val page = Page.getPage(url)
        val location = getLocation(page)
        val price = getPrice(page)
        println("$location ---- $price")
        return Property(url.idObject!!, location, price)
    }

    public suspend fun getLocation(page:Document): Location? {
        val table = page.select("table[class=table-params table-borderless table-striped mb-0]").first()
        val rows = table.select("tr")
        val region = rows[0].select("td").last().text()
        val city = rows[1].select("td").last().text()
        var address: Address? = Address("","")
        for(i in 2 until rows.size){
            val isAddress = rows[i].select("td").first().text() == "Адрес"
            if(isAddress){
                address = rows[i].select("td").last().text().toAddress()
            }
        }
        var coordinates = Coordinates("", "")
        if(address?.numberHouse != ""){
            val url = builGeoUrl(city.substringAfter("."), address!!.street.substringAfter("."), address.numberHouse)
            val json = client.get<String>(url)
            coordinates = getCoordinates(json)
        }
        return Location(region, city, address, coordinates)
    }

    private fun getCoordinates(json:String):Coordinates{
        val type = object : TypeToken<Array<Response>>() {}.type
        val response = gson.fromJson<Array<Response>>(json, type)
        var coordinates = Coordinates("","")
        if (response.isNotEmpty()){
            coordinates = Coordinates(response[0].lat!!, response[0].lon!!)
        }
        return coordinates
    }

    private fun builGeoUrl(city: String, street: String, numberOfHouse: String):String {
        val mainUrl = "https://nominatim.openstreetmap.org/search?q="
        return "$mainUrl$city,$street,$numberOfHouse&format=json&limit=1"
    }

    private fun getPrice(page:Document): Array<Price?> {
        val div = page.select("div.select").first()
        if(div != null) {
            val a = div.select("a")
            val priceBYN = getCurrencyPrice(a, 0)
            val priceUSD = getCurrencyPrice(a, 1)
            return arrayOf(priceBYN, priceUSD)
        }
        return arrayOf(Price("договорная", 0,0, "договорная"))
    }

    private fun getCurrencyPrice(a:Elements, indexCurrency:Int):Price?{
        val currencyWholePrice = a[indexCurrency].attr("data-price")
        val currency = currencyWholePrice.toCurrency()
        val wholePrice = currencyWholePrice.toPriceInt()
        val priceCurrencyM2 = a[indexCurrency].attr("data-price_m2")
        val m2price = priceCurrencyM2.toPriceInt()
        val dimension = priceCurrencyM2.toDimension()
        return Price(currency, wholePrice, m2price, dimension)
    }
}