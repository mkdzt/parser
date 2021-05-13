package page.region

import Constants
import Page
import findDate
import findId
import model.ID
import model.URL
import model.URL.Companion.url
import toDate

object RegionPage {
    private fun getNumberOfLinks(url: URL): Int {
        val page = Page.getPage(url)
        val element = page.select("div.paging-list").first()
        return element.select("a[href]").last().text().toInt() - 1
    }

    fun getIdApartmentsByYear(regionSearch:String, year:Int):List<String>{
        val ids = mutableListOf<String>()
        val url1 = url{
            sale = Constants.sale
            flats = Constants.flats
            region = regionSearch
            page = Constants.page
            numberPage = 1
        }
        val numberPages = getNumberOfLinks(url1)
        println(numberPages)
        for (i in 0..numberPages) {
            val url = url {
                sale = Constants.sale
                flats = Constants.flats
                region = regionSearch
                page = Constants.page
                numberPage = i
            }
            val classElementsMiniInfo = Page.getPage(url).getElementsByClass("info-mini")
            for (element in classElementsMiniInfo){
                val date = element.text().findDate().toDate()
                if (date.year == year) {
                    val id = element.text().findId()
                    ids.add(id)
                    //Database.id.insertOne(ID(id, url.region!!))
                    println(id + " " + url.region)
                }
            }
        }
        return ids
    }

    fun getAllRegionsApartmentsId():List<ID>{
        val allId = mutableListOf<ID>()
        /*val minskRegion = getIdApartmentsByYear(Constants.minskRegion, 2021)
        minskRegion.forEach{
            allId.add(ID(it,Constants.minskRegion))
        }
        val brestRegion = getIdApartmentsByYear(Constants.brestRegion, 2021)
        brestRegion.forEach{
            allId.add(ID(it,Constants.brestRegion))
        }
        val vitebskRegion = getIdApartmentsByYear(Constants.vitebskRegion, 2021)
        vitebskRegion.forEach{
            allId.add(ID(it,Constants.vitebskRegion))
        }
        val gomelRegion = getIdApartmentsByYear(Constants.gomelRegion, 2021)
        gomelRegion.forEach{
            allId.add(ID(it,Constants.gomelRegion))
        }*/
        val grodnoRegion = getIdApartmentsByYear(Constants.grodnoRegion, 2021)
        grodnoRegion.forEach {
            allId.add(ID(it,Constants.grodnoRegion))
        }
        /*val mogilevRegion = getIdApartmentsByYear(Constants.mogilevRegion, 2021)
        mogilevRegion.forEach {
            allId.add(ID(it,Constants.mogilevRegion))
        }*/
        return allId
    }
}