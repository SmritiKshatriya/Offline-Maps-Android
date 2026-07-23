package com.example.offlinemaps.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.MapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
@Composable
fun MapScreen(
    latitude: Double,
    longitude: Double
){

    val context = LocalContext.current

    Configuration.getInstance().userAgentValue =
        "Tripsutra-KumbhPrototype/1.0 (contact: smritikshatriya07@gmail.com)"
    AndroidView(

        modifier = Modifier.fillMaxSize(),

        factory = {

            MapView(context).apply {

                setTileSource(TileSourceFactory.MAPNIK)

                setMultiTouchControls(true)

                controller.setZoom(18.0)

            }

        },

        update = { map ->

            val point = GeoPoint(latitude, longitude)

            map.controller.setCenter(point)

            map.overlays.clear()

            val marker = Marker(map)
            marker.position = point
            marker.title = "You are here 📍"

            map.overlays.add(marker)

            map.invalidate()

        }

    )
}