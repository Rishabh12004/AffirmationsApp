package com.example.affirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationsapp.data.DataSource
import com.example.affirmationsapp.model.Affirmation
import com.example.affirmationsapp.ui.theme.AffirmationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffirmationsAppTheme {
                 AffirmationApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationAppPreview() {
    AffirmationsAppTheme {
        AffirmationApp()
    }
}

@Composable
fun AffirmationApp() {
    val layoutDirection = LocalLayoutDirection.current//retrieve the current layout directions and save them in a variable. These will be used to configure the padding later.
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection)
            )
    ) {
        AffirmationList(
            affirmationList = DataSource().loadDataSource()
        )
    }
}


@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier){
    Card(modifier = modifier){
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

//@Preview
@Composable
private fun AffirmationCardPreview(){
    AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(affirmationList){ affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}