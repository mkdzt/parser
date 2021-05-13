import model.ID
import model.Property
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

object Database {
    private val client = KMongo.createClient(
        "mongodb+srv://maksim:TteoDTQM@clusterproperty.0uynr.mongodb.net/Property?retryWrites=true&w=majority"
    )

    private val database = client.getDatabase("Realt")
    val property = database.getCollection<Property>("Property")
}