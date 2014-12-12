import play.api.mvc.WithFilters
import com.mohiva.play.htmlcompressor.HTMLCompressorFilter
import com.mohiva.play.xmlcompressor.XMLCompressorFilter
import play.filters.gzip.GzipFilter

/**
 * Uses the default implementation of the HTML and XML compressor filters.
 */
//object Global extends WithFilters(HTMLCompressorFilter(), XMLCompressorFilter())
object Global extends WithFilters(new GzipFilter(), HTMLCompressorFilter())
