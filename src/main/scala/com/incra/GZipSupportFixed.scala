package com.incra

import java.io.PrintWriter
import java.util.zip.GZIPOutputStream

import org.scalatra._

import javax.servlet.{WriteListener, ServletOutputStream}
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponseWrapper

/**
 * Patched
 *
 * Scalatra handler for gzipped responses.
 */
trait GZipSupportFixed extends Handler {
  self: ScalatraBase =>

  abstract override def handle(req: HttpServletRequest, res: HttpServletResponse) {
    withRequestResponse(req, res) {

      if (isGzip(req)) {
        val gzos = new GZIPOutputStream(res.getOutputStream)
        val w = new PrintWriter(gzos)
        val r = response

        ScalatraBase onRenderedCompleted { _ =>
          w.flush()
          w.close()
        }

        val gzsos = new GZipServletOutputStream(gzos, r.getOutputStream)
        val wrapped = new WrappedGZipResponse(r, gzsos, w)

        ScalatraBase.onCompleted { _ => {
          if (! isGzip(wrapped)) {
            wrapped.addHeader("Content-Encoding", "gzip")
          }
        }
        }

        super.handle(req, wrapped)
      } else {
        super.handle(req, res)
      }
    }
  }

  class GZipServletOutputStream(gzos: GZIPOutputStream, orig: ServletOutputStream) extends ServletOutputStream {
    override def write(b: Int): Unit = gzos.write(b)

    override def setWriteListener(writeListener: WriteListener): Unit = orig.setWriteListener(writeListener)

    override def isReady: Boolean = orig.isReady()
  }

  class WrappedGZipResponse(res: HttpServletResponse, gzsos: GZipServletOutputStream, w: PrintWriter) extends HttpServletResponseWrapper(res) {
    override def getOutputStream: ServletOutputStream = gzsos

    override def getWriter: PrintWriter = w

    override def setContentLength(i: Int) = {} // ignoring content length as it won't be the same when gzipped
  }

  /**
   * Returns true if Accept-Encoding contains gzip.
   */
  private[this] def isGzip(request: HttpServletRequest): Boolean = {
    Option(request.getHeader("Accept-Encoding")).getOrElse("").toUpperCase.contains("GZIP")
  }

  /**
   * Returns true if Content-Encoding contains gzip.
   */
  private[this] def isGzip(response: HttpServletResponse): Boolean = {
    Option(response.getHeader("Content-Encoding")).getOrElse("").toUpperCase.contains("GZIP")
  }
}