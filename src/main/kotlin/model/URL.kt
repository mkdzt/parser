package model

import Constants

class URL(
    val mainUrl:String?,
    val sale:String?,
    val flats:String?,
    val region:String?,
    val page:String?,
    val numberPage:Int?,
    val property:String?,
    val idObject:String?
){
    private constructor(builder: Builder) : this(
        builder.mainUrl,
        builder.sale,
        builder.flats,
        builder.region,
        builder.page,
        builder.numberPage,
        builder.property,
        builder.idObject
    )

    override fun toString(): String {
        if(this.numberPage == 0){
            return this.mainUrl + this.region + this.sale + this.flats
        }
        if(this.idObject != ""){
            return this.mainUrl + this.region + this.sale + this.flats + this.property + this.idObject
        }
        if(this.region == "" && this.idObject == ""){
            this.mainUrl + this.sale + this.flats + this.page + this.numberPage
        }
        return this.mainUrl + this.region + this.sale + this.flats + this.page + this.numberPage
    }

    companion object{
        inline fun url(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder{
        var mainUrl:String? = ""
        var sale:String? = ""
        var flats:String? = ""
        var region:String? = ""
        var page:String? = ""
        var numberPage:Int? = -1
        var idObject:String? = ""
        var property:String? = ""

        fun build() = URL(mainUrl = Constants.main,sale ,flats , region ,page ,numberPage, property = Constants.property, idObject)
    }
}