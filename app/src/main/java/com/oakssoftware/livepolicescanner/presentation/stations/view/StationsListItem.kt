package com.oakssoftware.livepolicescanner.presentation.stations.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oakssoftware.livepolicescanner.domain.model.Station
import com.oakssoftware.livepolicescanner.presentation.theme.PoliceScannerProTheme

@Composable
fun StationListItem(
    station: Station,
    onItemClick: (Station) -> Unit,
    onFavoriteButtonClick: (Station) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, start = 4.dp, end = 4.dp)
            .clickable { onItemClick(station) }
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = { onFavoriteButtonClick(station) },
                modifier = Modifier.padding(start = 8.dp),
            ) {
                if (station.isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.inversePrimary,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.inversePrimary
                    )

                }
            }

            Text(
                text = station.name, maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Text(
            modifier = Modifier.padding(start = 22.dp, end = 8.dp, top = 4.dp),
            text = station.location,
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun PreviewStationsListItem() {
    PoliceScannerProTheme {
        Surface {
            StationListItem(
                station = Station(
                    uid = 1,
                    name = "Maltepe Emniyet Mudurlugu",
                    location = "Baglarbasi Mahallesi Bagdat Caddesi",
                    search = "Maltepe Emniyet Mudurlugu Baglarbasi Mahallesi Bagdat Caddesi",
                    isFavorite = true
                ),
                onItemClick = {},
                onFavoriteButtonClick = {},
                modifier = Modifier
            )
        }
    }
}