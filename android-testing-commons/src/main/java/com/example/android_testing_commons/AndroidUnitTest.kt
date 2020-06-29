package com.example.android_testing_commons

import com.haurbano.testing_commons.UnitTest

abstract class AndroidUnitTest : UnitTest() {
    protected val androidMocksFactory = AndroidMocksFactory(mocksFactory)
}