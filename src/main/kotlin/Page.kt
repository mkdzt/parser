import model.URL
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object Page {
    fun getPage(url: URL): Document {
        return Jsoup.connect(url.toString()).timeout(600*1000).get()
    }
}