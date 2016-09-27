package util

import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}

trait TestFutures extends ScalaFutures with IntegrationPatience

