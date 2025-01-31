package com.example.presentation.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.model.remote.Offer
import com.example.presentation.ui.theme.DarkBlue
import com.example.presentation.ui.theme.DarkGreen
import com.example.presentation.ui.theme.Green
import com.example.presentation.ui.theme.Grey1
import com.example.presentation.ui.theme.text1
import com.example.presentation.ui.theme.title4
import com.example.presentation.R

@Composable
fun OfferItem(
    offer: Offer
) {
    val context = LocalContext.current
    val iconResId  = when (offer.id) {
        "near_vacancies" -> Pair(R.drawable.ic_marker, DarkBlue)
        "level_up_resume" -> Pair(R.drawable.ic_small_star, DarkGreen)
        "temporary_job" -> Pair(R.drawable.ic_vacancies_active, DarkGreen)
        else -> null
    }

    Column(
        modifier = Modifier
            .width(132.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .clickable(onClick = {
                offer.link.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            })
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            iconResId?.let {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(iconResId.second),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = iconResId.first),
                        contentDescription = "Custom Icon"
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                color = Color.White,
                style = title4,
                text = offer.title,
                maxLines = if (offer.button != null) 2 else 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            offer.button?.let {
                Text(
                    color = Green,
                    style = text1,
                    text = it.text
                )
            }
        }
    }
}