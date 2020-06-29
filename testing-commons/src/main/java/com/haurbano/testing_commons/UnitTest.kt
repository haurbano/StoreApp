package com.haurbano.testing_commons

import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
abstract class UnitTest {
    val mocksFactory = MocksFactory()
}