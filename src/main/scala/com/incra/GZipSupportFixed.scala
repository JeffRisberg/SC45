package com.incra

import org.scalatra._

import java.io.PrintWriter
import java.util.zip.GZIPOutputStream
import javax.servlet.ServletOutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

/**
 * Scalatra handler for gzipped responses, with correction to not emit the content-encoding header more than once.
 *
 * @author Jeffrey Risberg
 * @since 12/23/2014.
 */
trait GZipSupportFixed extends Handler {
  self: ScalatraBase =>

  abstract override def handle(req: HttpServletRequest, res: HttpServletResponse) {
    withRequestResponse(req, res) {
      if (isGzip) {
        val gzos = new GZIPOutputStream(res.getOutputStream)
        val w = new PrintWriter(gzos)
        val gzsos = new ServletOutputStream {
          def write(b: Int) {
            gzos.write(b)
          }
        }

        val response = new HttpServletResponseWrapper(res) {
          override def getOutputStream: ServletOutputStream = gzsos

          override def getWriter: PrintWriter = w

          override def setContentLength(i: Int) = {} // ignoring content length as it won't be the same when gzipped
        }

        /** BEGIN PATCH */

        ScalatraBase onCompleted { _ =>
          val currentValue = response.getHeader("Content-Encoding")
          if (currentValue == null || currentValue.indexOf("gzip") == -1) {
            println("adding gzip header")
            response.addHeader("Content-Encoding", "gzip")
          }
          else {
            println("skipping adding gzip header")
          }
        }

        /** END PATCH */

        ScalatraBase onRenderedCompleted { _ =>
          w.flush()
          w.close()
        }

        withRequestResponse(req, response) {
          super.handle(req, response)
        }
      } else {
        super.handle(req, res)
      }
    }
  }

  /**
   * Returns true if Accept-Encoding contains gzip.
   */
  private[this] def isGzip(implicit request: HttpServletRequest): Boolean = {
    Option(request.getHeader("Accept-Encoding")).getOrElse("")
      .toUpperCase.contains("GZIP")
  }
}