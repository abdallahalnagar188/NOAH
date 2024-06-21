package com.example.noah.screen.setting

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noah.R
import java.util.Locale

@Composable
fun SettingsScreen(navController: NavController) {
    var selectedLanguage by remember { mutableStateOf(Locale.getDefault()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.bgcolor),
                contentScale = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingTopBar(navController = navController)

        Card(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.coffie))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.language),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Arabic language option
                    Text(
                        text = stringResource(id = R.string.arabic),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.clickable {
                            selectedLanguage = Locale("ar")
                            setLocale(context, selectedLanguage)
                            navController.navigate("home"){
                                popUpTo("home"){
                                    inclusive = true
                                }
                            } // Navigate to home screen
                        }
                    )

                    // English language option
                    Text(
                        text = stringResource(id = R.string.english),
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.clickable {
                            selectedLanguage = Locale("en")
                            setLocale(context, selectedLanguage)
                            navController.navigate("home"){
                                popUpTo("home"){
                                    inclusive = true
                                }
                            } // Navigate to home screen
                        }
                    )
                }
            }
        }
    }
}

// Function to set locale globally
fun setLocale(context: Context, locale: Locale) {
    Locale.setDefault(locale)
    val resources = context.resources
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    context.createConfigurationContext(configuration)
    resources.updateConfiguration(configuration, resources.displayMetrics)
}
