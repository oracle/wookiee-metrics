package com.webtrends.harness.component.metrics

import java.util.concurrent.TimeUnit

import com.webtrends.harness.component.metrics.messages.TimerObservation
import com.webtrends.harness.component.metrics.metrictype.Timer
import com.webtrends.harness.logging.LoggingAdapter

object TimerStopwatch {
  def apply(name: String, startOnCreate: Boolean = true) = new TimerStopwatch(name, startOnCreate)
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

  def failure(timerName: String) = finish (Timer(s"${timerName}.failure"))

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
