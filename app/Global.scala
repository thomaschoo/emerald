import com.mohiva.play.htmlcompressor.HTMLCompressorFilter

import play.api.mvc.WithFilters
import play.filters.gzip.GzipFilter

object Global extends WithFilters(new GzipFilter(), HTMLCompressorFilter())
