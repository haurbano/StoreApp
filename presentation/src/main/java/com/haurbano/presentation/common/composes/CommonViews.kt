package com.haurbano.presentation.common.composes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingScreen() {
    val modifier = Modifier.fillMaxSize()
    CircularProgressIndicator(modifier = modifier)
}

@Composable
fun ErrorScreen() {
    Column() {
        Text(text = "Something was wrong")
    }
}