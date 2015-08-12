# Wookiee - Component: Metrics

[![Build Status](https://travis-ci.org/Webtrends/wookiee-metrics.svg?branch=master)](https://travis-ci.org/Webtrends/wookiee-kafka) [![Coverage Status](https://coveralls.io/repos/Webtrends/wookiee-kafka/badge.svg?branch=master&service=github)](https://coveralls.io/github/Webtrends/wookiee-kafka?branch=master) [![Latest Release](https://img.shields.io/github/release/webtrends/wookiee-kafka.svg)](https://github.com/Webtrends/wookiee-kafka/releases) [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

[Main Wookiee Project](https://github.com/Webtrends/wookiee)

For Configuration information see [Metrics Config](docs/config.md)

The metrics component allows users to track metrics for functionality on their services. It is based off the [codahale github project](http://www.github.com/codahale/metrics). It includes the following metrics:

* Counter - A metric that simply counts the number of time some event has occurred.
* Gauge - Instantaneous measurements of something
* Timer - Times how long a specific event takes to complete
* Histogram - Tracks the distribution of a stream of values.
* Meter - Marks the occurrence of an event

For a working example of how this would work see [Wookiee - Metrics Example](../../examples/example-metrics)