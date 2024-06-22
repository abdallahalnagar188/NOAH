package com.example.noah.screen.setting

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun SettingsScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedLanguage by remember { mutableStateOf(Locale.getDefault()) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    if (showDialog) {
        LanguageSelectionDialog(
            onDismiss = { showDialog = false },
            onLanguageSelected = { locale ->
                selectedLanguage = locale
                setLocale(context, selectedLanguage)
                showDialog = false
                coroutineScope.launch {
                    navController.navigate("home") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            }
        )
    }

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
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { showDialog = true }, // Show dialog on click
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.coffie2),
                contentColor = colorResource(id = R.color.coffie)
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp,vertical = 16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.language),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.icon_language),
                    contentDescription = "Icon local",
                    modifier = Modifier.size(30.dp)
                )

            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { navController.navigate("aboutUs") }, // Show dialog on click
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.coffie2),
                contentColor = colorResource(id = R.color.coffie)
            )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp,vertical = 16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.aboutUs),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_about),
                    contentDescription = "Icon local",
                    modifier = Modifier.size(30.dp)
                )

            }
        }
    }
}

@Composable
fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onLanguageSelected: (Locale) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(id = R.string.language_selected))
        },
        text = {
            Column {
                Text(
                    text = stringResource(id = R.string.arabic),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .clickable {
                            onLanguageSelected(Locale("ar"))
                        }
                        .padding(8.dp)
                )
                Text(
                    text = stringResource(id = R.string.english),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .clickable {
                            onLanguageSelected(Locale("en"))
                        }
                        .padding(8.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.coffie))
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
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
