package br.com.thuler.vagalivre.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.thuler.vagalivre.R
import br.com.thuler.vagalivre.components.AppLogo
import br.com.thuler.vagalivre.components.AppText
import br.com.thuler.vagalivre.components.FormInput
import br.com.thuler.vagalivre.components.RectangularButton
import br.com.thuler.vagalivre.components.SmallIcon
import br.com.thuler.vagalivre.components.UserPhoto
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun HomeScreen(navController: NavController) {

    var dockIsVisible by remember { mutableStateOf(false) }
    var menuIsVisible by remember { mutableStateOf(true) }
    var search by remember { mutableStateOf("") }

    val bauru = LatLng(-22.316705, -49.070066)
//    val suzano = LatLng(-23.537611, -46.309262)
//    val saoJoseDosCampos = LatLng(-23.219840, -45.891566)

    val cameraPositionState = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(bauru, 15f) }

    // Mapa
    Box(modifier = Modifier.fillMaxSize())
    {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
//            Marker(
//                state = MarkerState(position = bauru),
//                title = "Bauru",
//                snippet = "Marker in Bauru"
//            )
        }

        // Menu
        AnimatedVisibility(visible = menuIsVisible) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ) {
                Row(modifier = Modifier.clickable { dockIsVisible = true; menuIsVisible = false }) {
                    SmallIcon(type = "menu")
                }

                }
        }

        // Estacionamento
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center), horizontalArrangement = Arrangement.End) {
            Button(modifier = Modifier.size(width = 70.dp, height = 70.dp),
                onClick = {
                    navController.navigate("parking")
                }
            ) {
                Text(text = "R$5", fontSize = 12.sp, maxLines = 1)
            }
        }

        // Barra de pesquisa
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF5F5F5).copy(alpha = 0.9f),
                        Color.White.copy(alpha = 1f),
                        Color(0xFFF5F5F5).copy(alpha = 0.8f),
                    )
                )
            )
        ) {
            FormInput(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 15.dp, top = 7.dp),
                value = search,
                onValueChange = { search = it},
                label = "Procurar",
                icon = R.drawable.search_24px,
                trailing = {
                    Icon(
                        painter = painterResource(id = R.drawable.my_location_24px),
                        contentDescription = "Minha localização",
                        tint = Color(0XFF878787),
                        modifier = Modifier.clickable {  }
                    )
                },
                keyboardType = KeyboardType.Ascii
            )
        }
    }

    // Painel lateral
    AnimatedVisibility(visible = dockIsVisible) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(end = 200.dp)
                .padding(vertical = 10.dp)
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFD8D8D8).copy(alpha = 0.95f),
                            Color(0xFFF0F0F0).copy(alpha = 1f),
                            Color.White.copy(alpha = 1f),
                            Color(0xFFF0F0F0).copy(alpha = 1f),
                            Color(0xFFD8D8D8).copy(alpha = 0.95f),
                        )
                    )
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.clickable { navController.navigate("settings") }) {
                    SmallIcon(type = "settings")
                }

                Row(modifier = Modifier.clickable { dockIsVisible = false; menuIsVisible = true }) {
                    SmallIcon(type = "close")
                }
            }


            // Main
            Column {

                // TÍTULO
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AppLogo(size = 26.sp)
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .border(
                                BorderStroke(2.dp, Color.Black),
                                shape = RectangleShape
                            )
                            .fillMaxWidth()
                    )
                }

                // FOTO
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(284.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UserPhoto(
                        photo = R.drawable.foto,
                        size = 100.dp
                    )

                    AppText(
                        modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
                        text = "João dos Santos Silva",
                        size = 14.sp,
                        color = R.color.black,
                        weight = FontWeight.Bold
                    )

                    RectangularButton(
                        modifier = Modifier
                            .width(100.dp)
                            .height(30.dp),
                        text = "Editar",
                        onClick = { navController.navigate("profile") },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        border = null
                    )

                }


                // VERSÃO
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .border(
                                BorderStroke(2.dp, Color.Black),
                                shape = RectangleShape
                            )
                            .fillMaxWidth()
                    )

                    AppText(
                        text = "VERSÃO 1.0.0",
                        size = 8.sp,
                        color = R.color.black,
                        weight = FontWeight.Bold
                    )
                }

            }


            // Footer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("login") },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.width(50.dp)) {
                    SmallIcon(type = "logout")
                }
                Row(modifier = Modifier.width(50.dp)) {
                    AppText(text = "Sair", size = 14.sp, color = R.color.black, weight =FontWeight.Bold )
                }
            }

        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}