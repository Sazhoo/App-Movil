package com.example.actividad1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. CLASE PRINCIPAL: Controla qué pantalla se muestra actualmente
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("profile") }

            if (currentScreen == "profile") {
                StudentProfileScreen(
                    onNavigateToAnime = { currentScreen = "anime" }
                )
            } else {
                AnimeScreen(
                    onBackToProfile = { currentScreen = "profile" }
                )
            }
        }
    }
}

// 2. PANTALLA 1: Tu Perfil Universitario
@Composable
fun StudentProfileScreen(onNavigateToAnime: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Capa de Fondo: Imagen moradita
        Image(
            painter = painterResource(id = R.drawable.moradita),
            contentDescription = "Fondo Universitario Estético",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Filtro Moradito (Estilo Say Yes to Heaven)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0B0FF).copy(alpha = 0.6f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "My University Life",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Foto de perfil circular
            Image(
                painter = painterResource(id = R.drawable.logoo),
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Estudiante Universitario",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(20.dp))

            InfoCard(label = "PROGRAMA", value = "Auxiliar en Sistemas")
            Spacer(modifier = Modifier.height(12.dp))
            InfoCard(label = "INSTITUCIÓN", value = "CESDE")

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onNavigateToAnime,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Ver Mis Animes Favoritos ✨",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Derechos de autor
            Text(
                text = "Creado por Juan Zamora © 2026",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// Estructura de datos para los 5 animes
data class AnimeItem(
    val title: String,
    val description: String,
    val imageRes: Int
)

// 3. PANTALLA 2: Sección de Anime con los 5 espacios personalizados
@Composable
fun AnimeScreen(onBackToProfile: () -> Unit) {
    // Lista con los 5 animes. Puedes cambiar R.drawable.moradita por tus propias imágenes en drawable
    val animeList = listOf(
        AnimeItem("Clasroom of the elite", "Ayanokouji con Karuizawa.", R.drawable.ayano22),
        AnimeItem("Rent-no girltfriend", "La prota 10/10.", R.drawable.kanojo),
        AnimeItem("Monster", "Taki Taki rumba.", R.drawable.kenzo_tenma),
        AnimeItem("Kimino nawa ", "your name.", R.drawable.kimi_no_nawa),
        AnimeItem("Shingeki no Kyojin", "Eren Yeager.", R.drawable.eren_yeager)
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo estético para la segunda pantalla
        Image(
            painter = painterResource(id = R.drawable.moradita),
            contentDescription = "Fondo Anime Estético",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Filtro morado más profundo para diferenciar la sección
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF8A2BE2).copy(alpha = 0.5f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "My Anime Collection",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista deslizable (LazyColumn) para mostrar los 5 elementos
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(animeList) { anime ->
                    AnimeCard(
                        title = anime.title,
                        description = anime.description,
                        imageRes = anime.imageRes
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackToProfile,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Volver al Perfil",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Creado por Juan Zamora © 2026",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Componente para cada tarjeta de anime
@Composable
fun AnimeCard(title: String, description: String, imageRes: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, Color.White, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

// Componente reutilizable para las tarjetas del perfil
@Composable
fun InfoCard(label: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = label,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}