# Wookiee - Component: Metrics

[![Build Status](https://travis-ci.org/oracle/wookiee-metrics.svg?branch=master)](https://travis-ci.org/oracle/wookiee-metrics) [![Latest Release](https://img.shields.io/github/release/oracle/wookiee-metrics.svg)](https://github.com/oracle/wookiee-metrics/releases) [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

[Main Wookiee Project](https://github.com/oracle/wookiee)

For Configuration information see [Metrics Config](docs/config.md)

The metrics component allows users to track metrics for functionality on their services. It is based off the [codahale github project](http://www.github.com/codahale/metrics). It includes the following metrics:

* Counter - A metric that simply counts the number of time some event has occurred.
* Gauge - Instantaneous measurements of something
* Timer - Times how long a specific event takes to complete
* Histogram - Tracks the distribution of a stream of values.
* Meter - Marks the occurrence of an event

For a working example of how this would work see [Wookiee - Metrics Example](example-metrics)

### Adding to Pom

Add the jfrog repo to your project first:
~~~~
<repositories>
    <repository>
        <id>JFrog</id>
        <url>http://oss.jfrog.org/oss-release-local</url>
    </repository>
</repositories>
~~~~

Add [latest version](https://github.com/oracle/wookiee-metrics/releases/latest) of wookiee:
~~~~
<dependency>
    <groupId>com.webtrends</groupId>
    <artifactId>wookiee-metrics_2.11</artifactId>
    <version>${wookiee.version}</version>
</dependency>
~~~~

## Contributing
This project is not accepting external contributions at this time. For bugs or enhancement requests, please file a GitHub issue unless it’s security related. When filing a bug remember that the better written the bug is, the more likely it is to be fixed. If you think you’ve found a security vulnerability, do not raise a GitHub issue and follow the instructions in our [security policy](./SECURITY.md).

## Security
Please consult the [security guide](./SECURITY.md) for our responsible security vulnerability disclosure process

## License
Copyright (c) 2004, 2023 Oracle and/or its affiliates.
Released under the Apache License Version 2.0
