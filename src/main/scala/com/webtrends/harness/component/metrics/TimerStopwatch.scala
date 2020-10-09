package com.webtrends.harness.component.metrics

import java.util.concurrent.TimeUnit

import com.webtrends.harness.component.metrics.messages.TimerObservation
import com.webtrends.harness.component.metrics.metrictype.Timer
import com.webtrends.harness.logging.LoggingAdapter

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success, Try}

object TimerStopwatch {
  def apply(name: String, startOnCreate: Boolean = true) = new TimerStopwatch(name, startOnCreate)

  def tryWrapper[T](name: String)(toTry: => T): T = {
    val timer = TimerStopwatch(name)
    Try(toTry) match {
      case Success(t) =>
        timer.success()
        t
      case Failure(t) =>
        timer.failure()
        throw t
    }
  }

  def futureWrapper[T](name: String)(toRun: => Future[T])(implicit ec: ExecutionContext): Future[T] = {
    val timer = TimerStopwatch(name)
    val evaluatedFuture = toRun
    evaluatedFuture.onComplete {
      case Success(_) =>
        timer.success()
      case Failure(_) =>
        timer.failure()
    }

    evaluatedFuture
  }
}

class TimerStopwatch(val name: String, val startOnCreate: Boolean = true) extends MetricsAdapter with LoggingAdapter {

  private var startTime: Option[Long] = None
  private var endTime: Option[Long] = None

  if (startOnCreate) {
    start()
  }

  def start() = {
    startTime = Some(System.currentTimeMillis)
  }

  def success() = finish (Timer(s"${name}.success"))

  def failure() = finish (Timer(s"${name}.failure"))

  def failure(failureType: String) = finish (Timer(s"${name}.${failureType}.failure"))

  private def finish(timer: Timer): Unit = {
    startTime match {
      case Some(start) =>
        endTime = Some(System.currentTimeMillis())
        record (TimerObservation(
          timer,
          endTime.get - start,
          TimeUnit.MILLISECONDS)
        )
      case None =>
        log.error(s"Timer $name finished without being started")
    }
  }

  def durationMillis: Long = {
    (startTime, endTime) match {
      case (Some(start), Some(end))  => end - start
      case (Some(start), None)  => System.currentTimeMillis - start
      case _ => 0
    }
  }
}
