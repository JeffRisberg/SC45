package com.incra.app

import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

import com.escalatesoft.subcut.inject.Injectable
import com.incra.GZipSupportFixed
import com.incra.infrastructure.Logging
import org.fusesource.scalate.TemplateEngine
import org.fusesource.scalate.layout.DefaultLayoutStrategy
import org.scalatra._
import org.scalatra.scalate.ScalateSupport
import scala.collection.mutable

/**
 * @author Jeffrey Risberg
 * @since 9/10/2014
 */
trait SC45Stack extends ScalatraServlet
with Injectable
with GZipSupportFixed
with ScalateSupport
with Logging {

  /* wire up the precompiled templates */
  override protected def defaultTemplatePath: List[String] = List("/WEB-INF/templates/views")

  override protected def createTemplateEngine(config: ConfigT) = {
    val engine = super.createTemplateEngine(config)
    engine.layoutStrategy = new DefaultLayoutStrategy(engine,
      TemplateEngine.templateTypes.map("/WEB-INF/templates/layouts/default." + _): _*)
    engine.packagePrefix = "templates"
    engine
  }

  /* end wiring up the precompiled templates */

  override protected def templateAttributes(implicit request: HttpServletRequest): mutable.Map[String, Any] = {
    super.templateAttributes ++ mutable.Map.empty // Add extra attributes here, they need bindings in the build file
  }

  /**
   * Attempts to find a static resource matching the request path.  Override
   * to return None to stop this.
   */
  protected def serveStaticResourceFixed(request: HttpServletRequest, response: HttpServletResponse): Option[Any] = {
    servletContext.resource(request) map { _ =>

      response match {
        case wGZR: WrappedGZipResponse =>
          wGZR.addHeader("Content-Encoding", "gzip")
      }

      servletContext.getNamedDispatcher("default").include(request, response)

      response match {
        case wGZR: WrappedGZipResponse =>
          wGZR.getWriter().flush()
      }
    }
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResourceFixed(request, response) getOrElse resourceNotFound()
  }
}
