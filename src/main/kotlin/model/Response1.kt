import com.google.gson.annotations.SerializedName
data class Response1(

	@field:SerializedName("osm_type")
	val osmType: String? = null,

	@field:SerializedName("osm_id")
	val osmId: Int? = null,

	@field:SerializedName("licence")
	val licence: String? = null,

	@field:SerializedName("boundingbox")
	val boundingbox: List<String?>? = null,

	@field:SerializedName("importance")
	val importance: Double? = null,

	@field:SerializedName("lon")
	val lon: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("class")
	val jsonMemberClass: String? = null,

	@field:SerializedName("place_id")
	val placeId: Int? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)

